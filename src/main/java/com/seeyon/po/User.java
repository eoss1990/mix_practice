package com.seeyon.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-9-2.
 */
public class User implements Serializable {

    private String id;
    private String userName;
    private String userAge;
    private String userAddress;

    public User()
    {}

    public User(String id,String userName,String userAge,String userAddress)
    {
        this.id=id;
        this.userName=userName;
        this.userAge=userAge;
        this.userAddress=userAddress;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAge() {
        return userAge;
    }
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

}

