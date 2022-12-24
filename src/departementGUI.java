import javax.swing.*;
import java.awt.*;

public class departementGUI extends JFrame {
    private javax.swing.JPanel mainPanel;
    private JTabbedPane rolesTabbedPane;
    private JPanel userPanel;
    private JPanel officeWorkerPanel;
    private JPanel adminPanel;
    private JLabel OWHelloLabel;
    private JPanel OWPanel;
    private JPanel OWLogin;
    private JLabel OWNameLabel;
    private JTextField OWNameInput;
    private JLabel OWPasswordLabel;
    private JPasswordField OWPasswordInput;
    private JLabel AdminHelloLabel;
    private JPanel AdminPanel;
    private JPanel AdminLogin;
    private JTextField AdminNameInput;
    private JPasswordField AdminPasswordInput;
    private JLabel AdminPasswordLabel;
    private JLabel AdminLabelName;

    public static void main(String[] args) {
        departementGUI departementGUIInstance = new departementGUI();
        departementGUIInstance.setVisible(true);
    }

    public departementGUI(){
        super("e-Urząd");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);

        //global styles
        OWHelloLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        AdminHelloLabel.setFont(new Font("Serif", Font.PLAIN, 32));
    }
}
