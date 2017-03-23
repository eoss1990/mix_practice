package com.seeyon.cxf.rest;

import com.seeyon.mybatis.pojo.People;

import javax.ws.rs.*;
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

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<People> getPeople(People peo){
        System.out.println(peo.getName());
        People people = new People("10","2","3","4","5");
        return Arrays.asList(people);
    }
}
