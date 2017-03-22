package com.seeyon.redis.se.queue.pojo;

import java.io.Serializable;

/**
 * Created by yangyu on 16/10/14.
 */
public class RequestPojo implements Serializable{

    private String flowId;
    private Object data;
    private Object parameters;

    public RequestPojo(String flowId){
        this.flowId=flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getParameters() {
        return parameters;
    }

    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }
}
