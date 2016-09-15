package data_client;

import java.net.*;
import java.util.*;

public class Data_client {
    public static void main(String[] args) throws Exception {
        int port = 1234;
        Scanner key = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        byte[] buf;
        InetAddress ip = InetAddress.getLocalHost();
        String str = "", ip2 = "127.0.0.1";
        String msg = "";
        try {
            while (!str.equals("end")) {
                buf = new byte[1024];
                System.out.println("Write msg to server:");
                str = key.nextLine();
                DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, port);
                ds.send(dp);
                DatagramPacket dp2 = new DatagramPacket(buf, 1024);
                ds.receive(dp2);
                msg = new String(dp2.getData(), 0, dp2.getLength());
                System.out.println("Server: " + msg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ds.close();
    }
}