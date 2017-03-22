package com.seeyon.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.event.SourceFilteringListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyu on 16/10/27.
 */
@Controller
@RequestMapping("/test1")
@SessionAttributes("id")//将sessionAttributes自动设置到modelAttributes
public class TestSpringMvc implements EnvironmentAware{

    private Environment environment;

    @ModelAttribute("name")//设置modelAttributes,在所有controller方法执行之前
    public String setModel(){
        System.out.println("11111");
        return "yangyu";
    }

    @RequestMapping("/go")
    public String index(Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("msg","你好");
        redirectAttributes.addFlashAttribute("abc","我是重定向过来的哦");
        Map map = new HashMap();
        map.put("high","180");
        return "forward:test"; //后台服务器直接跳转,浏览器发一次请求
        //return map;  //浏览器再次发送请求跳转，浏览器发两次请求
    }

    @ResponseBody
    @RequestMapping("/test")
    public String setModel(Model model, HttpSession httpSession,String para){
        System.out.println("this is para:"+para);
        model.addAttribute("id","18615720100");
        System.out.println(model.asMap().get("name"));
        System.out.println(model.asMap().get("id"));
        model.addAttribute("msg","大家好");
        return "go";
    }

    @RequestMapping("/path/{pathPara}")
    public String doServer(@PathVariable String pathPara){
        System.out.println(pathPara);
        return pathPara;
    }

//    @ResponseBody
//    @RequestMapping(value="/async",produces = "text/plain;charset=UTF-8")
    @RequestMapping(value="/async")
    public WebAsyncTask<String> webAsyncTask(Model model){
        System.out.println("async主线程进入"+Thread.currentThread().getId());
        WebAsyncTask<String> task = new WebAsyncTask<String>(2000,()->{
            Thread.sleep(5*1000);
            System.out.println(Thread.currentThread().getId()+"webAsyncTask处理中....");
            model.addAttribute("msg","async执行了");
            return "go";
        });

        task.onTimeout(()->{
            model.addAttribute("msg","超时了");
            System.out.println(Thread.currentThread().getId()+"超时了");
            return "go";
        });
        System.out.println("async主线程退出");
        return task;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
