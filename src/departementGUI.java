import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton AdminLoginButton;
    private JPanel AdminInfo;
    private JLabel AdminLabelInfo;
    private JButton AdminLogoutButton;
    //Admin instance - there is only one admin
    Admin admin = new Admin(1,"Zyta", "Guzek", "zg@gmail.com", "111222333", "idk", "admin123");

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

        //Admin login
        AdminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = AdminNameInput.getText();
                String userPassword = String.valueOf(AdminPasswordInput.getPassword());

                boolean isNameCorrect = userName.equals(admin.name);
                boolean isPasswordCorrect = userPassword.equals(admin.password);

                if(isNameCorrect && isPasswordCorrect){
                    AdminInfo.setVisible(true);
                    AdminLogoutButton.setEnabled(true);
                    AdminLoginButton.setEnabled(false);
                    AdminLabelInfo.setText("Witaj " + admin.name);
                }else{
                    JOptionPane.showMessageDialog(null,"Błędne dane!");
                }
            }
        });
        AdminLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminNameInput.setText("");
                AdminPasswordInput.setText("");
                AdminInfo.setVisible(false);
                AdminLogoutButton.setEnabled(false);
                AdminLoginButton.setEnabled(true);
            }
        });
    }
}
