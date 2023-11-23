import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI {
    public String windowTitle;
    int windowWidth = 490;
    int windowHeight = 680;
    public int totalMessages;
    public String profilePictureImagePathString;
    JFrame window;
    JPanel navJPanel;
    ImageIcon serverProfilePicture;
    JScrollPane textingPanel;
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
    JTextArea messagesArea;
    
    public GUI() throws IOException {
        window = new JFrame();
        textingPanel = new JScrollPane();
        textingPanel.setLayout(null);
        textingPanel.setVisible(true);
        backgroundImage = new ImageIcon("Assets\\backgroundImage.jpeg");
        Image backImage = backgroundImage.getImage();
        sendImageIconHolder = new JLabel();
        sendButtIcon = new ImageIcon("Assets\\send1.png");
        Image sendImage = sendButtIcon.getImage().getScaledInstance(44, 44, Image.SCALE_SMOOTH);
        sendButtIcon = new ImageIcon(sendImage);
        sendImageIconHolder.setIcon(sendButtIcon);

        //setting the background image for the window or the JFrame.
        window.setContentPane(new ImagePanel(backImage));
        atachmentHolder = new JLabel();
        attachImageIcon = new ImageIcon("Assets\\attachment.png");
        messageField = new RoundJTextField(20);
        profilePicHolder = new JLabel();
        navOptionButton = new JButton();
        threeDotIcon = new ImageIcon("Assets\\the_dot.png");
        arrowIconHolder = new JLabel();
        //FlowLayout allow me to put the profile picture and name side by side
        profilePicHolder.setLayout(new FlowLayout());
        messageJPanel = new JPanel();
        //setting the profile picture layout to be null so that i can arrang the icons as I want
        messageJPanel.setLayout(null);
        navJPanel = new JPanel();
        navJPanel.setLayout(null);
        //Enlarging the text inside of the messaging tab
        messageField.setFont(new Font("Arial", Font.PLAIN ,15));
        
        //This method will convert the rectangular image into circular image
        convertImageToCircular();
        
        ImageIcon backArrowIcon = new ImageIcon("Assets\\arrow.png");
        Image threeDot = threeDotIcon.getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH);
        Image attachemntImage = attachImageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        attachImageIcon = new ImageIcon(attachemntImage);
        Image backArrow = backArrowIcon.getImage();
        threeDotIcon = new ImageIcon(threeDot);
        
        
       
        
       
        
        atachmentHolder.setIcon(attachImageIcon);
        textingPanel.setOpaque(false);
        navOptionButton.setBackground(null);
        navOptionButton.setBorder(null);
        // messageJPanel.setBackground(Color.black);
        navOptionButton.setIcon(threeDotIcon);

        //resizing the backarrow image(shrinking)
        Image backarrImage = backArrow.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        arrowIconHolder.setIcon(new ImageIcon(backarrImage));
        System.out.println(backArrowIcon); 
        profilePicHolder.setSize(50,45);
        textingPanel.setSize(windowWidth, 200);



       


        navOptionButton.setFocusable(false);
        // profilePicHolder.setText("Abebe Demelash");
        profilePicHolder.setFont(new Font("Arial", Font.BOLD , 20));
        profilePicHolder.setForeground(Color.white);
        navJPanel.setBounds(0, 0, windowWidth, 60);
        textingPanel.setBounds(0,65,windowWidth - 15,500);
        textingPanel.setBackground(null);
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
        // will set the background color of the bottom nav bar to transparent
        messageJPanel.setOpaque(false);
        window.add(navJPanel);
        window.add(sendImageIconHolder);
        
        messageJPanel.add(atachmentHolder);
        
        messageJPanel.add(messageField);
        navJPanel.add(profilePicHolder);
        navJPanel.add(navOptionButton);
        navJPanel.add(arrowIconHolder);
        window.add(messageJPanel);
        window.add(textingPanel);
        window.setSize(windowWidth,windowHeight);

        
        //i have made the window have a fixed size no one can change it while runnin
        window.setResizable(false);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
         window.setVisible(true);
        //null is the layout because i wanted to arrange everything like i wanted   
        window.setLayout(null);
        
        window.setTitle(windowTitle); 
        System.out.println("program begins");
        
    
    //This method is used to convert the image from a rectangular frame to a circular frame
     
     
}





    //convert image to a circular png image
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
    //used to set the title of the JFrame
    public void setWindowTitle(String titile) {
        window.setTitle(titile);
    }

    //used to set the profilePicture of the window or JFrame
    public void setProfilePicture (String imagePath) {
        serverProfilePicture = new ImageIcon(imagePath);
        Image profilePic = serverProfilePicture.getImage();
         Image newProfilePicture = profilePic.getScaledInstance(60, 57, Image.SCALE_SMOOTH);
        serverProfilePicture = new ImageIcon(newProfilePicture);
        profilePicHolder.setIcon(serverProfilePicture);
    }
    //this method will set the profile name of the other user
    public void setProfileName (String name) {
         profilePicHolder.setText(name);
    }
    public void getMessage(String messageText, int y) {
        JPanel first = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setSize(first.getWidth(), first.getHeight());
        textArea.setText(messageText);
        textArea.setFont(new Font("Arial", Font.TYPE1_FONT,16));
        first.setSize(textArea.getWidth(), textArea.getHeight());
        first.setBackground(Color.black);
        first.add(textArea);
        first.setBounds(windowWidth - 215,y,200,50);

        textingPanel.add(first);

    } 
    public void sendMessage(String message, int y) {
         JPanel first = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setSize(first.getWidth(), first.getHeight());
        textArea.setText(message);
        textArea.setFont(new Font("Arial", Font.TYPE1_FONT,16));
        first.setSize(textArea.getWidth(), textArea.getHeight());
        first.setBackground(Color.black);
        first.add(textArea);
        first.setBounds(12,y,200,50);

        textingPanel.add(first);
    }
    public String getTextFieldInput() {
         return messageField.getText();
    }

    }

