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
        System.out.println("port 19 passed");
        Socket profilePicturSocket = new Socket(serverAddress,53);
        profilePicturSocket.setSoTimeout(100000);
        System.out.println("port 53 passed...");
        Socket profileName = new Socket(serverAddress,12);
        
        InputStreamReader readMessageFromServer = new InputStreamReader(reciveMessageFromServer.getInputStream(), "UTF-8");
        BufferedReader readingMessage = new BufferedReader(readMessageFromServer);
        
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
    
        clientGui.setProfileName(readName.readLine());
        
        clientGui.setProfilePicture("fuck.png");
        System.out.println("working so far line: 51");

        String readString = readingMessage.readLine();
       
        // while (true) {
          
          
        
        System.out.println("the clinet is ready to chat ....");
        System.out.println(readString);
        clientGui.getMessage(readString, 55);
       if (readString != null) {
           clientGui.setProfileName(readString);
       }
        
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

