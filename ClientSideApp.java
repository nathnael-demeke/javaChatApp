import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
// import java.io.OutputStreamWriter;
// import java.io.OutputStream;
// import java.io.Writer;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.net.ServerSocket;


public class ClientSideApp {
    static boolean isServer;
    static Socket clienSocket;
    // static Socket sendingAttachmentFiles;
    static int i;
    public static void main(String[] args) throws IOException, InterruptedException {
        String serverAddress = "192.168.152.1";
        // String serverAddress = "localhost";

        Socket reciveMessageFromServer = new Socket(serverAddress, 19);
        System.out.println("port 19: passed");
        Socket finishedClinSocket = new Socket(serverAddress, 1);
        System.out.println("port 1: passed ...");
        Socket profilePicturSocket = new Socket(serverAddress,53);
        System.out.println("port 53: passed ...");
        Socket profileName = new Socket(serverAddress,12);
        
        
        InputStreamReader readMessageFromServer = new InputStreamReader(reciveMessageFromServer.getInputStream(), "UTF-8");
        
        InputStream recieveProfile =  profileName.getInputStream();
        // Reader reader = new InputStreamReader(recieveProfile, "UTF-8");
        // BufferedReader readName = new BufferedReader(reader);

        
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
    int i = 0;
    
    InputStream lengthOfWord = finishedClinSocket.getInputStream();
    int recivedLength = lengthOfWord.read();
    // Reader reader2 = new InputStreamReader(lengthOfWord, "UTF-8");
    // BufferedReader lBufferedReader = new BufferedReader(reader2);
    // String read = lBufferedReader.readLine();
    List<Integer> recivedBytes = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
    Integer recivedBytesLastIndex = 0;
    
       while (true) {
        
         while ((i = readMessageFromServer.read()) != -1) {
           
            stringBuilder.append((char)i);
            recivedBytes.add(i);
            // System.out.println("hey " + i);
            // System.out.println(recivedBytes);         
            if (recivedBytes.size() != 0 ) {
                 int notifyFinished = recivedBytes.size() - 1;
                //  System.out.println(recivedBytes.get(notifyFinished));
                 var theLasInteger  = recivedBytes.get(notifyFinished);
                 
                 if (theLasInteger == recivedLength) {
                    recivedBytesLastIndex = theLasInteger;
                    break;
                 }
            }
        }
       
        int index = stringBuilder.length() - 1;
        stringBuilder.deleteCharAt(index);
        clientGui.getMessage("  " + new String(stringBuilder) + "   ", clientGui.y);
        System.out.println("finished");
        recivedBytes = new ArrayList<>();
        stringBuilder = new StringBuilder();
       
        
        clientGui.y += 30;
       }

        
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

