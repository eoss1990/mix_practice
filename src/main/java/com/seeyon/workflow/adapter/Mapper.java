package com.seeyon.workflow.adapter;

import com.seeyon.workflow.AbstractAdapter;
import com.seeyon.workflow.Service;
import org.apache.ibatis.annotations.Case;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

/**
 * Created by yangyu on 16/10/31.
 */
public class Mapper extends Service {

    private String inBound;

    private String outBound;

    public Document execute(Document document){
        System.out.println("I am mapper Adapter!"+"inBound:"+inBound+",outBound:"+outBound);
        document.getRootElement().addElement(outBound);
        return document;
    }

    @Override
    public boolean rout(String condition) {
        switch (condition){
            case "true":
                return true;
            case "false":
                return false;
        }
        return false;
    }

    public String getInBound() {
        return inBound;
    }

    public void setInBound(String inBound) {
        this.inBound = inBound;
    }

    public String getOutBound() {
        return outBound;
    }

    public void setOutBound(String outBound) {
        this.outBound = outBound;
    }
}
