package com.seeyon.workflow;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

/**
 * Created by yangyu on 16/11/2.
 */
public class SeUtil {
    public static String getSetMethod(String fieldName){
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

    public static Document merge(Document src,Document target){
        if (src==null||src.content().size()==0){
            src = DocumentHelper.createDocument();
            src.addElement("response").add(target.getRootElement());
        }
        else
            src.getRootElement().add(target.getRootElement());
        return src;
    }
}
