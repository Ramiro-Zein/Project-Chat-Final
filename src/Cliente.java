import java.io.*;
import java.net.*;

public class HiloCliente {
    LoginScreen c = new LoginScreen();

    public HiloCliente() {
        try {
            Socket s1 = new Socket("localhost", 6075);
            PrintWriter w = new PrintWriter(s1.getOutputStream(), true);
            w.println("Hello world " + c.getNombre());
            w.close();
            s1.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
