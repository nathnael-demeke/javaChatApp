import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI {
    public String windowTitle;
    int windowWidth = 490;
    int windowHeight = 680;
    public String profilePictureImagePathString;
    JFrame window;
    JPanel navJPanel;
    ImageIcon serverProfilePicture;
    ImageIcon sendButtIcon;
    JLabel atachmentHolder;
    ImageIcon attachImageIcon;
    ImageIcon threeDotIcon;
    JPanel messageJPanel;
    JTextField messageField;
    JLabel profilePicHolder;
    JLabel arrowIconHolder;
    JLabel sendImageIconHolder;
    JButton navOptionButton;
    ImageIcon backgroundImage;
    
    public GUI() throws IOException {
        window = new JFrame();
        
        windowTitle = "";
        backgroundImage = new ImageIcon("Assets\\backgroundImage.jpeg");
        Image backImage = backgroundImage.getImage();
        sendImageIconHolder = new JLabel();
        sendButtIcon = new ImageIcon("Assets\\send1.png");
        Image sendImage = sendButtIcon.getImage().getScaledInstance(44, 44, Image.SCALE_SMOOTH);
        sendButtIcon = new ImageIcon(sendImage);
        sendImageIconHolder.setIcon(sendButtIcon);
        window.setContentPane(new ImagePanel(backImage));
        atachmentHolder = new JLabel();
        attachImageIcon = new ImageIcon("Assets\\attachment.png");
        messageField = new RoundJTextField(20);
        profilePicHolder = new JLabel();
        navOptionButton = new JButton();
        threeDotIcon = new ImageIcon("Assets\\the_dot.png");
        arrowIconHolder = new JLabel();
        profilePicHolder.setLayout(new FlowLayout());
        messageJPanel = new JPanel();
        messageJPanel.setLayout(null);

        //The method down below will convert the "cliprofilepic.jpeg" image into a circular png image to make it appropriate for profile picture
        navJPanel = new JPanel();
        navJPanel.setLayout(null);
        messageField.setFont(new Font("Arial", Font.PLAIN ,15));

        convertImageToCircular();
        
        ImageIcon backArrowIcon = new ImageIcon("Assets\\arrow.png");
        Image threeDot = threeDotIcon.getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH);
        Image attachemntImage = attachImageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        attachImageIcon = new ImageIcon(attachemntImage);
        Image backArrow = backArrowIcon.getImage();
        threeDotIcon = new ImageIcon(threeDot);
        
        
       
        

        profilePicHolder.setBackground(Color.blue);
        atachmentHolder.setIcon(attachImageIcon);
        navOptionButton.setBackground(null);
        navOptionButton.setBorder(null);
        // messageJPanel.setBackground(Color.black);
        navOptionButton.setIcon(threeDotIcon);
        
        
        
        // Image newBackArrowImage = shrinkImage(backArrow, windowWidth, windowHeight);
        // backArrowIcon = new ImageIcon(newBackArrowImage);
        Image backarrImage = backArrow.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
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
        atachmentHolder.setBounds(4, 2, 60, 60);
        messageField.setBounds(32, 12 , windowWidth - 100 , 40);
        navOptionButton.setBounds(windowWidth - 45, 5, 20 , 50);
        profilePicHolder.setBounds(32, 3,windowWidth - 90,58);
        
        arrowIconHolder.setBounds(4,5,30,10);
        arrowIconHolder.setSize(30,50);
        sendImageIconHolder.setBounds(426,564,50,50);
        
        profilePicHolder.setIconTextGap(15);
        
        messageJPanel.setBounds(0, windowHeight - 120, windowWidth, 100);
        navJPanel.setBackground(new Color(18 ,140 ,126));
        // messageJPanel.setBackground(Color.green);

        messageJPanel.setOpaque(false);
        window.add(navJPanel);
        window.add(sendImageIconHolder);
        
        messageJPanel.add(atachmentHolder);
        
        messageJPanel.add(messageField);
        navJPanel.add(profilePicHolder);
        navJPanel.add(navOptionButton);
        navJPanel.add(arrowIconHolder);
        window.add(messageJPanel);
        window.setSize(windowWidth,windowHeight);
        window.setResizable(false);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        window.setTitle(windowTitle); 
        System.out.println("program begins");
        
    
    //This method is used to convert the image from a rectangular frame to a circular frame
     
     
}

    private void convertImageToCircular() throws IOException{

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
    public void setWindowTitle(String titile) {
        window.setTitle(titile);
    }
    public void setProfilePicture (String imagePath) {
        serverProfilePicture = new ImageIcon(imagePath);
        Image profilePic = serverProfilePicture.getImage();
         Image newProfilePicture = profilePic.getScaledInstance(60, 57, Image.SCALE_SMOOTH);
        serverProfilePicture = new ImageIcon(newProfilePicture);
        profilePicHolder.setIcon(serverProfilePicture);
    }
    public void setProfileName (String name) {
         profilePicHolder.setText(name);
    }

    }

