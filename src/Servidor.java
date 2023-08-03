import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Servidor extends Thread {
    private static final Set<PrintWriter> salidasClientes = new HashSet<>();
    private ServerSocket socketServidor;

    public Servidor() {
        try {
            socketServidor = new ServerSocket(6075);
            System.out.println("=== Servidor iniciado en el puerto 6075 ===");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Si el servidor recibe un mensaje, lo envía a todos los clientes conectados
    private static void enviarMensajeATodos(String message) {
        synchronized (salidasClientes) {
            for (PrintWriter salida : salidasClientes) {
                salida.println(message);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Servidor esperando clientes...");
            try {
                new ManejadorClientes(socketServidor.accept()).start();
                System.out.println("Nuevo cliente conectado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ManejadorClientes extends Thread {
        private final Socket socketCliente;

        public ManejadorClientes(Socket socketCliente) {
            this.socketCliente = socketCliente;
        }

        @Override
        public void run() {
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
                synchronized (salidasClientes) {
                    salidasClientes.add(salida);
                }

                while (true) {
                    String mensaje = entrada.readLine();
                    // Si el mensaje es 'bye', salir del bucle
                    if (mensaje.equalsIgnoreCase("bye")) {
                        break;
                    }
                    // Enviar el mensaje a todos los demás clientes
                    enviarMensajeATodos(mensaje);
                }

                // Cerrar la conexión con el cliente
                socketCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
