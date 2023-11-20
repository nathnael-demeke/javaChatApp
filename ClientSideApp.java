import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientSideApp {
    public static void main(String[] args) {
        ClientSwing clientSwing = new ClientSwing();
    }
}

class ClientSwing {
    static JFrame window;
    JPanel navJPanel;
    ImageIcon serverProfilePicture;
    JPanel messageJPanel;
    int windowWidth = 490;
    int windowHeight = 680;
   
    public ClientSwing() {
        window = new JFrame();
        serverProfilePicture = new ImageIcon("");
        navJPanel = new JPanel();
        messageJPanel = new JPanel();
        window.setVisible(true);
        window.setLayout(null);




        navJPanel.setBounds(0, 0, windowWidth, 60);
        messageJPanel.setBounds(0, windowHeight - 120, windowWidth, 100);
        navJPanel.setBackground(new Color(18 ,140 ,126));
        messageJPanel.setBackground(Color.green);


        window.add(navJPanel);
        window.add(messageJPanel);
        window.setSize(windowWidth,windowHeight);
        window.setResizable(false);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        window.setTitle("Nati whatsApp");

    }
     

}