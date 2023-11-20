import javax.swing.JFrame;

public class ClientSwing {
    static JFrame window;
   
    public ClientSwing() {
        window = new JFrame();
        window.setVisible(true);

        window.setSize(1080,1920);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setTitle("Nati whatsApp");

    }
     

}