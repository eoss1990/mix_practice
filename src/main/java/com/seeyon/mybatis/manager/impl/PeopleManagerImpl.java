package com.seeyon.mybatis.manager.impl;

import com.seeyon.mybatis.dao.PeopleMapper;
import com.seeyon.mybatis.manager.PeopleManager;
import com.seeyon.mybatis.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by yangyu on 16/10/17.
 */
@Service("peopleManagerImpl")
public class PeopleManagerImpl implements PeopleManager {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public People getPeopleById(String id) {
        return peopleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<People> selectAll() {
        return peopleMapper.selectAll();
    }

    @Override
    public void insertOrUpdate(People people) {
        if (StringUtils.isEmpty(people.getId()))
            return;
        if(peopleMapper.selectByPrimaryKey(people.getId())==null)
            peopleMapper.insert(people);
        else
            peopleMapper.updateByPrimaryKey(people);
    }

    public void insert(People people){
        peopleMapper.insert(people);
    }

    public void update(People people){
        peopleMapper.updateByPrimaryKey(people);
    }

    @Transactional
    public void insertAndUpdate(People people){
        update(people);
        insert(people);
    }
}
