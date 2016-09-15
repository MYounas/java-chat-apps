package my_data_app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.net.*;
import java.io.*;


public class app_client extends JFrame{

    
    DatagramSocket con;DatagramPacket pack;InetAddress ip;
    byte[] buf=new byte[1024];String msg="";
    DataOutputStream dos=null;DataInputStream dis=null;
        int port=12;
        Socket socket=null;
        FileInputStream fis=null;
        FileOutputStream fout=null;
    
    JTextField txt_msg=new JTextField(30);
    JTextArea txt_area=new JTextArea(100,35);
    JScrollBar s_bar=new JScrollBar();
    JButton btn_send=new JButton("Send");
    
    public app_client(String head) throws Exception {
        super(head);
        
        ip = InetAddress.getLocalHost();
        txt_msg.setEditable(true);
        txt_area.setEditable(true);
        System.out.println("ffd"+ip);
        con=new DatagramSocket();
        setLayout(new FlowLayout());
        setSize(500, 500);
        add(btn_send);
        add(txt_msg);
        add(txt_area);
        add(new JScrollPane(txt_area));
    }
    
    public void actionPerformed(ActionEvent e) {}
    
    //recieve msgs
    public void read_msg() throws Exception{
           
    }
    
    //send to server
    public void write_msg() throws Exception{
        
       socket=new Socket(InetAddress.getLocalHost(),port);
        System.out.println("ip="+socket.getInetAddress()+"port="+socket.getPort());
        dos=new DataOutputStream(socket.getOutputStream());
        System.out.println(txt_msg.getText());
        dos.writeUTF(txt_msg.getText());
//        socket.close();
//        dos.close();
        
    }
    
    public void file_sending()
    {
        try{
            socket=new Socket(InetAddress.getLocalHost(),port);
        String[] snd_file_path=txt_msg.getText().split("\\\\");
        String snd_file_name=snd_file_path[snd_file_path.length-1];
        
        fis=new FileInputStream(txt_msg.getText());
        
        dos=new DataOutputStream(socket.getOutputStream());
        dos.write(snd_file_name.getBytes());
        dos.write(new String("<-->").getBytes());
        
        int i=0;
        while((i=fis.read())>-1){
            dos.write(i);
        }
        
            System.out.println("finaly "+dos);
        
        }
        catch(Exception e){
            System.out.println("file sendning ka exception "+e.getMessage());
        }
        
    }
    
}
