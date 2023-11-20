import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JButton;

public class ClientSideApp {
    public static void main(String[] args) throws IOException {
        ClientSwing clientSwing = new ClientSwing();
    }
}

class ClientSwing {
    static JFrame window;
    JPanel navJPanel;
    ImageIcon serverProfilePicture;
    ImageIcon threeDotIcon;
    JPanel messageJPanel;
    JLabel profilePicHolder;
    JLabel arrowIconHolder;
    JButton navOptionButton;
    int windowWidth = 490;
    int windowHeight = 680;

   
    public ClientSwing() throws IOException {
        window = new JFrame();
        profilePicHolder = new JLabel("Abebe Demelash");
        navOptionButton = new JButton();
        threeDotIcon = new ImageIcon("Assets\\the_dot.png");
        arrowIconHolder = new JLabel();
        profilePicHolder.setLayout(new FlowLayout());
        messageJPanel = new JPanel();

        //The method down below will convert the "cliprofilepic.jpeg" image into a circular png image to make it appropriate for profile picture
        navJPanel = new JPanel();
        navJPanel.setLayout(null);

        convertImageToCircular();
        serverProfilePicture = new ImageIcon("Assets\\cliprofilepic.png");
        
        Image profilePic = serverProfilePicture.getImage();
        ImageIcon backArrowIcon = new ImageIcon("Assets\\arrow.png");
        Image threeDot = threeDotIcon.getImage().getScaledInstance(25, 30, java.awt.Image.SCALE_DEFAULT);
        Image backArrow = backArrowIcon.getImage();
        threeDotIcon = new ImageIcon(threeDot);
        
        
        Image newProfilePicture = profilePic.getScaledInstance(60, 57, java.awt.Image.SCALE_AREA_AVERAGING);
        serverProfilePicture = new ImageIcon(newProfilePicture);
        profilePicHolder.setIcon(serverProfilePicture);
        profilePicHolder.setBackground(Color.blue);
        navOptionButton.setBackground(null);
        navOptionButton.setBorder(null);
        
        navOptionButton.setIcon(threeDotIcon);
        
        
        // Image newBackArrowImage = shrinkImage(backArrow, windowWidth, windowHeight);
        // backArrowIcon = new ImageIcon(newBackArrowImage);
        Image backarrImage = backArrow.getScaledInstance(20, 20, java.awt.Image.SCALE_AREA_AVERAGING);
        arrowIconHolder.setIcon(new ImageIcon(backarrImage));
        
        
        System.out.println(backArrowIcon);
        
        
        profilePicHolder.setSize(50,45);
        
        

        

        window.setVisible(true);
        window.setLayout(null);
        navOptionButton.setFocusable(false);
        
        // profilePicHolder.setText("Abebe Demelash");
        profilePicHolder.setFont(new Font("Arial", Font.BOLD , 20));
        profilePicHolder.setForeground(Color.white);
        
        System.out.println(profilePicHolder.getText());

        navJPanel.setBounds(0, 0, windowWidth, 60);
        navOptionButton.setBounds(windowWidth - 45, 5, 20 , 50);
        profilePicHolder.setBounds(32, 3,windowWidth - 90,58);
        
        arrowIconHolder.setBounds(4,5,30,10);
        arrowIconHolder.setSize(30,50);
        profilePicHolder.setIconTextGap(15);
        
        messageJPanel.setBounds(0, windowHeight - 120, windowWidth, 100);
        navJPanel.setBackground(new Color(18 ,140 ,126));
        // messageJPanel.setBackground(Color.green);

      
        window.add(navJPanel);
        navJPanel.add(profilePicHolder);
        navJPanel.add(navOptionButton);
        navJPanel.add(arrowIconHolder);
        window.add(messageJPanel);
        window.setSize(windowWidth,windowHeight);
        window.setResizable(false);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        window.setTitle("Nati whatsApp");
        
    }
    //This method is used to convert the image from a rectangular frame to a circular frame
     void convertImageToCircular() throws IOException {
         
         BufferedImage master = ImageIO.read(new File("Assets\\profilepic.jpeg"));

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

        // Now we are saving the completely converted iamge into directory mentioned below as a png format 
        ImageIO.write(circularImage, "PNG", new File("Assets\\cliprofilepic.png"));
     }
     
}