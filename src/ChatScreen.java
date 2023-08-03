import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ChatScreen extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panelMensajes;
    private JTextField campoMensaje;
    private JButton botonEnviar;
    private JTextArea areaMensajes;
    private JPanel panelMensaje;
    private Cliente cliente;

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
        // Agregar eventos
        agregarEventos();

        campoMensaje.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !campoMensaje.getText().isEmpty()) {
                    enviarMensaje(campoMensaje.getText());
                    campoMensaje.setText("");
                }
            }
        });
    }

    public interface MensajeListener {
        void onMensajeRecibido(String mensaje);
    }

    private void iniciarCliente(String usuario) {
        cliente = new Cliente("localhost", 6075, usuario);
        cliente.start();
    }

    private void agregarEventos() {
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = campoMensaje.getText();
                if (!mensaje.isEmpty()) {
                    enviarMensaje(campoMensaje.getText());
                    campoMensaje.setText("");
                }
            }
        });

        cliente.setMensajeListener(new MensajeListener() {
            @Override
            public void onMensajeRecibido(String mensaje) {
                SwingUtilities.invokeLater(() -> {
                    areaMensajes.append("- " + mensaje + "\n");
                });
            }
        });
    }

    public void enviarMensaje(String mensaje) {
        cliente.enviarMensaje(mensaje);
    }


}
