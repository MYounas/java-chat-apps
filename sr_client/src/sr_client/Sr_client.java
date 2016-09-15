package sr_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class Sr_client {
public static void main(String[] args) throws IOException {
    Scanner key=new Scanner(System.in);
    InetAddress RemoteIpAddress=InetAddress.getLocalHost();
    System.out.println("client "+RemoteIpAddress);
    InetSocketAddress serverSockAddress=new InetSocketAddress(RemoteIpAddress,4444);
//    InetSocketAddress serverSockAddress=new InetSocketAddress("127.0.0.01",4444);
    System.out.println(serverSockAddress.getPort());
    Socket clientSock=new Socket();
    clientSock.connect(serverSockAddress);
    System.out.println("chek it out: "+clientSock.getRemoteSocketAddress());
    String message="";DataOutputStream output;DataInputStream input;
    do{
    output=new DataOutputStream(clientSock.getOutputStream());
    System.out.println("Write my client:");
    message=key.nextLine();
    System.out.println("Sendin gtata: "+message);
    output.writeUTF(message);
    input=new DataInputStream(clientSock.getInputStream());
    message=input.readUTF();
    System.out.println("data received: "+message);
    }
    while(!message.equals("end"));
    input.close();
    output.close();
    clientSock.close();
    }
}
