package com.seeyon.mybatis.pojo;

public class PeopleCopy {
    private String id;

    private String name;

    private String address;

    private String tel;

    private String sex;

    public PeopleCopy() {
    }

    public PeopleCopy(String id, String name, String address, String tel, String sex) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}