import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
    private JTextField OWPeselInput;
    private JLabel OWPasswordLabel;
    private JPasswordField OWPasswordInput;
    private JLabel AdminHelloLabel;
    private JPanel AdminPanel;
    private JPanel AdminLogin;
    private JTextField AdminPeselInput;
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
    private JButton visitButton;
    private JTable OWListOfVisits;
    private JTable adminListOfVisits;


    public static void main(String[] args) {
        departementGUI departementGUIInstance = new departementGUI();
        departementGUIInstance.setVisible(true);
    }

    DB db = new DB();
    Admin admin = db.getAdmin();
    ArrayList<OfficeWorker> listOfOW = db.getListOfOW();
    ArrayList<Visit> listOfVisits = db.getListOfVisits();
    ArrayList<Department> listOfDepartments = db.getListOfDepartments();

    public departementGUI(){
        super("e-Urząd");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);


        setUpBasicView();

        //Admin login
        AdminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pesel = AdminPeselInput.getText();
                String password = String.valueOf(AdminPasswordInput.getPassword());

                boolean isPeselCorrect = pesel.equals(admin.PESEL);
                boolean isPasswordCorrect = password.equals(admin.password);

                if(isPeselCorrect && isPasswordCorrect){
                    loginHandler("Admin", null);
                }else{
                    JOptionPane.showMessageDialog(null,"Błędne dane!");
                }
            }
        });
        //Admin logout
        AdminLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutHandler("Admin");
            }
        });

        //Office worker login
        OWLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pesel = OWPeselInput.getText();
                String password = String.valueOf(OWPasswordInput.getPassword());
                boolean isLogged = false;

                for(OfficeWorker OW : listOfOW) {
                    if (OW.PESEL.equals(pesel) && OW.password.equals(password)) {
                        isLogged = true;
                        loginHandler("OW", OW);
                        break;
                    }
                }
                if(!isLogged){
                    JOptionPane.showMessageDialog(null,"Błędne dane!");
                }
            }
        });
        //Office worker logout
        OWLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutHandler("OW");
            }
        });
        departmentsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentDepartment = departmentsBox.getSelectedItem().toString();

                OWBox.removeAllItems();
                OWBox.addItem("");
                for (OfficeWorker ow:listOfOW) {
                    if(ow.department.name.equals(currentDepartment)){
                        OWBox.addItem(ow.name + " " + ow.surname);
                    }
                }
            }
        });
        visitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visit newVisit = createNewVisit();

                listOfVisits.add(newVisit);
                JOptionPane.showMessageDialog(null, "Wizyta została umówiona");
            }
        });
    }

    private void setUpBasicView(){
        //global styles
        OWHelloLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        AdminHelloLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        UserHelloLabel.setFont(new Font("Serif", Font.PLAIN, 32));

        //Basic combobox setup
        departmentsBox.addItem("");
        for (Department d:listOfDepartments) {
            departmentsBox.addItem(d.name);
        }
    }

    private void logoutHandler(String role){
        if(role.equals("OW")){
            OWPeselInput.setText("");
            OWPasswordInput.setText("");
            OWInfo.setVisible(false);
            OWLogoutButton.setEnabled(false);
            OWLoginButton.setEnabled(true);
        }else if(role.equals("Admin")){
            AdminPeselInput.setText("");
            AdminPasswordInput.setText("");
            AdminInfo.setVisible(false);
            AdminLogoutButton.setEnabled(false);
            AdminLoginButton.setEnabled(true);
        } else {
            throw new IllegalArgumentException("Invalid role!");
        }
    }

    private void loginHandler(String role, OfficeWorker OW){
        if(role.equals("OW")){
            if(OW == null) throw new IllegalArgumentException("You need to pass an extra params!");
            OWInfo.setVisible(true);
            OWLogoutButton.setEnabled(true);
            OWLoginButton.setEnabled(false);
            OWLabelInfo.setText("Witaj " + OW.name + ", oto lista Twoich wizyt");

            DefaultTableModel dtm = new DefaultTableModel(0, 0);
            String header[] = new String[] { "Imie", "Nazwisko", "Pesel", "E-mail", "Numer tel.", "Data", "godzina" };
            dtm.setColumnIdentifiers(header);
            OWListOfVisits.setModel(dtm);

            for (Visit v:listOfVisits) {
                if(v.officeWorker.PESEL.equals(OW.PESEL)){
                    dtm.addRow(new Object[]{v.user.name, v.user.surname, v.user.PESEL, v.user.email, v.user.phoneNumber, v.date, v.hour});
                }
            }
        }else if(role.equals("Admin")){
            AdminInfo.setVisible(true);
            AdminLogoutButton.setEnabled(true);
            AdminLoginButton.setEnabled(false);
            AdminLabelInfo.setText("Witaj " + admin.name + ", oto lista wszystkich wizyt");

            DefaultTableModel dtm = new DefaultTableModel(0, 0);
            String header[] = new String[] { "Imie", "Nazwisko", "Pesel", "E-mail", "Numer tel.", "Data", "godzina", "urzędnik", "wydział" };
            dtm.setColumnIdentifiers(header);
            adminListOfVisits.setModel(dtm);

            for (Visit v:listOfVisits) {
                dtm.addRow(new Object[]{v.user.name, v.user.surname,v.user.PESEL ,v.user.email, v.user.phoneNumber, v.date, v.hour, v.officeWorker.name+" "+v.officeWorker.surname, v.officeWorker.department.name});
            }
        }else {
            throw new IllegalArgumentException("Invalid role!");
        }
    }

    private Visit createNewVisit(){
        //get data from inputs
        String userName = userNameInput.getText();
        String userSurname = userSurnameInput.getText();
        String userEmail = userEmailInput.getText();
        String userPhone= userPhoneNumberInput.getText();
        String dateOfVisit = dateInput.getText();
        String hourOfVisit = hourInput.getText();
        String currentOWName = OWBox.getSelectedItem().toString();

        //prepare data for Visit constructor
        Random r = new Random();
        long randomPesel = r.nextInt(1_000_000_000) + (r.nextInt(90) + 10) * 1_000_000_000L;
        User newUser = new User(Long.toString(randomPesel), userName, userSurname, userEmail, userPhone);
        OfficeWorker currentOW = null;
        for (OfficeWorker ow:listOfOW) {
            if(ow.name.equals(currentOWName.split(" ")[0]) && ow.surname.equals(currentOWName.split(" ")[1])){
                currentOW = ow;
            }
        }

        return new Visit(newUser, currentOW, dateOfVisit, hourOfVisit);
    }
}
