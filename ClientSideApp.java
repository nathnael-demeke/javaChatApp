import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ClientSideApp {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        Socket profilePicturSocket = new Socket(serverAddress,53);
        Socket profileName = new Socket(serverAddress,12);
        Socket reciveMessageFromServer = new Socket(serverAddress, 19);
        reciveMessageFromServer.setSoTimeout(10000);
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
        clientGui.getMessage(readingMessage.readLine(), 10);
        
         clientGui.setProfilePicture("fuck.png");
        
        
        
        


       
    }
}

