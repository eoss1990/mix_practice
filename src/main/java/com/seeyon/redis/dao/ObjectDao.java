package com.seeyon.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016-9-6.
 */
@Repository
public class ObjectDao<T> {

    @Autowired
    private RedisTemplate<String,T> redisTemplate;

    public void save(String id ,T t) {
        /*redisTemplate.opsForList();
        redisTemplate.opsForSet();
        redisTemplate.opsForHash()*/
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        valueOper.set(id,t);
    }

    public T read(String id) {
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        return valueOper.get(id);
    }

    public void delete(String id) {
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        RedisOperations<String,T> RedisOperations  = valueOper.getOperations();
        RedisOperations.delete(id);
    }

    public T getRandomByPattern(String pattern){
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        RedisOperations<String,T> RedisOperations  = valueOper.getOperations();
        Set<String> set = RedisOperations.keys(pattern);
        if (set!=null)
        {
            for (String key : set)
            {
                return valueOper.get(key);
            }
        }
        return null;

    }

    public List<T> multiGet(Collection<String> stringCollection){
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        return valueOper.multiGet(stringCollection);
    }
}
