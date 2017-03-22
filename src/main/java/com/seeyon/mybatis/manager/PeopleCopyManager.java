package com.seeyon.mybatis.manager;

import com.seeyon.mybatis.pojo.PeopleCopy;

/**
 * Created by yangyu on 16/10/18.
 */
public interface PeopleCopyManager {
    public void insertOrUpdate(PeopleCopy people);

    public void insert(PeopleCopy people);
}
