package com.seeyon.workflow.adapter;

import com.seeyon.workflow.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

/**
 * Created by yangyu on 16/10/31.
 */
public class Jdbc extends Service{

    private String dataSourceId;

    private String userName;

    private String passWord;

    public Document execute(Document document){
        System.out.println("I am Jdbc Adapter!");
        document.add(DocumentHelper.createElement("jdbc"));
        return document;
    }

    public boolean rout(String condition) {
        System.out.println(condition);
        return true;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
