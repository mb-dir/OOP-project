public class OfficeWorker extends User{
    int yearsOfWork;
    float salary;
    String password;
    Department department;

    public OfficeWorker(int id, String name, String surname, String email, String phoneNumber, int yearsOfWork, float salary, String password, Department department) {
        super(id, name, surname, email, phoneNumber);
        this.yearsOfWork = yearsOfWork;
        this.salary = salary;
        this.password = password;
        this.department = department;
    }
}
