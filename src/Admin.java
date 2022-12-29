public class Admin extends User{
    String password;
    public Admin(String PESEL, String name, String surname, String email, String phoneNumber, String password) {
        super(PESEL, name, surname, email, phoneNumber);
        this.password = password;
    }
}
