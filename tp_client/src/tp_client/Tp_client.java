package tp_client;

import java.net.*;
import java.io.*;
import java.util.*;

public class Tp_client {
    public static void main(String[] args) throws Exception {

        String ip = "127.0.0.1";
        int port = 34;

        InetSocketAddress inetSocketAddress;
        DataInputStream input = null;
        DataOutputStream output = null;
        Socket con;
        String msg = "";
        Scanner key = new Scanner(System.in);


        //    inetSocketAddress=new InetSocketAddress(ip,port);
        //    con=new Socket();
        //    con.connect(inetSocketAddress);
        con = new Socket(ip, port);
        //    System.out.println("Connected to "+inetSocketAddress.getAddress()+":"+inetSocketAddress.getPort());
        //    System.out.println("Connected to "+con.getRemoteSocketAddress());

        while (!msg.equals("end")) {
            output = new DataOutputStream(con.getOutputStream());
            System.out.println("Write msg to server:");
            msg = key.nextLine();
            output.writeUTF(msg);
            output.flush();
            input = new DataInputStream(con.getInputStream());
            System.out.println("Server reply: " + input.readUTF());
        }
        output.close();
        input.close();
        con.close();
    }
}