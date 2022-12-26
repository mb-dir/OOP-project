public class Admin extends User{
    String adminBio, password;
    public Admin(int id, String name, String surname, String email, String phoneNumber, String adminBio, String password) {
        super(id, name, surname, email, phoneNumber);
        this.adminBio = adminBio;
        this.password = password;
    }
}
