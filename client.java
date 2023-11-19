import java.net.*;
import java.io.*;
class client {
    public static void main(String[] args) {
       try {
         System.out.println("Trying to connect......");
         Socket client = new Socket("192.168.152.1", 12);
         
         System.out.println("Connection established succesfully......");
         // while ((i = reader.read()) != -1) {
         //    System.out.println(i);
         //    System.out.println((char)i );
            
         //    builder.append((char)i);
         // };
            while (true) {
               int i;
                InputStream in = client.getInputStream();
                Reader reade = new InputStreamReader(in, "UTF-8");
                BufferedReader reader = new BufferedReader(reade);
                String textFromServer = reader.readLine();
                System.out.println("Server: " + textFromServer);
                
            }
      
         
       } catch (Exception e) {
        // TODO: handle exception
       }

       
        
    }
}