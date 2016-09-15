package my_server_app;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class app_server extends JFrame implements ActionListener{

    
    InetAddress ip;int port=12;
    byte[] buf=new byte[1024];String msg="";
    JTextField txt_msg=new JTextField(30);
    JTextArea txt_area=new JTextArea("",100, 20);
    JButton btn_send=new JButton("Send");
    
    DataInputStream dis=null;DataOutputStream dos=null;
    FileInputStream fis=null;FileOutputStream fos=null;
    ServerSocket serverSocket=null;
    Socket socket=null;
    
    public app_server(String head) throws Exception{
        
        super(head);
        ip=InetAddress.getLocalHost();
        System.out.println(ip);
        txt_msg.setEditable(true);
        txt_area.setEditable(true);
        setLayout(new FlowLayout());
        setSize(500, 500);
        add(btn_send);
        add(txt_msg);
        add(txt_area);
        add(new JScrollPane(txt_area));
        
    }

    public void actionPerformed(ActionEvent e) { }
    
    //receive msgs
    public void read_msg() throws Exception{
        serverSocket=new ServerSocket(port,10);
//        System.out.println("port ka "+serverSocket.getLocalPort());
        System.out.println("server read strat");
        socket=serverSocket.accept();
        System.out.println("ab nechay");
        port=socket.getPort();
        ip=socket.getInetAddress();
        System.out.println("dnkl "+ip+"df "+port);
        dis=new DataInputStream(socket.getInputStream());
        
//        if(dis.readUTF().contains("<-->"))
//        {
//            System.out.println("great");
//            String rec_file_name="";
//            String[] name_data=dis.readUTF().split("<-->");
//            rec_file_name=name_data[0];
//            fos=new FileOutputStream("C:\\"+rec_file_name);
//            int i=0;
//            
//            dis=new DataInputStream(new ByteArrayInputStream(name_data[1].getBytes()));
//            
//            while((i=dis.read())>-1){
//                System.out.println();
//                fos.write(i);
//                
//            }
//        }
//        else{
            System.out.println("else");
            try{
                txt_area.setText(txt_area.getText()+"\n"+dis.readUTF());
                System.out.println(dis.readUTF());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            
//        }
        
//        socket.close();dis.close();serverSocket.close();
        
    }
    
    
    //send to server
    public void write_msg() throws Exception{
        
    }
    
    
//    public void file_sending()
//    {
//        try{
//            String falto=txt_msg.getText().trim();
//        String[] snd_file_name=falto.split("\\\\");
//        
//        FileReader fr=new FileReader(txt_msg.getText());
//        char[] snd_file_data=new char[500];
//        fr.read(snd_file_data);
//        String data_with_name=snd_file_name[snd_file_name.length-1];
//        data_with_name+=String.valueOf(snd_file_data);
////        System.out.println("heer uis ou "+data_with_name);
////        con.send(pack);
//        }
//        catch(Exception e){
//            System.out.println("file sendning ka exception "+e.getMessage());
//        }
//        
//    }
    
}
