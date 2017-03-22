package com.seeyon.redis.manager.impl;

import com.seeyon.jdbc.JdbcUtil;
import com.seeyon.po.User;
import com.seeyon.po.UserList;
import com.seeyon.redis.dao.ObjectDao;
import com.seeyon.redis.manager.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-9-6.
 */
@Service
public class RedisManagerImpl implements RedisManager {

    @Autowired
    private ObjectDao<UserList> userListObjectDao;

    @Override
    public void jdbcReadAndWriteOnly() throws SQLException {

        UserList userList = userListObjectDao.getRandomByPattern("");
        if (userList==null)
        {
            System.out.println("none");
        }
    }

    @Override
    public void jdbcForRedis() throws SQLException {
        System.out.println("开启缓存模式");
        Integer count  = 1;
        long startTime=System.currentTimeMillis();   //获取开始时间

        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        String sql_get = "select * from user";
        String sql_insert = "insert into user_redis(id,userName,userAge,userAddress) values (?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        List<Map<String, Object>> list = jdbcUtil.findModeResult(sql_get, null);
        int total = list.size();
        Iterator<Map<String, Object>> iterator = list.iterator();
        UserList userList = new UserList();
        while (iterator.hasNext())
        {
            Map<String, Object> map = iterator.next();
            if (total>100)//开启缓存模式
            {
                User user = new User(map.get("id").toString(),map.get("userName").toString(),map.get("userAge").toString(),
                        map.get("userAddress").toString());
                userList.setUser(user);
                if (userList.getUserList().size()==100)
                {
                    String id = "redis"+count.toString();
                    userListObjectDao.save(id,userList);
                    userList.clear();
                    count++;
                }
                if (!iterator.hasNext())
                {
                    String id = "redis"+count.toString();
                    userListObjectDao.save(id,userList);
                    userList.clear();
                    count++;
                }

            }
            else
            {
                System.out.println("数据少于100条开启直接JDBC插入数据库模式");
                params.add(map.get("id"));
                params.add(map.get("userName"));
                params.add(map.get("userAge"));
                params.add(map.get("userAddress"));
                jdbcUtil.updateByPreparedStatement(sql_insert,params);
                params.clear();
            }
        }




        jdbcUtil.closeConnection();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)/1000+"s");

    }
}
