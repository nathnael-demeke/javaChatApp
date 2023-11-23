
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerSideApp {
    static ServerSocket chattingSocket;
    static Socket chattin;
    public static void main(String[] args) throws IOException {
        String serverProfileString = "Hermela Solomon";
        
        GUI serverGui = new GUI();
         serverGui.getMessage("heel000",100);
        serverGui.getMessage("fuckno",200);
        serverGui.setProfileName(serverProfileString);
        serverGui.setWindowTitle("Nati Server WhatsApp");
        


        ServerSocket sendProfilePic = new ServerSocket(53);
        ServerSocket profileNameSocket = new ServerSocket(12);
        chattingSocket =  new ServerSocket(19);
        chattin = chattingSocket.accept();
        
        Socket profilSocket = profileNameSocket.accept();

        OutputStream sendProfileName = profilSocket.getOutputStream();
        Writer writer = new OutputStreamWriter(sendProfileName, "UTF-8");
        writer.write(serverProfileString);
        writer.flush();
        profileNameSocket.close();
        sendProfileName.close();

        Socket pictureSocket = sendProfilePic.accept();
        OutputStream out = pictureSocket.getOutputStream();
        BufferedInputStream read = new BufferedInputStream(new FileInputStream("cliprofilepic.png"));

        

        
        
        

       sendMessageThroughSocket("I dont like gay people", serverGui ,12);
       
        
       byte[] bytes = new byte[4096];
       int i;

       while((i = read.read(bytes)) != -1) {
            out.write(bytes, 0 , i);
       }
        
       pictureSocket.close();
        
        
        
    }
    public static void sendMessageThroughSocket(String message, GUI gui, int y) throws IOException {
        gui.sendMessage(message, y);
        OutputStream out = chattin.getOutputStream();
        Writer write = new OutputStreamWriter(out, "UTF-8");
        write.write(message);
        write.flush();
        
    }
}

