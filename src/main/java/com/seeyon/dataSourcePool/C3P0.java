package com.seeyon.dataSourcePool;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by yangyu on 2017/3/10.
 */
public class C3P0 {

    public static void main(String[] args) throws PropertyVetoException {

        long begin = System.currentTimeMillis();
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://10.211.55.4:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("yy926498");
        dataSource.setCheckoutTimeout(5000);
        dataSource.setInitialPoolSize(1);
        dataSource.setMinPoolSize(1);
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireRetryAttempts(2);
        try {
            dataSource.getConnection();
        } catch (Exception e) {
            long time = System.currentTimeMillis()-begin;
            System.out.println(time+"ms");
            e.printStackTrace();
        }
    }

    @Test
    public void testDruid(){
        long begin = System.currentTimeMillis();
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl("jdbc:mysql://10.211.55.4:3306/test");
        dds.setUsername("root");
        dds.setPassword("yy926498");
        dds.setInitialSize(1);
        dds.setMaxActive(5);
        dds.setMaxWait(5000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        //连接出错后重试次数
        dds.setConnectionErrorRetryAttempts(2);

        /**
         * setLoginTimeout可以控制ip不正确的情况下的时间
         */
        dds.setLoginTimeout(5);


//        dds.setTestWhileIdle(false);
//        dds.setTestOnReturn(false);
//        dds.setTestOnBorrow(false);

        /**
         * connectTime控制socket连接时间
         * StandardSocketFactory中connect(String hostname, int portNumber, Properties props)方法会使用
         */
        Properties propertie = new Properties();
        propertie.setProperty("connectTimeout","5000");
        dds.setConnectProperties(propertie);

        try {
            dds.getConnection(5000);
        } catch (SQLException e) {
            long time = System.currentTimeMillis()-begin;
            System.out.println(time+"ms");
            e.printStackTrace();
        }
    }
}
