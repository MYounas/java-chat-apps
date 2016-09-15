package sr_app;

import java.util.*;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;

public class Sr_app {
public static void main(String[] args) throws IOException {
    Scanner key=new Scanner(System.in);
    InetAddress LocalAddress=InetAddress.getLocalHost();
    InetSocketAddress serverSocketAddress=new InetSocketAddress(LocalAddress,4444);
//    InetSocketAddress serverSocketAddress=new InetSocketAddress("127.0.0.01",4444);
    int concurrent_clients=5;
    ServerSocket serverSocket=new ServerSocket();
    serverSocket.bind(serverSocketAddress,concurrent_clients);
    System.out.println("server ready to listen at : "+ serverSocket.getLocalSocketAddress());
    System.out.println("port "+serverSocket.getInetAddress());
    Socket clientSocket=null;
    clientSocket=serverSocket.accept();
    
    System.out.println("Request received from client: "+clientSocket.getRemoteSocketAddress());
    System.out.println("port "+serverSocket.getLocalPort());
    System.out.println("here is the new "+serverSocket.getLocalSocketAddress());
    
    String message="";DataOutputStream output;DataInputStream input;
    do{
    input=new DataInputStream(clientSocket.getInputStream());
    message=input.readUTF();
    System.out.println("Data recieved: "+message);
    output=new DataOutputStream(clientSocket.getOutputStream());
    System.out.println("write my server:");
    message=key.nextLine();
    System.out.println("sending data:"+message);
    output.writeUTF(message);
    }
    while(!message.equals("end"));
    input.close();
    output.close();
   clientSocket.close();
   serverSocket.close();
    }
}
