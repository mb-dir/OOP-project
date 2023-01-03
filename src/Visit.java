public class Visit {
    User user;
    OfficeWorker officeWorker;
    String date, hour;

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Visit(User user, OfficeWorker officeWorker, String date, String hour) {
        this.user = user;
        this.officeWorker = officeWorker;
        this.date = date;
        this.hour = hour;
    }

}
