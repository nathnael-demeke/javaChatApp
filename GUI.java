import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.MouseListener;
import java.net.UnknownHostException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI implements KeyListener, MouseListener{
    //streams and sockets
    public ServerSocket chattingSocket;
    static Socket sendingAttachmentFiles;
    ServerSocket finishedNotifyServerSocket;
    Socket finiSocket;
    static Socket chat;
    OutputStream out;
    Socket sendMessagSocket16;
    //premitive variables
    public String serverAddress;
    public String windowTitle;
    public String profilePictureImagePathString;
    int windowWidth = 490;
    int windowHeight = 680;
    public int totalMessages;
    boolean isConnected;
    int lengthOfSentMessage;
    public boolean isServer;

    //Javx swing gui components 
    JFrame window;
    JPanel navJPanel;
    JPanel textingPanel;
    JLabel atachmentHolder;
    JPanel messageJPanel;
    JTextField messageField;
    JLabel profilePicHolder;
    JLabel arrowIconHolder;
    JLabel sendImageIconHolder;
    JButton navOptionButton;
    JTextArea messagesArea;
    public int y;
    //Image icons
    ImageIcon sendButtIcon;
    ImageIcon attachImageIcon;
    ImageIcon threeDotIcon;
    ImageIcon backgroundImage;
    ImageIcon serverProfilePicture;
    
    public GUI() throws IOException { 
        window = new JFrame();
        window.setSize(windowWidth,windowHeight);
        window.setVisible(true);
        /*

        textingPanel: is the Scollable panel in which all the message send and recived are found
        sendButton: is the green button down below the textingPanel
        backgroundImage: is the image in the Asset folder that have the background image of WhatsApp
        attachmentHolder: is the jlabel with the attachment icon on the bottom left side of the window(JFrame)
        messageField: is the textField that you wil type your message on
        profilePicHolder: is the profile picture that you will get on the navPanel(Green Navigation Bar)

         */
        isServer = false;
        isConnected = false;
        
        sendImageIconHolder = new JLabel();
        sendButtIcon = new ImageIcon("Assets\\send1.png");
        Image sendImage = sendButtIcon.getImage().getScaledInstance(44, 44, Image.SCALE_SMOOTH);
        sendButtIcon = new ImageIcon(sendImage);
        sendImageIconHolder.setIcon(sendButtIcon);

        textingPanel = new JPanel();
        textingPanel.setLayout(null);
        
        
        
        textingPanel.setVisible(true);

        //setting the background image for the window or the JFrame.
        backgroundImage = new ImageIcon("Assets\\backgroundImage.jpeg");
        Image backImage = backgroundImage.getImage();
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
        navJPanel = new JPanel();
        navJPanel.setLayout(null);
        messageField.setFont(new Font("Arial", Font.PLAIN ,15));

        //This method will convert the rectangular image into circular image for profile picture
        convertImageToCircular();
        
        ImageIcon backArrowIcon = new ImageIcon("Assets\\arrow.png");
        Image threeDot = threeDotIcon.getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH);
        Image attachemntImage = attachImageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        attachImageIcon = new ImageIcon(attachemntImage);
        Image backArrow = backArrowIcon.getImage();
        threeDotIcon = new ImageIcon(threeDot);      
        atachmentHolder.setIcon(attachImageIcon);
        atachmentHolder.addMouseListener(this);
        
       
      
        navOptionButton.setBorder(null);
        // messageJPanel.setBackground(Color.black);
        navOptionButton.setIcon(threeDotIcon);
        //resizing the backarrow image(shrinking)
        Image backarrImage = backArrow.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        arrowIconHolder.setIcon(new ImageIcon(backarrImage));      
        profilePicHolder.setSize(50,45);
    
        navOptionButton.setFocusable(false);
        // profilePicHolder.setText("Abebe Demelash");
        profilePicHolder.setFont(new Font("Arial", Font.BOLD , 20));
        profilePicHolder.setForeground(Color.white);
        
        //Setting bounds and background color
        navOptionButton.setBackground(null);
        navJPanel.setBounds(0, 0, windowWidth, 60);
        textingPanel.setBounds(0,65,windowWidth - 15,500);
        
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
        
        // JScrollPane scrollPane = new JScrollPane();
        
       
        // scrollPane.setVisible(true);
        // textingPanel.setBackground(null);
        textingPanel.setOpaque(false);
        
        window.add(textingPanel);
        
        messageField.addKeyListener(this);
        
        //i have made the window have a fixed size no one can change it while runnin
        window.setResizable(false);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
         
        //null is the layout because i wanted to arrange everything like i wanted   
        window.setLayout(null);
        window.setTitle(windowTitle);    
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
        JTextArea textArea1 = new JTextArea();
        textArea1.setText(messageText);
        textArea1.setFont(new Font("Arial", Font.TYPE1_FONT,17));
        first.setBackground(Color.green);
        // first.setBackground(Color.black);
        first.setOpaque(false);
        textArea1.setEditable(false);
        first.add(textArea1);
        textArea1.setBounds(3, 0 , 170 , 50);
        first.setBounds(0,y,200,50);

        // first.setBounds(windowWidth - 215,100,200,50);
        textingPanel.add(first);
        textingPanel.revalidate();
    } 
    public void sendMessage(String message, int yAxis) {
        JPanel first = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(37, 211, 102));
        textArea.setSize(first.getWidth(), first.getHeight());
        textArea.setText(message);
        textArea.setFont(new Font("Arial", Font.TYPE1_FONT,17));  
        first.setSize(textArea.getWidth(), textArea.getHeight());
        first.setOpaque(false);
        textArea.setEditable(false);
        first.add(textArea);
        first.setBounds(windowWidth - 200, y, 200,50);

        textingPanel.add(first);
        textingPanel.revalidate();
    }
    public String getTextFieldInput() {
         return messageField.getText();
    }
    public void connectAsServer(ServerSocket soc, ServerSocket sendlegnSocket) {
        chattingSocket = soc;
        finishedNotifyServerSocket = sendlegnSocket;
    }





    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
   
  



    @Override
    public void keyPressed(KeyEvent e) {
        
        // TODO Auto-generated method stub
       if(e.getKeyCode() == KeyEvent.VK_ENTER) { 

            System.out.println("the enter button have been pressed");
            String message = messageField.getText();
            System.out.println("the program is running at line:271");
            Thread sendingPort16 = new Thread(new Runnable() {
                OutputStream out;
                @Override
                public void run() {
                  try {
                     sendMessagSocket16 = new Socket(serverAddress , 16);
                     out = sendMessagSocket16.getOutputStream();
                     byte[] bytes = message.getBytes();
                      int liveWordLength = 0;
                     for (byte bite : bytes) {
                        out.write(bite);
                        liveWordLength += 1;
                
                     }
                     out.flush();
                     out.close();
                     System.out.println("writing stream at thread sending port is finished");
                     
                     
                  } catch (Exception e) {
                    System.out.println("there is an error sending this data");
                  }
                }
            });
            Thread connectMessagingPort = new Thread(new Runnable() {
                @Override
                public void run() {
                   if (isConnected == false) {
              try {
               
                chat = chattingSocket.accept();
                isConnected = true;
                finiSocket = finishedNotifyServerSocket.accept();
               
               
              } catch (Exception expect) {
                // TODO: handle exception
              }

            }
            else {

            }
            messageField.setText("");
                }
            });
           

            connectMessagingPort.run();
            try {
                // if the user is a server then it will try to send message from server to client
              
                if (isServer) {
                 
                
                out = chat.getOutputStream();
               
                
                // Writer write = new OutputStreamWriter(outputStream, "UTF-8");
                byte[] bytes = message.getBytes("UTF-8");
                
                System.out.println("the program is running until line:300");
               
                for (byte bite : bytes) {
                    out.write(bite);
                    System.out.println("hey this is " + bite);
                }

                String[] wordStrings = message.split(" ");
                lengthOfSentMessage = 0;   

                for (String word : wordStrings) {
                    lengthOfSentMessage++;
                }
                System.out.println(lengthOfSentMessage);
                OutputStream sendLength = finiSocket.getOutputStream();
               Thread port1Thread = new Thread(new Runnable() {
                @Override
                public void run(){        
                    Writer writeLength;
                    try {
                        writeLength = new OutputStreamWriter(sendLength, "UTF-8");
                        writeLength.write(lengthOfSentMessage);
                        writeLength.flush();
                        
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                    }
                    
                }

               });
               
               Thread port19Thread = new Thread(new Runnable() {
                @Override 
                public void run() {
                    
                    try {
                        out.write(lengthOfSentMessage);
                        out.flush();
                        
                    } catch (IOException e) {
                        System.out.println("GUI.java line 342: " + e.getMessage());
                    }
                 
                }

               });
               
                port1Thread.start();
                port19Thread.start();
                lengthOfSentMessage = 0;
                System.out.println("line 298: the OutputStream is closed");
                sendMessage(" " + message, y);
                System.out.println("this is y " + y); 
                y += 30;
               
               
                
            }
            else {
                sendingPort16.start();
                sendMessage("  " + message + " ", y);
                y += 30;
            }

            } catch (Exception exception) {
                System.out.println("GUI.java error in line 357: " + exception);
            }
        }

    }

     


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == atachmentHolder) {
            
            String currentDir = System.getProperty("user.dir");
             JFileChooser fileChooser = new JFileChooser(currentDir);
             fileChooser.showOpenDialog(null);
           
            if (!isServer) {
               try {
            
                sendingAttachmentFiles = new Socket(serverAddress, 99);
                BufferedReader readingAttachmentFile = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
                OutputStream attachmenStream = sendingAttachmentFiles.getOutputStream();
                int attachReaded;
                for(attachReaded = readingAttachmentFile.read(); attachReaded != -1 ; attachReaded = readingAttachmentFile.read()) {
                    attachmenStream.write(attachReaded);
                    System.out.println(attachReaded);
                }
                attachmenStream.flush();
                System.out.println("finished sending files");
                

            } catch(Exception seningAttachemntException) {
                System.out.println("there is an error while sending attachmemnt files from client");
            }
               
            }
             
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    }

