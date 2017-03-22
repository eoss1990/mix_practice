package com.seeyon.mybatis.manager;

import com.seeyon.mybatis.pojo.People;

import java.util.List;

/**
 * Created by yangyu on 16/10/17.
 */
public interface PeopleManager {
    public People getPeopleById(String id);

    public List<People> selectAll();

    public void insertOrUpdate(People people);

    public void insert(People people);

    public void update(People people);

    public void insertAndUpdate(People people);
}
