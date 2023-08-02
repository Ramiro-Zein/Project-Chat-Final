import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Login_screen extends JFrame {
    private JPanel principal;
    private JTextField textField1;
    private JButton button1;
    private JButton comenzarButton;
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void log() {

        JFrame frame = new JFrame();
        frame.setContentPane(principal);
        frame.setTitle("Chat");
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        button1.setBorderPainted(false);
        textField1.setBorder(new LineBorder(Color.white, 5));

        frame.setVisible(true);
        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario");
                } else {
                    nombre = textField1.getText();
                    HiloCliente h = new HiloCliente();
                }
            }
        });
    }

}

class Inicio_login extends Thread {
    public Inicio_login() {
        Login_screen ls = new Login_screen();
        ls.log();
    }
}
