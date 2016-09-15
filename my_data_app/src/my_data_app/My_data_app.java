package my_data_app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane.*;

public class My_data_app {

    public static void main(String[] args) throws Exception {

        app_client client = new app_client("Client");
//        app_server server=new app_server();
        JFrame login = new JFrame("LogIn");
        JButton btn_log = new JButton("LogIn");

        login.setVisible(true);
        login.setLayout(new FlowLayout());
        JTextField username = new JTextField(20);
        username.setEditable(true);
        JTextField ip = new JTextField(20);
        ip.setEditable(true);

        username.setSize(50, 30);
        ip.setSize(50, 30);
        login.setSize(300, 150);

        login.add(username);
        login.add(ip);
        login.add(btn_log);

        btn_log.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (username.getText().equals("") && ip.getText().equals("")) {
                        login.setVisible(false);
                        client.setVisible(true);
                    } else
                        JOptionPane.showMessageDialog(btn_log, "Input mismatch");
                }
            }
        );
        
//        client.read_msg();
        
        
//        new Runnable() {
//            public void run() {
//                System.out.println("younas");
//                try {
//                    client.read_msg();
//                } catch (Exception ex) {
//                    System.out.println("runnable ka error"+ex.getMessage());
//                }
//            }
//        };
        
//        while (!client.txt_msg.getText().equals("end")) {

            client.btn_send.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(!client.txt_msg.getText().equals("end")){
                            System.out.println("click call");
                            if(client.txt_msg.getText().contains(".txt")){
                                System.out.println("hn bhai file hai");
                                client.file_sending();
                            }
                                else{
                                    client.write_msg();
                                }
                            }
                            else if(client.txt_msg.getText().equals("end")){
                                client.con.close();
//                                server.con.close();
                            }
//                            server.read_msg();
                        } catch (Exception ex) {
                            System.out.println("while me btn_snd "+ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                }
            );
            
//            client.txt_msg.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        try {
//                            client.write_msg();
////                            server.read_msg();
//                        } catch (Exception ex) {
//                            System.out.println("while me txt_snd"+ex.getMessage());
//                        }
//                    }
//                }
//            );
            
            int i=0;
            while(true){
//                System.out.println(i++);
            if(!client.msg.equals("end"))
            {
                client.read_msg();
            }
            else if(client.msg.equals("end"))
            {
//                System.out.println("necajy");
                client.con.close();
                break;
            }
        }

//        }
//        client.con.close();
//        try{
//            server.con.close();
//        }
//        catch(Exception exc){
//            server.con.close();
//        }
        
    }

}