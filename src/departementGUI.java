import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
    private JButton OWLoginButton;
    private JButton OWLogoutButton;
    private JPanel OWInfo;
    private JLabel OWLabelInfo;
    private JLabel UserHelloLabel;
    private JTextField userNameInput;
    private JTextField userSurnameInput;
    private JTextField userEmailInput;
    private JTextField userPhoneNumberInput;
    private JComboBox departmentsBox;
    private JComboBox OWBox;
    private JTextField dateInput;
    private JTextField hourInput;
    private JButton umówWizytęButton;
    //Admin instance - there is only one admin
    Admin admin = new Admin(1,"Zyta", "Guzek", "zg@gmail.com", "111222333", "idk", "admin123");

    //Office Workers
    Department d1 = new Department("ZUS", "Izdebki", "21-37");
    Department d2 = new Department("KRUS", "ChujCieTo", "22-37");
    OfficeWorker OW1 = new OfficeWorker(1, "Pawel", "Nedved", "pn@gmail.com", "222333444", 2,4500, "dupa1", d1);
    OfficeWorker OW2 = new OfficeWorker(2, "Jan", "Nedved", "jn@gmail.com", "222333544", 2,4600, "dupa2", d2);
    ArrayList<OfficeWorker> listOfOW = new ArrayList<OfficeWorker>(Arrays.asList(OW1, OW2));

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
        UserHelloLabel.setFont(new Font("Serif", Font.PLAIN, 32));

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

        //Office worker login
        OWLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = OWNameInput.getText();
                String userPassword = String.valueOf(OWPasswordInput.getPassword());
                boolean isLogged = false;

                for(OfficeWorker OW : listOfOW) {
                    if (OW.name.equals(userName) && OW.password.equals(userPassword)) {
                        isLogged = true;
                        OWInfo.setVisible(true);
                        OWLogoutButton.setEnabled(true);
                        OWLoginButton.setEnabled(false);
                        OWLabelInfo.setText("Witaj " + OW.name);
                        break;
                    }
                }
                if(!isLogged){
                    JOptionPane.showMessageDialog(null,"Błędne dane!");
                }
            }
        });
        OWLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OWNameInput.setText("");
                OWPasswordInput.setText("");
                OWInfo.setVisible(false);
                OWLogoutButton.setEnabled(false);
                OWLoginButton.setEnabled(true);
            }
        });
    }
}
