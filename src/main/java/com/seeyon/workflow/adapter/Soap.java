package com.seeyon.workflow.adapter;

import com.seeyon.workflow.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

/**
 * Created by yangyu on 16/10/31.
 */
public class Soap extends Service {
    
    private String url;
    private String userName;
    private String passWord;
    private String method;
    
    public Document execute(Document document){
        System.out.println("I am soap Adapter!");
        document.getRootElement().addElement("soap");
        return document;
    }

    @Override
    public boolean rout(String condition) {
        return true;
    }
}
