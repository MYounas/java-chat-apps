package chat;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame
{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    
    public Server()
    {
        super("messenger");
        userText=new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event)
                    {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        add(userText,BorderLayout.NORTH);
        chatWindow=new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300,150);
        setVisible(true);
    }
    
    public void startRunning()
    {
        try
        {
            server=new ServerSocket(8080,100);
            while(true)
            {
                try
                {
                    waitForConnection();
                    setupStreams();
                    whileChating();
                }
                catch(EOFException eofException)
                {
                    showMessage("\nServer Connection ends the connections");
                }
                finally
                {
                    closeCrap();
                }
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }  
    }
    
    private void waitForConnection() throws IOException
    {
        showMessage("\nWaiting for someone to connect...\n");
        connection=server.accept();
        showMessage("Now connected to "+connection.getInetAddress().getHostName());
         
    }
    
    private void setupStreams() throws IOException
    {
        output=new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input =new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are setup now! \n");
    }
    
    private void whileChating() throws IOException
    {
        String message="You are nwo connected!";
        sendMessage(message);
        ableToType(true);
        do
        {
            try
            {
                message=(String)input.readObject();
                showMessage("\n"+message);
            }
            catch(ClassNotFoundException classNotFoundException)
            {
                showMessage("\n ky send kia hai bhai");
            }
        }
        while(!message.equals("CLIENT - END"));
    }
    
    private void closeCrap()
    {
        showMessage("\n Closing connections....");
        ableToType(false);
        try
        {
            output.close();
            input.close();
            connection.close();
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    private void sendMessage(String message)
    {
        try
        {
            output.writeObject("SERVER -"+message);
            output.flush();
            showMessage("\nSERVER -"+message);
        }
        catch(IOException ioException)
        {
            chatWindow.append("\n ERROR: meesage ni snd kra ja rha");
        }
    }
    
    private void showMessage(final String text)
    {
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        chatWindow.append(text);
                    }    
                }
        );
    }
    
     private void ableToType(final boolean tof){
         SwingUtilities.invokeLater(
                 new Runnable() {
             public void run() {
                 userText.setEditable(tof);
             }
         }
         );
     }
    
}








































