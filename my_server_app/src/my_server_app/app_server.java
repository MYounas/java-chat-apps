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

    
    DatagramSocket con;DatagramPacket pack;InetAddress ip;int port=12;
    byte[] buf=new byte[1024];String msg="";
    JTextField txt_msg=new JTextField(30);
    JTextArea txt_area=new JTextArea("",100, 20);
    JButton btn_send=new JButton("Send");
    
    public app_server(String head) throws Exception{
        
        super(head);
        ip=InetAddress.getLocalHost();
        System.out.println(ip);
        txt_msg.setEditable(true);
        txt_area.setEditable(true);
        con = new DatagramSocket(port);
//        con = new DatagramSocket();
//        System.out.println("adbgjk "+ip);
//        System.out.println(con.getPort()+" "+con.getInetAddress());
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
        
        System.out.println("server read");
        pack=new DatagramPacket(buf, 1024);
//        con.receive(pack);
        con.receive(pack);
        msg=new String(pack.getData(),0,pack.getLength());
        System.out.println("client send to server file aftr msesgs "+msg);
//        txt_area.setText(txt_area.getText().trim()+"\n Client: "+msg);
//        String rec_file_name=msg.split(".txt")[0];
//        System.out.println("file name "+rec_file_name);
//        System.out.println("rececv file data "+ msg.split(".txt")[1]);
//        String rec_file_data=msg.split(".txt")[1];
        
//        File file=new File("C:\\Users\\M\\\\Desktop\\he"+rec_file_name+".txt");
//        file.createNewFile();
//        FileWriter fw=new FileWriter(file);
//        fw.write(rec_file_data);
//        fw.flush();
        
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
            txt_area.setText(txt_area.getText().trim()+"\n Client: "+msg);
        }
        
//        txt_area.setText("file saved at desktop name is he"+rec_file_name);
        
//        msg="";
        ip=pack.getAddress();
        port=pack.getPort();
//        pack=null;
//        System.out.println("afeter client eo tserver" +ip);
    }
    
    
    //send to server
    public void write_msg() throws Exception{
//        System.out.println("server write");
//        String bhj="kldfld";
        pack=new DatagramPacket(txt_msg.getText().getBytes(),txt_msg.getText().length(),ip,port);
//        pack=new DatagramPacket(bhj.getBytes(),bhj.length(),ip,12);
//        System.out.println("servr nay bhja "+txt_msg.getText());
//        con.send(pack);
        con.send(pack);
//        pack=null;
    }
    
//    public void run() {
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
//        System.out.println("heer uis ou "+data_with_name);
        pack=new DatagramPacket(data_with_name.getBytes(),data_with_name.toString().length(),ip,port);
//        con.send(pack);
        con.send(pack);
        pack=null;
        }
        catch(Exception e){
            System.out.println("file sendning ka exception "+e.getMessage());
        }
        
    }
    
}
