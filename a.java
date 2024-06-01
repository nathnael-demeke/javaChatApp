import java.net.*;
class nati {
    public static void main (String a[]) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostAddress());
    }
}