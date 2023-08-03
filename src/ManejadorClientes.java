import java.io.*;
import java.net.*;

class ManejadorClientes extends Thread {
    private final Socket socketCliente;
    private final BufferedReader entrada;
    private final PrintWriter salida;

    public ManejadorClientes(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;
        this.entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        this.salida = new PrintWriter(socketCliente.getOutputStream(), true);
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    @Override
    public void run() {
        try {
            String userName = entrada.readLine();
            System.out.println("Usuario '" + userName + "' conectado");

            // Enviar mensaje de bienvenida al cliente
            salida.println("¡Bienvenido, " + userName + "!");
            salida.println("Escribe 'bye' para salir.");

            while (true) {
                // Leer el mensaje del cliente
                String mensaje = entrada.readLine();
                // Si el mensaje es nulo o es 'bye', salir del bucle
                if (mensaje == null || mensaje.equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.println(userName + ": " + mensaje);

                // Enviar el mensaje a todos los demás clientes
                salida.println(userName + ": " + mensaje);
            }

            // Cerrar la conexión con el cliente
            System.out.println("Usuario '" + userName + "' desconectado");
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}