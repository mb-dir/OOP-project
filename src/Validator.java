import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public Validator() {}

    protected boolean validName(String name) {
        String regex = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*";

        Pattern p = Pattern.compile(regex);
        if (name == null || name.equals("")) return false;

        Matcher m = p.matcher(name);
        return m.matches();
    }

    protected boolean validEmail(String email) {
        String regex = "^(.+)@(\\S+)$";

        Pattern p = Pattern.compile(regex);
        if (email == null || email.equals("")) return false;

        Matcher m = p.matcher(email);
        return m.matches();
    }

    protected boolean phoneNumber(String number) {
        String regex = "[0-9]{9}";

        Pattern p = Pattern.compile(regex);
        if (number == null || number.equals("")) return false;

        Matcher m = p.matcher(number);
        return m.matches();
    }

    protected boolean validDate(String date) throws ParseException {
        String regex = "\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$";
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        Pattern p = Pattern.compile(regex);
        if (date == null || date.equals("")) return false;

        try {
            Date tempDate = formatter.parse(date);
            if (tempDate.before(new Date())) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Matcher m = p.matcher(date);
        return m.matches();
    }

    protected boolean validHour(String hour) {
        String regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

        Pattern p = Pattern.compile(regex);
        if (hour == null || hour.equals("")) return false;

        Matcher m = p.matcher(hour);
        return m.matches();
    }

    protected void creatErrorMessage(boolean isNameValid, boolean isSurnameValid, boolean isEmailValid, boolean isPhoneValid, boolean areComboboxesValid, boolean isDateValid, boolean isHourValid){
        String errorMessage = "";
        if(!isNameValid) errorMessage+="Niedozwolone znaki w imieniu\n";
        if(!isSurnameValid) errorMessage+="Niedozwolone znaki w nazwisku\n";
        if(!isEmailValid) errorMessage+="Błędny format email\n";
        if(!isPhoneValid) errorMessage+="Błędny format nr. telefonu\n";
        if(!areComboboxesValid) errorMessage += "Musisz wybrać wydział i urzędnika\n";
        if(!isDateValid) errorMessage+="Błędny format daty\n";
        if(!isHourValid) errorMessage+="Błędny format godziny\n";

        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
