package com.seeyon.redis.se.queue.manager.impl;

import com.seeyon.redis.se.queue.dao.QueueDao;
import com.seeyon.redis.se.queue.manager.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangyu on 16/10/14.
 */
@Service("queueManager")
public class QueueManagerImpl implements QueueManager{

    @Autowired
    private QueueDao queueDao;

    @Override
    public Object pop(String queueName) {
        return this.queueDao.pop(queueName);
    }

    @Override
    public void push(String queueName, Object o) {
        this.queueDao.push(queueName,o);
    }
}
