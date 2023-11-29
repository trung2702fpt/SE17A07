package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isOverTimeBooking(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        LocalDateTime now = LocalDateTime.now();
        return dateTime.isAfter(now);
    }
}
