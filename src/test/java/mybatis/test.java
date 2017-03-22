package mybatis;

import com.seeyon.SpringBean.BeanSelf;
import com.seeyon.jdbc.JdbcUtil;
import com.seeyon.mybatis.manager.PeopleCopyManager;
import com.seeyon.mybatis.manager.PeopleManager;
import com.seeyon.mybatis.pojo.People;
import com.seeyon.mybatis.pojo.PeopleCopy;
import com.seeyon.redis.se.queue.manager.QueueManager;
import com.seeyon.redis.se.threadPool.ThreadPoolSingleton;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yangyu on 16/10/17.
 */
public class test {

    private static ClassPathXmlApplicationContext factory;
    private static PeopleManager peopleManager;
    private static PeopleCopyManager peopleCopyManager;
    private static QueueManager<ArrayList<People>> queueManager;
    private static final int PAGE_SIZE=1000;
    private static final String DATA = "_data";
    private static final int ADAPTER_NUM=10;

    static {
        factory = new ClassPathXmlApplicationContext("mybatis/redis-mybatis.xml");
        peopleManager = (PeopleManager) factory.getBean("peopleManagerImpl");
        peopleCopyManager = (PeopleCopyManager) factory.getBean("peopleCopyManagerImpl");
        queueManager = (QueueManager<ArrayList<People>>) factory.getBean("queueManager");
    }

    /**
     * 数据分拆存入redis，并且使用线程池启动5个线程
     * 10005条数据：读取耗时700ms
     * 10005条数据：5线程读取+写入耗时9s，10线程7s
     * 100000条数据：读取耗时1371ms
     * 100000条数据：10线程读取+写入耗时50秒
     * @param args
     */
    public static void main(String[] args) {

        long startTime=System.currentTimeMillis();
        String flowId = "flow001";
        String flowDataKey = flowId+DATA;
        ArrayList<People> peopleArrayList = (ArrayList<People>) peopleManager.selectAll();
        long readTime=System.currentTimeMillis();
        System.out.println("读取所花时间： "+(readTime-startTime)+"ms");
        System.out.println("读取数据量："+peopleArrayList.size());

        if (peopleArrayList==null)
            return;

        for(;;)
        {
            if (peopleArrayList.size()>PAGE_SIZE)
            {
                List<People> sub = peopleArrayList.subList(0,PAGE_SIZE);
                ArrayList<People> subList = new ArrayList<>(sub);
                sub.clear();
                queueManager.push(flowDataKey,subList);
            }else {
                queueManager.push(flowDataKey,peopleArrayList);
                break;
            }
        }


        for (int i = 0; i < ADAPTER_NUM; i++) {
            ThreadPoolSingleton.excute(new JdbcAdapter(flowDataKey,startTime));
        }

        LockSupport.park();

    }

    public static class JdbcAdapter implements Runnable{

        private String dataKey;

        private long startTime;

        public JdbcAdapter(String dataKey,long startTime){
            this.dataKey=dataKey;
            this.startTime=startTime;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":开始");
            PeopleCopy pc = new PeopleCopy();
            for(;;)
            {
                ArrayList<People> peopleList = queueManager.pop(dataKey);
                if (peopleList==null)
                    break;
                for (People p :peopleList){
                    pc.setId(p.getId());
                    pc.setName(p.getName());
                    pc.setAddress(p.getAddress());
                    pc.setTel(p.getTel());
                    pc.setSex(p.getSex());
                    peopleCopyManager.insert(pc);
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+":完成,"+"运行时间："+(endTime-startTime)/1000+"s");
        }
    }

    /**
     * 造数据
     */
    @Test
    public void createData(){
        People people = new People("","yangyu","chengdu","18615720100","男");
        for (int i = 60000; i < 100000; i++) {
            people.setId(Integer.valueOf(i).toString());
            peopleManager.insertOrUpdate(people);
            System.out.println(i);
        }
    }

    /**
     * mybatis存取数据时间
     * 10005条数据：读取耗时887ms
     * 10005条数据：读取+写入耗时19s
     */
    @Test
    public void testMybatisSpeed(){

        long startTime=System.currentTimeMillis();

        PeopleCopy pc = new PeopleCopy();
        ArrayList<People> peopleArrayList = (ArrayList<People>) peopleManager.selectAll();

        long readTime=System.currentTimeMillis();
        System.out.println("读取所花时间： "+(readTime-startTime)+"ms");

        ListIterator<People> listIterator = peopleArrayList.listIterator();
        while (listIterator.hasNext()){
            People p = listIterator.next();
            pc.setId(p.getId());
            pc.setName(p.getName());
            pc.setAddress(p.getAddress());
            pc.setTel(p.getTel());
            pc.setSex(p.getSex());
            peopleCopyManager.insert(pc);
        }

        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)/1000+"s");
    }

    /**
     * JDBC存取数据时间
     * 10005条数据：读取耗时461ms
     * 10005条数据：读取+写入耗时16s
     */
    @Test
    public void testJdbcSpeed(){
        long startTime=System.currentTimeMillis();

        JdbcUtil jdbcUtil = new JdbcUtil();
        try {
            jdbcUtil.getConnection();
            String sql = "select * from people";
            List<Map<String, Object>> list = jdbcUtil.findModeResult(sql,null);

            long readTime=System.currentTimeMillis();
            System.out.println("读取所花时间： "+(readTime-startTime)+"ms");

            sql = "insert into people_copy(id,name,address,tel,sex) values (?,?,?,?,?)";
            for(Map<String,Object> map : list)
            {
                List<Object> params = new ArrayList<Object>();
                params.add(map.get("id").toString());
                params.add(map.get("name").toString());
                params.add(map.get("address").toString());
                params.add(map.get("tel").toString());
                params.add(map.get("sex").toString());
                jdbcUtil.updateByPreparedStatement(sql,params);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                jdbcUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)/1000+"s");
    }

    @Test
    /**
     * 测试for与Iterator的速度
     * 结论：for比Iterator略快一点点
     */
    public void testForAndIterator(){
        ArrayList<People> peopleArrayList = (ArrayList<People>) peopleManager.selectAll();

        long startTimeFor=System.currentTimeMillis();   //获取开始时间
        for (People p:peopleArrayList)
        {
            p.getId();
        }

        long endTimeFor=System.currentTimeMillis(); //获取结束时间
        System.out.println("for程序运行时间： "+(endTimeFor-startTimeFor)+"ms");

        long startTime=System.currentTimeMillis();   //获取开始时间
        ListIterator<People> it = peopleArrayList.listIterator();
        while (it.hasNext()){
            People people = it.next();
            people.getId();
        }

        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("Iterator程序运行时间： "+(endTime-startTime)+"ms");
    }
}
