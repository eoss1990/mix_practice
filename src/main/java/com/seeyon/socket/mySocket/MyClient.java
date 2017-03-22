package com.seeyon.socket.mySocket;

import com.seeyon.mybatis.pojo.People;
import com.seeyon.socket.Client;
import sun.misc.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * Created by yangyu on 16/10/19.
 */
public class MyClient {
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        Socket client = null;
        try {
            People p = new People("2","yangyu","4","5","6");
            oos = new ObjectOutputStream(bos = new ByteArrayOutputStream());
            oos.writeObject(p);
            byte[] bytes = bos.toByteArray();

            client = new Socket("127.0.0.1",20007);
            client.setSoTimeout(10000);

            client.getOutputStream().write(bytes);
            byte[] bytes1 = IOUtils.readFully(client.getInputStream(),-1,false);

            System.out.println(new String(bytes1));
            System.out.println(bytes1.length);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
                bos.close();
                System.out.println(client.isClosed());
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
