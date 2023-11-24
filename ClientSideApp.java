import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ClientSideApp {
    static boolean isServer;
    static Socket clienSocket;
    static int i;
    public static void main(String[] args) throws IOException, InterruptedException {
        String serverAddress = "localhost";
        Socket reciveMessageFromServer = new Socket(serverAddress, 19);
        Socket profilePicturSocket = new Socket(serverAddress,53);
        profilePicturSocket.setSoTimeout(100000);
        Socket profileName = new Socket(serverAddress,12);
        
        InputStreamReader readMessageFromServer = new InputStreamReader(reciveMessageFromServer.getInputStream(), "UTF-8");
        
        InputStream recieveProfile =  profileName.getInputStream();
        Reader reader = new InputStreamReader(recieveProfile, "UTF-8");
        BufferedReader readName = new BufferedReader(reader);

        
        InputStream in = profilePicturSocket.getInputStream();
        
        if(new File("fuck.png").exists()) {
            System.out.println("there is already fuck.png");
        }
        else {
            
            Files.copy(in, Paths.get("fuck.png"));  
        }

        GUI clientGui = new GUI();
    
        clientGui.setProfileName("NATI");
        
        clientGui.setProfilePicture("fuck.png");
        

        // String readString = readingMessage.readLine();
       
        // while (true) {
        System.out.println("the clinet is ready to chat ....");
    
        
    //    if () {
    //       
    //    }
    String actualMessage = "";
    int i;
    byte[] bytes = new byte[1024];
    StringBuilder stringBuilder = new StringBuilder();
    
        while ((i = readMessageFromServer.read()) != -1) {
            stringBuilder.append((char)i);
            System.out.println("hey " + i);
            System.out.println("this is " + stringBuilder.toString());
        }
        System.out.println(stringBuilder.toString());
        clientGui.getMessage(stringBuilder.toString(),15);
        System.out.println("finished");

        
        // }
        


       
    }



    //  public static void sendMessageThroughSocket(String message, GUI gui, int y, int x) throws IOException {
       
        
    //         clienSocket = new Socket("localhost", 19);
    //         OutputStream out =  clienSocket.getOutputStream();
    //         Writer write = new OutputStreamWriter(out, "UTF-8");
    //         write.write(message);
    //         write.flush();
    //         write.close();

        
        
    // }
}

