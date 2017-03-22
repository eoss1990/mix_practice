package com.seeyon.socket.mySocket;

import com.seeyon.mybatis.pojo.People;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yangyu on 16/10/19.
 */
public class MyServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;

        try {
            server = new ServerSocket(20007);
            client = server.accept();

            byte[] bytes = IOUtils.readFully(client.getInputStream(),-1,false);
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            People people = (People) ois.readObject();
            System.out.println("people name:"+people.getName());

            String res = "消息已经收到";
            client.getOutputStream().write(res.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bis.close();
                ois.close();
                client.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
