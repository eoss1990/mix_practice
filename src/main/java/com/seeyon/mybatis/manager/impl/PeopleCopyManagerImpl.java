package com.seeyon.mybatis.manager.impl;

import com.seeyon.mybatis.dao.PeopleCopyMapper;
import com.seeyon.mybatis.manager.PeopleCopyManager;
import com.seeyon.mybatis.pojo.PeopleCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yangyu on 16/10/18.
 */
@Service("peopleCopyManagerImpl")
public class PeopleCopyManagerImpl implements PeopleCopyManager {

    @Autowired
    private PeopleCopyMapper peopleCopyMapper;

    @Override
    public void insertOrUpdate(PeopleCopy people) {
        if (people==null||StringUtils.isEmpty(people.getId()))
            return;
        if(peopleCopyMapper.selectByPrimaryKey(people.getId())==null)
            peopleCopyMapper.insert(people);
        else
            peopleCopyMapper.updateByPrimaryKey(people);
    }

    @Override
    public void insert(PeopleCopy people) {
        if (people==null||StringUtils.isEmpty(people.getId()))
            return;
        peopleCopyMapper.insert(people);
    }
}
