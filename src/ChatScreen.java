import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatScreen extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panelMensajes;
    private JTextField campoMensaje;
    private JButton botonEnviar;
    private JTextArea areaMensajes;
    private JPanel panelMensaje;
    private Cliente cliente;
    private String userName;

    private String mensajeAll;

    public ChatScreen(String usuario) {
        // Inicializar la interfaz gráfica
        JFrame frame = new JFrame();
        frame.setContentPane(panelPrincipal);
        frame.setTitle("Chat " + usuario);
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setResizable(false);

        // Inicializar el cliente
        iniciarCliente(usuario);
        // Agregar los eventos
        agregarEventos();

    }

    private void iniciarCliente(String usuario) {
        cliente = new Cliente("localhost", 6075, usuario);
        cliente.start();
        userName = cliente.getUsuario();
    }

    private void agregarEventos() {
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoMensaje = campoMensaje.getText();
                if (!textoMensaje.isEmpty()) {
                    enviarMensaje(textoMensaje);
                    campoMensaje.setText("");
                    recibirMensajes();
                    mensajeAll = textoMensaje;
                    areaMensajes.append(userName + ": " + textoMensaje + "\n");
                }
            }
        });
    }

    public void enviarMensaje(String textoMensaje) {
        cliente.enviarMensaje(textoMensaje);
    }

    public void recibirMensajes() {
        List<String> mensajes = cliente.mensajes;
        cliente.mensajes.clear();
        for (String mensaje : mensajes) {
            areaMensajes.append(mensaje + "\n");
        }
    }
}
