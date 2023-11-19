import java.net.*;
import java.io.*;
import java.util.*;
class server {
    public static void main(String[] args) {
       try {
         System.out.println(InetAddress.getLocalHost().getHostAddress());
         ServerSocket server = new ServerSocket(12);
         Socket serverSocket = server.accept();
         Scanner input = new Scanner(System.in);
         OutputStream out = serverSocket.getOutputStream();
        
         
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
         
         
         while (true) {
            
            
            String inputText = input.nextLine();
            bufferedWriter.write(inputText);

            System.out.println("SEVER: " + inputText);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            
            
         }
         

       } catch (Exception e) {
        // TODO: handle exception
       }
    }
}