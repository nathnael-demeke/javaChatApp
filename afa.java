import java.net.*;
class n {
    public static void main (String a[]){
        try {
            InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostAddress());
        } catch (Exception e){

        }
    }
}