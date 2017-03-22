package com.seeyon.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yangyu on 16/12/28.
 */

/**
 * 比较Stream流，NIO ByteBuffer，NIO MappedByteBuffer性能对比
 * 其中Stream最慢，NIO MappedByteBuffer最快
 * Stream：1000ms
 * NIO ByteBuffer:220ms
 * NIO MappedByteBuffer:112ms
 */
public class Compare {

    /**
     * 使用stream作为IO流读取和写入文件
     * 速度：1000ms左右
     *
     * @throws IOException
     */
    @Test
    public void useStream() throws IOException {

        long startTime = System.currentTimeMillis();
        /**
         * 4000000个整数长度的文件
         */
        int num = 2000 * 2000;

        /**
         * 带缓冲的输出流，写文件
         */
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/Users/yangyu/Downloads/compare.tmp")));
        for (int i = 0; i < num; i++) {
            dataOutputStream.writeInt(i);
        }
        dataOutputStream.close();

        int data = 0;
        /**
         * 带缓冲的输入流，读文件
         */
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("/Users/yangyu/Downloads/compare.tmp")));
        try {
            while (true) {
                data = in.readInt();
            }
        } catch (EOFException e) {
            System.out.println("读取完成"+data);
        }
        in.close();
        long endTime = System.currentTimeMillis();
        System.out.println("ms:" + (endTime - startTime));
    }

    /**
     * 使用NIO ByteBuffer
     * 时间：220ms
     * @throws IOException
     */
    @Test
    public void useNioByteBuffer() throws IOException {
        long startTime = System.currentTimeMillis();
        int num = 2000*2000;
        /**
         * 文件输出流
         */
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/yangyu/Downloads/compare.tmp");
        /**
         * NIO Channel 通道
         */
        FileChannel fileChannel = fileOutputStream.getChannel();
        /**
         * ByteBuffer缓冲区
         */
        ByteBuffer buffer = ByteBuffer.allocate(num*5);
        for (int i = 0; i < num; i++) {
            buffer.putInt(i);
        }
        /**
         * 为写做准备
         */
        buffer.flip();
        /**
         * 写操作
         */
        fileChannel.write(buffer);
        fileChannel.close();

        /**
         * 缓冲区
         */
        ByteBuffer buffer1 = ByteBuffer.allocate(num*5);
        /**
         * 文件输入流
         */
        FileInputStream in = new FileInputStream("/Users/yangyu/Downloads/compare.tmp");
        /**
         * 输入通道
         */
        FileChannel fin = in.getChannel();
        /**
         * 为读取做准备
         */
        buffer1.clear();
        System.out.println(buffer1.limit());
        /**
         * 读取
         */
        fin.read(buffer1);
        fin.close();

        long endTime = System.currentTimeMillis();
        System.out.println("ms:" + (endTime - startTime));
        buffer1.flip();
        System.out.println(buffer1.limit());
    }

    /**
     * 使用MappedByteBuffer，通过FileChannel将文件映射到内存
     * 时间：112ms
     * @throws IOException
     */
    @Test
    public void useRandomAccess() throws IOException {
        long startTime = System.currentTimeMillis();
        int num = 2000*2000;

        /**
         * 使用可随机访问位置的RandomAccessFile
         */
        RandomAccessFile file = new RandomAccessFile("/Users/yangyu/Downloads/compare.tmp","rw");
        /**
         * 获取通道Channel
         */
        FileChannel fileChannel = file.getChannel();
        /**
         * 将文件映射到缓冲区MappedByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,num*4);
        /**
         * 写文件
         */
        for (int i = 0; i < num; i++) {
            mappedByteBuffer.putInt(i);
        }
        fileChannel.close();

        int data=0;
        RandomAccessFile file1 = new RandomAccessFile("/Users/yangyu/Downloads/compare.tmp","rw");
        FileChannel fc = file1.getChannel();
        MappedByteBuffer mappedByteBuffer1 = fc.map(FileChannel.MapMode.READ_WRITE,0,file1.length());
        /**
         * 读文件
         */
        while (mappedByteBuffer1.hasRemaining()){
            data = mappedByteBuffer1.getInt();
        }
        fc.close();
        long endTime = System.currentTimeMillis();
        System.out.println("ms:" + (endTime - startTime));
        System.out.println(data);
    }
}
