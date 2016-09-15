package clienttest;
import javax.swing.*;
public class ClientTest {
    public static void main(String[] args) {
        //192.168.2.101
        //175.107.2.107
        //192.168.0.104
        Client client=new Client("127.0.0.1");
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.startRunning();
    }
}
