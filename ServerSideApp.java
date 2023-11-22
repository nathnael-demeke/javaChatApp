
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.FileReader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;


public class ServerSideApp {
    public static void main(String[] args) throws IOException {
        GUI serverGui = new GUI();
        

        serverGui.setWindowTitle("Nati Server WhatsApp");
        ServerSocket sendProfilePic = new ServerSocket(53);
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