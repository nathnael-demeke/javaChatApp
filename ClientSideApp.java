import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientSideApp {
    public static void main(String[] args) throws IOException {
        ClientSwing clientSwing = new ClientSwing();
    }
}

class ClientSwing {
    static JFrame window;
    JPanel navJPanel;
    ImageIcon serverProfilePicture;
    JPanel messageJPanel;
    JLabel profilePicHolder;
    int windowWidth = 490;
    int windowHeight = 680;

   
    public ClientSwing() throws IOException {
        window = new JFrame();
        profilePicHolder = new JLabel();
        convertImageToCircular();
        serverProfilePicture = new ImageIcon("cliprofilepic.png");
        Image profilePic = serverProfilePicture.getImage();
        Image newProfilePicture = profilePic.getScaledInstance(60, 57, java.awt.Image.SCALE_AREA_AVERAGING);
        serverProfilePicture = new ImageIcon(newProfilePicture);
        profilePicHolder.setIcon(serverProfilePicture);
        profilePicHolder.setSize(50,45);
        navJPanel = new JPanel();
        navJPanel.setLayout(null);
        
        messageJPanel = new JPanel();
        window.setVisible(true);
        window.setLayout(null);
 
        navJPanel.setBounds(0, 0, windowWidth, 60);
        profilePicHolder.setBounds(32, 3,100,58);
        messageJPanel.setBounds(0, windowHeight - 120, windowWidth, 100);
        navJPanel.setBackground(new Color(18 ,140 ,126));
        // messageJPanel.setBackground(Color.green);


        window.add(navJPanel);
        navJPanel.add(profilePicHolder);
        window.add(messageJPanel);
        window.setSize(windowWidth,windowHeight);
        window.setResizable(false);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        window.setTitle("Nati whatsApp");
        
    }
     void convertImageToCircular() throws IOException {
         
         BufferedImage master = ImageIO.read(new File("cliprofilepic.jpeg"));

        // Get the diameter
        int diameter = Math.min(master.getWidth(), master.getHeight());

        // Create a new image for the result
        BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Prepare the Graphics for drawing
        Graphics2D g2d = mask.createGraphics();
        g2d.fillOval(0, 0, diameter - 1, diameter - 1);
        g2d.dispose();

        // Create the final image
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g2d = circularImage.createGraphics();
        int x = (diameter - master.getWidth()) / 2;
        int y = (diameter - master.getHeight()) / 2;
        g2d.drawImage(master, x, y, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g2d.drawImage(mask, 0, 0, null);
        g2d.dispose();

        // Save the result
        ImageIO.write(circularImage, "PNG", new File("cliprofilepic.png"));
     }

}