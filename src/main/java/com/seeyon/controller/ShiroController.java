package com.seeyon.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * shiro还可以使用注解的方式来判断方法的执行权限，详见org.apache.shiro.authz.annotation包下的注解,比如@RequiresUser
 * Created by yangyu on 2017/2/15.
 */
@Controller
@SessionAttributes("loginTime")
public class ShiroController {

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(Model model){
        return "没有权限！";
    }

    @RequestMapping("/space/normal/normal")
    @ResponseBody
    public String normal(Model model){
        return "只有normal角色可以访问";
    }

    @RequestMapping("/space/other/other")
    @ResponseBody
    public String other(Model model){
        return "other";
    }

    @RequestMapping("/space/all/all")
    @ResponseBody
    public String all(Model model){
        return "只有all权限才可以访问";
    }

    @RequestMapping("/space/view/view")
    @ResponseBody
    public String view(Model model){
        return "只有view权限才可以访问";
    }

    @RequestMapping("/space/logout/logout")
    @ResponseBody
    public String logout(Model model){
        SecurityUtils.getSubject().logout();
        return "成功注销用户";
    }

    @RequestMapping("space/logout/invalidate")
    @ResponseBody
    public String invalidate(Model model){
        Map map = model.asMap();
        long loginTime = (long) map.get("loginTime");
        long currentTime = System.currentTimeMillis();
        if ((currentTime-loginTime)/1000>100){
            SecurityUtils.getSubject().logout();
            return "超时请重新登录！";
        }
        return "还未超时，可以继续使用！";
    }


    @RequestMapping("/logined/{name}/{password}")
    @ResponseBody
    public String logined(@PathVariable String name,@PathVariable String password,Model model){
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        try {
            subject.login(token);
        }catch (AuthenticationException e){
            return "登录失败";
        }

        model.addAttribute("loginTime",System.currentTimeMillis());
        return "登录成功";
    }
}
