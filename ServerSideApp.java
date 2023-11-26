import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class ServerSideApp {

    static boolean isServer;
    static ServerSocket chattingSocket;
    static ServerSocket lenghSocket;
    static Socket chat;
    public static void main(String[] args) throws IOException {
        String serverProfileString = "Hermela Solomon";
        chattingSocket =  new ServerSocket(19);
        ServerSocket reciveClientMessage = new ServerSocket(16);
        lenghSocket = new ServerSocket(1);
        GUI serverGui = new GUI();
        serverGui.connectAsServer(chattingSocket,lenghSocket);
        ServerSocket sendProfilePic = new ServerSocket(53);
        sendProfilePic.setSoTimeout(1000);
        ServerSocket profileNameSocket = new ServerSocket(12);
        serverGui.setProfileName(serverProfileString);
        serverGui.isServer = true;
        serverGui.setWindowTitle("Nati Server WhatsApp");
        


        
        Socket profilSocket = profileNameSocket.accept();
        // chat = chattingSocket.accept();
        OutputStream sendProfileName = profilSocket.getOutputStream();
        Writer writer = new OutputStreamWriter(sendProfileName, "UTF-8");
        writer.write(serverProfileString);
        writer.flush();
        profileNameSocket.close();
        sendProfileName.close();

        Socket pictureSocket = sendProfilePic.accept();
        OutputStream out = pictureSocket.getOutputStream();
        BufferedInputStream read = new BufferedInputStream(new FileInputStream("cliprofilepic.png"));
 
       byte[] bytes = new byte[4096];
       int i;
    
       
       while((i = read.read(bytes)) != -1) {
            out.write(bytes, 0 , i);
       }
       
       pictureSocket.close();
       System.out.println("the Server is ready to chat...");
       int y = serverGui.y;
        while (true) {
           Socket messaging = reciveClientMessage.accept();
           InputStream in = messaging.getInputStream();
           StringBuilder bStringBuilder = new StringBuilder();
           int Input;
           for(Input = in.read(); Input != -1 ; Input = in.read()) {
              
               bStringBuilder.append((char)Input);
           }
           System.out.println("the input is finished " + bStringBuilder);
           
           serverGui.getMessage(" " + bStringBuilder.toString() + " ", serverGui.y);
           serverGui.y += 30;
           
        }
        
    }
   
    // public static void sendMessageThroughSocket(String message, GUI gui, int y, int x) throws IOException {
            
            
    //         // gui.sendMessage(message, y, 1000);
    //         // OutputStream out = chat.getOutputStream();
    //         // Writer write = new OutputStreamWriter(out, "UTF-8");
    //         // write.write(message);
    //         // write.flush();
    //         // write.close();

            
       
    // }

}

