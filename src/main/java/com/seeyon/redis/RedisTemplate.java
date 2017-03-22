package com.seeyon.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2016-9-5.
 */
public class RedisTemplate {
    /**
     * 数据源
     */
    private JedisPool jedisPool;

    static {
        System.out.println("daying-------------------------");
    }
    /**
     * 获取数据库连接
     *
     * @return conn
     */
    public Jedis getConnection() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public void closeConnection(Jedis jedis) {
        if (null != jedis) {
            try {
                jedisPool.returnResource(jedis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置连接池
     *
     * @param 数据源
     */
    public void setJedisPool(JedisPool JedisPool) {
        this.jedisPool = JedisPool;
    }

    /**
     * 获取连接池
     *
     * @return 数据源
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
