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
    int port=12;
    
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
//        txt_area.setText("noiii");
//        txt_area.setText("jgh");
    }
    
    public void actionPerformed(ActionEvent e) {}
    
    //recieve msgs
    public void read_msg() throws Exception{
        System.out.println("hi cleint read");
//        ip=InetAddress.getLocalHost();
        
        pack=new DatagramPacket(buf, 1024);
        System.out.println("end of client read");
        System.out.println("haha phs gya opr "+msg);
//        try{
//            con.setSoTimeout(5000);
//            con.receive(pack);
//        }
//        catch(Exception err)
//        {
//            System.out.println("time out "+err.getMessage());
//        }
        con.receive(pack);
//        con.receive(pack);
        msg=new String(pack.getData(),0,pack.getLength());
        System.out.println("client received bad "+msg);
//        System.out.println("clent class read "+msg);
        
        
        if(msg.contains(".txt")){
            
            String[] spliting_msg=msg.split(".txt");
            String rec_file_name=spliting_msg[0];
            String rec_file_data=spliting_msg[1];
            
            File file=new File("C:\\he"+rec_file_name+".txt");
        file.createNewFile();
        FileWriter fw=new FileWriter(file);
        fw.write(rec_file_data);
        fw.flush();
            
            txt_area.setText(txt_area.getText().trim()+"\nfile saved in c drive name is he"+rec_file_name);
        }
        else{
                txt_area.setText(txt_area.getText().trim()+"\n Server: "+msg);
        }
        
        ip=pack.getAddress();
        port=pack.getPort();
//        pack=null;
//        System.out.println("end of client read");
    }
    
    
    //send to server
    public void write_msg() throws Exception{
        System.out.println("client write");
        pack=new DatagramPacket(txt_msg.getText().getBytes(),txt_msg.getText().length(),ip,port);
        
//        File file=new File("C:\\Users\\M\\Desktop\\Test2.txt");
//        FileReader fr=new FileReader(file);
//        FileReader fr=new FileReader("C:\\Users\\M\\Desktop\\Test2.txt");
//        FileReader fr=new FileReader(txt_msg.getText());
//        String falto=txt_msg.getText().trim();
//        String[] file_name=falto.split("\\\\");
//        System.out.println("total path "+falto);
//        System.out.println("file name "+file_name[file_name.length-1]);
//        
//        char[] meri=new char[500];
//        fr.read(meri);
////        char v;
//        String you="";
//        you=String.valueOf(meri);
//        System.out.println("bhj de "+you);
////        System.out.println("gkh"+meri[0]);
//        pack=new DatagramPacket(you.getBytes(),you.toString().length(),ip,port);
//        pack=new DatagramPacket(file_name[file_name.length-1].getBytes(),file_name[file_name.length-1].length(),ip,port);
//        con.send(pack);
//        FileReader fr=new FileReader(txt_msg.getText());
//        char[] meri=new char[500];
//        fr.read(meri);
//        String you=file_name[file_name.length-1];
//        you+=String.valueOf(meri);
//        System.out.println("heer uis ou "+you);
//        pack=new DatagramPacket(you.getBytes(),you.toString().length(),ip,port);
        con.send(pack);
//        con.send(pack);
//        pack=null;
//        System.out.println("haha phs gya "+msg);
    }
    
    
//            public void run() {
//                try {
//                    read_msg();
//                } catch (Exception ex) {
//                    System.out.println("cleint rea ruiuable error "+ ex.getMessage());
//                }
//            }
    
    public void file_sending()
    {
        try{
            String falto=txt_msg.getText().trim();
        String[] snd_file_name=falto.split("\\\\");
        
        FileReader fr=new FileReader(txt_msg.getText());
        char[] snd_file_data=new char[500];
        fr.read(snd_file_data);
        String data_with_name=snd_file_name[snd_file_name.length-1];
        data_with_name+=String.valueOf(snd_file_data);
        System.out.println("yh hai msg by client "+data_with_name);
        
        pack=new DatagramPacket(data_with_name.getBytes(),data_with_name.toString().length(),ip,port);
        con.send(pack);
//        con.send(pack);
//        pack=null;
        }
        catch(Exception e){
            System.out.println("file sendning ka exception "+e.getMessage());
        }
        
    }
    
}
