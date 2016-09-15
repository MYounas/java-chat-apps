package data_server;

import java.net.*;
import java.util.*;

public class Data_server {
    public static void main(String[] args) throws Exception {
        int port = 1234;
        Scanner key = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket(port);
        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf;
        String str = "";
        String msg = "";
        try {
            while (!msg.equals("end")) {
                buf = new byte[1024];
                DatagramPacket dp2 = new DatagramPacket(buf, 1024);
                ds.receive(dp2);
                str = new String(dp2.getData(), 0, dp2.getLength());
                ip = dp2.getAddress();
                port = dp2.getPort();
                System.out.println("Client :" + str);
                System.out.println("Write message to client:");
                msg = key.nextLine();
                DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), ip, port);
                ds.send(dp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ds.close();

    }
}