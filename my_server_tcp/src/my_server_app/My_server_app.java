package my_server_app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane.*;
public class My_server_app {

    public static void main(String[] args) throws Exception {

        app_server server = new app_server("Server");
//        app_client client=new app_client();
        server.setVisible(true);
        
//        server.write_msg();
        
        
//        server.read_msg();
        
//         new Runnable() {
//            public void run() {
//                System.out.println("younas");
//                try {
//                    server.read_msg();
//                } catch (Exception ex) {
//                    System.out.println("runnable ka error"+ex.getMessage());
//                }
//            }
//        };

//        server.read_msg();
        
//        while (!server.txt_msg.getText().equals("end")) {

//            server.read_msg();

//            server.btn_send.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        try {
//                            if(!server.txt_msg.getText().equals("end"))
//                            {
//                                if(server.txt_msg.getText().contains(".txt")){
//                                    server.file_sending();
//                                }
//                                else{
//                                        server.write_msg();
//                                }
//                                
//                            }
//                            else if(server.txt_msg.getText().equals("end"))
//                            {
//                                server.con.close();
//                            }
////                            client.read_msg();
////                            JOptionPane.showMessageDialog(server.btn_send,"helo");
//                        } catch (Exception ex) {
//                            System.out.println(ex.getMessage());
//                        }
//                    }
//                }

//            );
            
//            server.txt_msg.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        try{
//                        if(!server.txt_msg.getText().equals("end"))
//                            {
//                                server.write_msg();
//                            }
//                            else if(server.txt_msg.getText().equals("end"))
//                            {
//                                server.con.close();
//                            }
//                        }
//                        catch (Exception ex) {
//                            System.out.println(ex.getMessage());
//                        }
//                    }
//                }
//
//            );

//        }
        
        while(true){
            if(!server.msg.equals("end"))
            {
                server.read_msg();
            }
            else if(server.msg.equals("end"))
            {
//                server.con.close();
                break;
            }
        }
        
//        server.con.close();
//        try{
//            client.con.close();
//        }
//        catch(Exception exc){
////            client.con.close();
//        }
        
    }

}