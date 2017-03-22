package com.seeyon.socket;

import com.seeyon.mybatis.pojo.People;

import java.io.*;

/**
 * Created by yangyu on 16/10/19.
 */
public class TestStream {
    public static void main(String[] args) {
        People people = new People("1","2","3","4","5");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayInputStream bis = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(people);

            byte[] data = bos.toByteArray();
            String s = new String(data);
            System.out.println(s);

            bis = new ByteArrayInputStream(s.getBytes("UTF-8"));
            ois = new ObjectInputStream(bis);
            People p = (People) ois.readObject();
            System.out.println(p.getName());

        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
                System.out.println("已关闭流");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
