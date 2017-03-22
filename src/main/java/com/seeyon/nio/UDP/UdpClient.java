package com.seeyon.nio.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by yangyu on 2017/2/23.
 */
public class UdpClient {

    public static void main(String[] args) {
        try(DatagramSocket datagramSocket = new DatagramSocket()) {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            datagramSocket.connect(inetAddress,8777);

            byte[] bytes = "this is first message".getBytes("UTF-8");
            DatagramPacket request = new DatagramPacket(bytes,bytes.length);
            byte[] bytes1 = "this is second message...........".getBytes("UTF-8");

            DatagramPacket response = new DatagramPacket(new byte[1024],1024);

            datagramSocket.send(request);
            request.setData(bytes1);
            request.setLength(bytes1.length);
            datagramSocket.send(request);
            datagramSocket.receive(response);

            System.out.println(new String(response.getData(),0,response.getLength(),"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
