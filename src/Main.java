import javax.swing.*;

public class Main extends Thread {
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.start();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen();
            }
        });
    }
}
