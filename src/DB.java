import java.util.ArrayList;
import java.util.Arrays;

public class DB {
    //Admin instance - there is only one admin
    Admin admin = new Admin("61081036478","Zyta", "Guzek", "zg@gmail.com", "111222333", "admin123");

    //Office Workers
    Department d1 = new Department("1","ZUS", "Izdebki", "11-111");
    Department d2 = new Department("2","KRUS", "Rzeszów", "22-222");
    Department d3 = new Department("3","PZU", "Pogórze", "33-333");

    OfficeWorker OW1 = new OfficeWorker("73020468529", "Paweł", "Olkiewicz", "po@gmail.com", "222333444", 2,4500, "tajnehaslo", d1);
    OfficeWorker OW2 = new OfficeWorker("58091781218", "Jan", "Urban", "ju@gmail.com", "222333544", 2,4600, "tajnehaslo", d2);
    OfficeWorker OW3 = new OfficeWorker("91012786242", "Dariusz", "Pataj", "jp@gmail.com", "222333544", 2,4600, "tajnehaslo", d3);

    ArrayList<OfficeWorker> listOfOW = new ArrayList<>(Arrays.asList(OW1, OW2, OW3));
    ArrayList<Department> listOfDepartments = new ArrayList<>(Arrays.asList(d1, d2, d3));

    ArrayList<Visit> listOfVisits= new ArrayList<>();

    public Admin getAdmin() {
        return admin;
    }

    public ArrayList<OfficeWorker> getListOfOW() {
        return listOfOW;
    }

    public ArrayList<Department> getListOfDepartments() {
        return listOfDepartments;
    }

    public ArrayList<Visit> getListOfVisits() {
        return listOfVisits;
    }
}
