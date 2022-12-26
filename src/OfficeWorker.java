public class OfficeWorker extends User{
    int yearsOfWork;
    float salary;
    String password;

    public OfficeWorker(int id, String name, String surname, String email, String phoneNumber, int yearsOfWork, float salary, String password) {
        super(id, name, surname, email, phoneNumber);
        this.yearsOfWork = yearsOfWork;
        this.salary = salary;
        this.password = password;
    }
}
