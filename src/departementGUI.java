import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.setSize(800, 600);
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
                String errorMessage = "";

                //get data from inputs
                String userName = userNameInput.getText();
                String userSurname = userSurnameInput.getText();
                String userEmail = userEmailInput.getText();
                String userPhone= userPhoneNumberInput.getText();
                String dateOfVisit = dateInput.getText();
                String hourOfVisit = hourInput.getText();
                Object currentOW = OWBox.getSelectedItem();
                String currentDepartment = departmentsBox.getSelectedItem().toString();
                String currentOWName="";

                boolean isNameValid = validName(userName);
                boolean isSurnameValid = validName(userSurname);
                boolean isEmailValid = validEmail(userEmail);
                boolean isPhoneValid = phoneNumber(userPhone);
                boolean areComboboxesValid = false;
                boolean isDateValid = false;
                try {
                    isDateValid = validDate(dateOfVisit);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                boolean isHourValid = validHour(hourOfVisit);

                if(!isNameValid) errorMessage+="Niedozwolone znaki w imieniu\n";
                if(!isSurnameValid) errorMessage+="Niedozwolone znaki w nazwisku\n";
                if(!isEmailValid) errorMessage+="Błędny format email\n";
                if(!isPhoneValid) errorMessage+="Błędny format nr. telefonu\n";
                if(currentOW!=null && !currentOW.toString().equals("") && !currentDepartment.equals("")){
                    currentOWName = currentOW.toString();
                    areComboboxesValid=true;
                }else{
                    errorMessage += "Musisz wybrać wydział i urzędnika\n";
                }
                if(!isDateValid) errorMessage+="Błędny format daty\n";
                if(!isHourValid) errorMessage+="Błędny format godziny\n";



                if(!isNameValid || !isSurnameValid || !isEmailValid || !isPhoneValid || !areComboboxesValid || !isDateValid || !isHourValid){
                    JOptionPane.showMessageDialog(null, errorMessage);
                    return;
                }


                Visit newVisit = createNewVisit(userName, userSurname, userEmail,userPhone, dateOfVisit, hourOfVisit, currentOWName);

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

            createTable("OW", new String[] { "Imie", "Nazwisko", "Pesel", "E-mail", "Numer tel.", "Data", "godzina" }, OW);

        }else if(role.equals("Admin")){
            AdminInfo.setVisible(true);
            AdminLogoutButton.setEnabled(true);
            AdminLoginButton.setEnabled(false);
            AdminLabelInfo.setText("Witaj " + admin.name + ", oto lista wszystkich wizyt");

            createTable("Admin", new String[] { "Imie", "Nazwisko", "Pesel", "E-mail", "Numer tel.", "Data", "godzina", "urzędnik", "wydział" }, null);
        }else {
            throw new IllegalArgumentException("Invalid role!");
        }
    }

    private Visit createNewVisit(String userName, String userSurname, String userEmail, String userPhone, String dateOfVisit, String hourOfVisit, String currentOWName){

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

    private void createTable(String role, String[] headers, OfficeWorker OW ){
        if(role.equals("OW")){
            if(OW == null) throw new IllegalArgumentException("You need to pass an extra params!");
            DefaultTableModel dtm = new DefaultTableModel(0, 0);
            dtm.setColumnIdentifiers(headers);
            OWListOfVisits.setModel(dtm);

            for (Visit v:listOfVisits) {
                if(v.officeWorker.PESEL.equals(OW.PESEL)){
                    dtm.addRow(new Object[]{v.user.name, v.user.surname, v.user.PESEL, v.user.email, v.user.phoneNumber, v.date, v.hour});
                }
            }
        }else if(role.equals("Admin")){
            DefaultTableModel dtm = new DefaultTableModel(0, 0);;
            dtm.setColumnIdentifiers(headers);
            adminListOfVisits.setModel(dtm);

            for (Visit v:listOfVisits) {
                dtm.addRow(new Object[]{v.user.name, v.user.surname,v.user.PESEL ,v.user.email, v.user.phoneNumber, v.date, v.hour, v.officeWorker.name+" "+v.officeWorker.surname, v.officeWorker.department.name});
            }
        }else {
            throw new IllegalArgumentException("Invalid role!");
        }
    }

    private boolean validName(String name) {
        String regex = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*";

        Pattern p = Pattern.compile(regex);
        if (name == null || name.equals("")) return false;

        Matcher m = p.matcher(name);
        return m.matches();
    }

    private boolean validEmail(String email) {
        String regex = "^(.+)@(\\S+)$";

        Pattern p = Pattern.compile(regex);
        if (email == null || email.equals("")) return false;

        Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean phoneNumber(String number) {
        String regex = "[0-9]{9}";

        Pattern p = Pattern.compile(regex);
        if (number == null || number.equals("")) return false;

        Matcher m = p.matcher(number);
        return m.matches();
    }

    private boolean validDate(String date) throws ParseException {
        String regex = "\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$";
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date tempDate = formatter.parse(date);
            if (tempDate.before(new Date())) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Pattern p = Pattern.compile(regex);
        if (date == null || date.equals("")) return false;

        Matcher m = p.matcher(date);
        return m.matches();
    }

    private boolean validHour(String hour) {
        String regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

        Pattern p = Pattern.compile(regex);
        if (hour == null || hour.equals("")) return false;

        Matcher m = p.matcher(hour);
        return m.matches();
    }
}
