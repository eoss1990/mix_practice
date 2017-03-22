package com.seeyon.controller;

import com.seeyon.po.User;
import com.seeyon.redis.RedisTemplate;
import com.seeyon.redis.dao.UserDao;
import com.seeyon.redis.manager.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016-9-5.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    //@Autowired
    private RedisTemplate redisTemplate;

    //@Autowired
    private UserDao userDao;

    //@Autowired
    private RedisManager redisManager;

    @RequestMapping("/gocache")
    public ModelAndView goCache(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Jedis jedis = redisTemplate.getConnection();
        String yang = jedis.get("yang");
        Writer out = response.getWriter();
        out.write(yang);
        return null;
    }

    @RequestMapping("/redisCache")
    public ModelAndView redisCache(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = new User();
        user.setId("99999");
        user.setUserName("杨宇");
        user.setUserAge("28");
        user.setUserAddress("成都茶店子");
        userDao.save(user);
        Writer out = response.getWriter();
        out.write("success");
        return null;
    }

    @RequestMapping("/getCache")
    public ModelAndView getCache(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = new User();
        user = userDao.read("99999");
        Writer out = response.getWriter();
        out.write(user.getUserName());
        return null;
    }

    @RequestMapping("/jdbcOnly")
    public ModelAndView jdbcOnly(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            redisManager.jdbcReadAndWriteOnly();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/jdbcRedis")
    public ModelAndView jdbcRedis(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("点击了redis缓存button---------------------------------------------------");
        try {
            redisManager.jdbcForRedis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成--------------------------------------------------------------");
        return null;
    }

    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Writer out = response.getWriter();
        out.write("success");
        return null;
    }

}
