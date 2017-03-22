package com.seeyon.springmvc.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu on 16/11/8.
 */
public class WorkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.setHeader("Cache-Control","private");
        resp.setHeader("Pragma","no-cache");

        final PrintWriter writer = resp.getWriter();
        writer.println("老板检查当前需要做的工作");
        writer.flush();

        List<String> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jobs.add("job"+i);
        }
        final AsyncContext ac = req.startAsync();
        ac.addListener(new BossListener());
        ac.addListener(new leaderListener());
        doWork(ac,jobs);
        writer.println("老板检查完工作就走了");
        writer.flush();

    }

    private void doWork(AsyncContext ac,List<String> jobs){
        ac.setTimeout(1*60*60*1000);
        ac.start(()->{
            try {
                PrintWriter write = ac.getResponse().getWriter();
                for (String job : jobs){
                    write.println(job+"请求处理中........");
                    Thread.sleep(1000);
                    write.flush();
                }
                ac.complete();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
