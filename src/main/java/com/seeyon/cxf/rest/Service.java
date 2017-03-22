package com.seeyon.cxf.rest;

import com.seeyon.mybatis.pojo.People;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangyu on 2017/2/17.
 */
public class Service {

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> getAll(){
        People people = new People("1","2","3","4","5");
        return Arrays.asList(people);
    }
}
