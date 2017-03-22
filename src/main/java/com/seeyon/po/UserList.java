package com.seeyon.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-9-6.
 */
public class UserList implements Serializable {

    private List<User> listUser = new ArrayList<User>();

    public void setUser(User user)
    {
        this.listUser.add(user);
    }

    public List<User> getUserList()
    {
        return this.listUser;
    }

    public void clear()
    {
        this.listUser.clear();
    }
}
