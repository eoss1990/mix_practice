package com.seeyon.mybatis.dao;

import com.seeyon.mybatis.pojo.People;

import java.util.List;

public interface PeopleMapper {

    int deleteByPrimaryKey(String id);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);

    List<People> selectAll();
}