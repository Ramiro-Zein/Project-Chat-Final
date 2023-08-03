import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends Thread {
    private Socket socket;
    private String usuario;
    private BufferedReader entrada;
    private PrintWriter salida;
    private ChatScreen.MensajeListener mensajeListener;

    public Cliente(String ipServidor, int puerto, String usuario) {
        try {
            this.socket = new Socket(ipServidor, puerto);
            this.usuario = usuario;
            // La entrada es para leer los mensajes del servidor
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // La salida es para enviar mensajes al servidor
            salida = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("No se pudo encontrar el host: " + ipServidor);
        } catch (IOException e) {
            System.err.println("No se pudo obtener I/O para la conexión con: " + ipServidor);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensaje = entrada.readLine();
                if (mensaje.equalsIgnoreCase("bye")) {
                    break;
                }
                if (mensajeListener != null) {
                    mensajeListener.onMensajeRecibido(mensaje);
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(String mensaje) {
        salida.println(usuario + ": " + mensaje);
        System.out.println("Usuario '" + usuario + "' envio mensaje: " + mensaje);
    }

    public void enviarNombre(String mensaje) {
        salida.println(usuario + ": " + mensaje);
        System.out.println("Usuario '" + usuario + "' envio mensaje: " + mensaje);
    }

    public void setMensajeListener(ChatScreen.MensajeListener listener) {
        this.mensajeListener = listener;
    }
}
