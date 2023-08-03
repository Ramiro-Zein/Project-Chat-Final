import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IpScreen extends JFrame{

    private JTextField textField1;
    private JPanel panel1;
    private JButton button1;
    private String host1;

    public IpScreen () {
        JFrame frame = new JFrame();
        frame.setContentPane(panel1);
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setResizable(false);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Agrega la dirección IP");
                } else {
                    host1 = textField1.getText();
                    frame.dispose();
                }
            }
        });
    }

    public String getHost1() {
        return host1;
    }

    public void setHost1(String host1) {
        this.host1 = host1;
    }
}
