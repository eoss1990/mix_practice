package com.seeyon.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yangyu on 2017/2/27.
 */
public class BigFile {

    private static int fileSize = 1024 * 1024 * 1024;

    private static int bufferSize = 1024*1024*10;

    /**
     * 通过FileChannel写1GB大文件
     */
    @Test
    public void makeFile() {
        byte[] bytes = new byte[fileSize];
        byte b = 1;
        for (int i = 0; i < fileSize; i++) {
            bytes[i] = b;
        }

        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        try (FileChannel fileChannel = new FileOutputStream
                ("/Users/yangyu/Downloads/test").getChannel()) {
            int leftSize = fileSize;
            int position = 0;
            while (leftSize>0){
                buffer.clear();
                if (leftSize<bufferSize)
                    buffer.put(bytes,position,leftSize);
                else
                    buffer.put(bytes,position,bufferSize);
                buffer.flip();
                fileChannel.write(buffer);
                leftSize=leftSize-bufferSize;
                position=position+bufferSize;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过FileChannel读取1GB大文件
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        try(FileChannel fileChannel = new FileInputStream
                ("/Users/yangyu/Downloads/test").getChannel()){
            while (fileChannel.read(buffer)>0){
                buffer.flip();
                //这里将读取到的buffer做其它想要处理的
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void randomAccessFile(){

    }
}
