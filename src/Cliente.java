import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class Cliente extends Thread {
    private final String usuario;
    public String getUsuario () {
        return usuario;
    }
    private final String ipServidor;
    private final int puerto;
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    public List<String> mensajes = new ArrayList<>();
    private String MessageSever;

    public Cliente(String ipServidor, int puerto, String usuario) {
        this.ipServidor = ipServidor;
        this.puerto = puerto;
        this.usuario = usuario;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ipServidor, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Enviar el nombre de usuario al servidor
            salida.println(usuario);

            // Comenzar el bucle para recibir mensajes del servidor
            recibirMensajes();

        } catch (IOException e) {
            System.out.println("Error al conectar al servidor");
            e.printStackTrace();
        }
    }

    public void recibirMensajes() {
        // Crear un hilo para leer los mensajes del servidor
        Thread messageReaderThread = new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = entrada.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                    mensajes.add(serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        messageReaderThread.start();
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    public void desconectar() {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
