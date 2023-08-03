import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Servidor extends Thread {
    private final List<ManejadorClientes> clientes = new ArrayList<>();
    private ServerSocket serverSocket;
    private boolean estaCorriendo = true;

    public Servidor() {
        arrancar();
    }

    public void arrancar() {
        try {
            serverSocket = new ServerSocket(6075);
            System.out.println("=== Servidor iniciado en el puerto 6075 ===");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(String mensaje) {
        for (ManejadorClientes cliente : clientes) {
            cliente.enviarMensaje(mensaje);
        }
    }

    public void detener() {
        estaCorriendo = false;
        try {
            serverSocket.close();
            System.out.println("Servidor detenido");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (estaCorriendo) {
            System.out.println("Esperando clientes...");
            try {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");
                // Crear un nuevo hilo para manejar al cliente
                ManejadorClientes manejadorClientes = new ManejadorClientes(socketCliente);
                // Agregar el nuevo hilo a la lista de clientes
                clientes.add(manejadorClientes);
                manejadorClientes.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
