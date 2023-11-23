
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerSideApp {
    public static void main(String[] args) throws IOException {
        String serverProfileString = "Hermela Solomon";
        GUI serverGui = new GUI();
        serverGui.getMessage("heel000",100);
        serverGui.getMessage("fuckno",200);
        serverGui.setProfileName(serverProfileString);
        serverGui.setWindowTitle("Nati Server WhatsApp");


        ServerSocket sendProfilePic = new ServerSocket(53);
        ServerSocket profileNameSocket = new ServerSocket(12);
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

        
       byte[] bytes = new byte[4096];
       int i;

       while((i = read.read(bytes)) != -1) {
            out.write(bytes, 0 , i);
       }
        
       pictureSocket.close();
        
        
        
    }
}