import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloServidor extends Thread {
    public HiloServidor() {
        while (true) {
            try {
                ServerSocket ss = new ServerSocket(6075);
                System.out.println("Esperando...");
                Socket s1 = ss.accept();
                System.out.println("Cliente conectado");
                BufferedReader bf = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                String mensaje = bf.readLine();
                System.out.println("Mensaje: " + mensaje);
                bf.close();
                s1.close();
                ss.close();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }
}
