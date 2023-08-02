import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat_screen extends JFrame implements Runnable {
    private JPanel principal_chat;
    private JTextField textField1;
    private JButton button1;
    private JTextArea jtextarea;
    private JPanel pane2;
    Login_screen na = new Login_screen();

    public void run() {

        JFrame frame = new JFrame();
        frame.setContentPane(principal_chat);
        frame.setTitle("Chat");
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        button1.setBorderPainted(false);
        jtextarea.setText(na.getNombre());

        frame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un mensaje");
                } else {
                    JTextField n1 = new JTextField();
                    n1.setText(textField1.getText());
                    frame.add(n1);
                }
            }
        });
    }

}

