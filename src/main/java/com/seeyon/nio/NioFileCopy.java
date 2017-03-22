package com.seeyon.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yangyu on 16/12/27.
 */
public class NioFileCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/yangyu/Downloads/kaobei.docx");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/yangyu/Downloads/kaobei-copy.docx");
        FileChannel readChannel = fileInputStream.getChannel();
        FileChannel writeChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            buffer.clear();
            int i = readChannel.read(buffer);
            if (i==-1)
                break;
            buffer.flip();
            writeChannel.write(buffer);
        }

        readChannel.close();
        writeChannel.close();
    }

    /**
     * 测试buffer的使用
     */
    @Test
    public void testBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(15);
        for (int i=1;i<=10;i++){
            buffer.put((byte) i);
        }
        buffer.flip();
        for (int i = 0; i < 5; i++) {
            System.out.println(buffer.get());
        }
        buffer.flip();
        for (int i = 0; i < 5; i++) {
            System.out.println(buffer.get());
        }
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        RandomAccessFile file = new RandomAccessFile("/Users/yangyu/Downloads/123456","rw");
        FileChannel fileChannel = file.getChannel();
        MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,file.length());
        while (mbb.hasRemaining()){
            System.out.println((char) mbb.get());
        }
        mbb.clear();
        mbb.putChar('h');

        mbb.flip();
        while (mbb.hasRemaining()){
            System.out.println((char) mbb.get());
        }
        file.close();

    }
}
