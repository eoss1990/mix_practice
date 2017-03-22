package com.seeyon.redis.dao;

import com.seeyon.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016-9-6.
 */

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String,User> redisTemplate;

    public void save(User user) {
        /*redisTemplate.opsForList();
        redisTemplate.opsForSet();
        redisTemplate.opsForHash()*/
        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();
        valueOper.set(String.valueOf(user.getId()), user);
    }

    public User read(String id) {
        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();
        return valueOper.get(id);
    }

    public void delete(String id) {
        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();
        RedisOperations<String,User> RedisOperations  = valueOper.getOperations();
        RedisOperations.delete(id);
    }

}
