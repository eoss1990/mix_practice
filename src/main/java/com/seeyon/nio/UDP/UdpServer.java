package com.seeyon.nio.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by yangyu on 2017/2/23.
 */
public class UdpServer {

    public static void main(String[] args) {
        try(DatagramSocket datagramSocket = new DatagramSocket(8777)) {
            DatagramPacket request = new DatagramPacket(new byte[1024],1024);
            System.out.println(request.getLength());

            while (true){
                datagramSocket.receive(request);
                System.out.println(request.getLength());

                System.out.println(request.getSocketAddress());
                System.out.println(request.getAddress()+":"+request.getPort());
                System.out.println(new String(request.getData(),0,request.getLength(),"UTF-8"));

                byte[] bytes = "你好，我是服务器".getBytes("UTF-8");
                DatagramPacket response = new DatagramPacket(bytes,bytes.length,request.getAddress(),request.getPort());
                datagramSocket.send(response);

                /**
                 * Java网络编程（第四版）上说这里需要重设packet大小，因为接收第一个数据报以后会将DatagramPacket中buffer大小设置为第一个数据报的大小
                 *
                 * 其实并不是这样，在JDK1.8中，DatagramPacket中的length只是接收到的数据报的length，而其中还含有一个bufferLength
                 * 而bufferLength才是缓冲区大小，根据源码所得而且在接收数据报时，只是更改了DatagramPacket中的length而并没有更改
                 * bufferLength，所以并不会影响下一个数据报的接收；
                 *
                 * 所以数据报能否接收完整，跟第一个数据报大小无关，而跟DatagramPacket中buffer大小有关
                 */
                //request.setLength(1024);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
