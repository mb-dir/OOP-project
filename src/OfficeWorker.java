public class OfficeWorker extends User{
    int yearsOfWork;
    float salary;

    public OfficeWorker(int id, String name, String surname, String email, String phoneNumber, int yearsOfWork, float salary) {
        super(id, name, surname, email, phoneNumber);
        this.yearsOfWork = yearsOfWork;
        this.salary = salary;
    }
}
