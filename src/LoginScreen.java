import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class LoginScreen extends JFrame {
    private JPanel panelPrincipal;
    private JTextField textoUsuario;
    private JButton botonUsuario;
    private JButton botonIniciar;
    private String nombreusuario;

    public LoginScreen() {
        JFrame frame = new JFrame();
        frame.setContentPane(panelPrincipal);
        frame.setTitle("Chat");
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        botonUsuario.setBorderPainted(false);
        textoUsuario.setBorder(new LineBorder(Color.white, 5));
        frame.setVisible(true);

        agregarEventos();
        textoUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !textoUsuario.getText().isEmpty()) {
                    iniciarChat(textoUsuario.getText());
                }
            }
        });
    }

    public void iniciarChat(String usuario) {
        this.dispose();
        new ChatScreen(usuario);
    }

    public void agregarEventos() {
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textoUsuario.getText();
                if (usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario");
                } else {
                    iniciarChat(usuario);
                }
            }
        });
    }
}
