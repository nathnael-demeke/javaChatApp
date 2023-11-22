
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ClientSideApp {
    public static void main(String[] args) throws IOException {
       Thread downloading = new Thread();
        Socket profilePicturSocket = new Socket("localhost",53);
        
        InputStream in = profilePicturSocket.getInputStream();
        if(new File("fuck.png").exists()) {
            System.out.println("there is already fuck.png");
            
        }
        else {
            
            Files.copy(in, Paths.get("fuck.png"));  
        }

        GUI clientGui = new GUI();
         clientGui.setProfilePicture("fuck.png");
        
        
        
        


       
    }
}

