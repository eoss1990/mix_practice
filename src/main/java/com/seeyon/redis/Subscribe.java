package com.seeyon.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016-9-5.
 */
public class Subscribe {

    @Test
    public void subscribeClient() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-config.xml");
        RedisTemplate ru = (RedisTemplate) ac.getBean("redisTemplate");
        final Jedis jedis = ru.getConnection();
        final Mylistener listener = new Mylistener();
        //可以订阅多个频道
//订阅得到信息在lister的onMessage(...)方法中进行处理
//jedis.subscribe(listener, "foo", "watson");

//也用数组的方式设置多个频道
//jedis.subscribe(listener, new String[]{"hello_foo","hello_test"});

//这里启动了订阅监听，线程将在这里被阻塞
//订阅得到信息在lister的onPMessage(...)方法中进行处理
        jedis.psubscribe(listener, new String[]{"yangyu_*"});//使用模式匹配的方式设置频道
    }

    @Test
    public void publish(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-config.xml");
        RedisTemplate ru = (RedisTemplate) ac.getBean("redisTemplate");
        Jedis jedis = ru.getConnection();
        jedis.publish("yangyu_1", "这是一个DEE任务");
        jedis.publish("yangyu_2", "这个一个交换中心公文");
    }


}
