package com.seeyon.io;

import org.junit.Test;
import sun.misc.IOUtils;

import java.io.*;

/**
 * Created by yangyu on 16/12/5.
 */
public class TestIO {
    public static void main(String[] args) throws Exception {

        /**
         * FileInputStream用法
         */
        FileInputStream fileInputStream = new FileInputStream("/Users/yangyu/Downloads/myclass");
        byte[] data = IOUtils.readFully(fileInputStream,-1,false);
        System.out.println(new String(data));

        /**
         * ByteArrayInputStream用法
         */
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        byte[] data1 = IOUtils.readFully(byteArrayInputStream,-1,false);
        System.out.println(new String(data1));

    }

    /**
     * ObjectOutputStream用法
     * ObjectInputStream用法
     * @throws IOException
     */
    @Test
    public void TestObjectStream() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(new Peo("yangyu"));
        byte[] bytes = byteOutputStream.toByteArray();
        System.out.println(bytes.length);

        ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream1);
        Peo p = (Peo) objectInputStream.readObject();
        System.out.println(p.name);

        byteArrayInputStream1.close();
        objectInputStream.close();
        byteOutputStream.close();
        objectOutputStream.close();
    }

    @Test
    public void TestBufferStream() throws IOException {
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("/Users/yangyu/Downloads/myclass"));
//        ByteOutputStream bos = new ByteOutputStream();
//        BufferedOutputStream os = new BufferedOutputStream(bos);
//        byte[] tmp = new byte[1];
//        while (bufferedInputStream.read(tmp)!=-1){
//            os.write(tmp);
//        }
//        os.flush();
//        System.out.println(new String(bos.getBytes()));
//        bufferedInputStream.close();
//        os.close();
//        bos.close();
    }

    private static class Peo implements Serializable{

        public String name;

        public Peo(String name){
            this.name = name;
        }

        /**
         * 自定义序列化，在调用ObjectOutputStream进行writeObject时候会检测对象是否含有名称为"writeObject"，参数为ObjectOutputStream的方法
         * 如果有则会调用该方法来实现自定义序列化
         * @param stream
         * @throws IOException
         */
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            System.out.println("自定义writeObject");
        }

        /**
         * 自定义序列化，在调用ObjectInputStream进行readObject时候会检测对象是否含有名称为"readObject"，参数为ObjectInputStream的方法
         * 如果有则会调用该方法来实现自定义序列化
         * @param stream
         * @throws IOException
         */
        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            System.out.println("自定义readObject");
        }
    }

    @Test
    public void tst(){
        System.out.println(System.getProperty("user.dir"));
    }
}
