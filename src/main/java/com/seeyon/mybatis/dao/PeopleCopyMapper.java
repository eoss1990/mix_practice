package com.seeyon.mybatis.dao;

import com.seeyon.mybatis.pojo.PeopleCopy;

public interface PeopleCopyMapper {

    int deleteByPrimaryKey(String id);

    int insert(PeopleCopy record);

    int insertSelective(PeopleCopy record);

    PeopleCopy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleCopy record);

    int updateByPrimaryKey(PeopleCopy record);
}