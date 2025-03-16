package Engineer.command;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class parseDate {
    // create dates format from string
    public String parseDateInput(String dataString){
        if(dataString == null) {
            return "Error: Input is empty.";
        }

        try {
            // define possible input formatter
            DateTimeFormatter formatterWithTimeSingleDigit = DateTimeFormatter.ofPattern("yyyy/M/d HHmm", Locale.ENGLISH);
            DateTimeFormatter formatterWithTimeDoubleDigit = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm", Locale.ENGLISH);
            DateTimeFormatter formatterWithoutTimeSingleDigit = DateTimeFormatter.ofPattern("yyyy/M/d", Locale.ENGLISH);
            DateTimeFormatter formatterWithoutTimeDoubleDigit = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);

            // define possible output formatter
            DateTimeFormatter outputFormatterWithTime = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH);
            DateTimeFormatter outputFormatterWithoutTime = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);

            // identify if contains time portion
            boolean hasTime = dataString.contains(" ");

            // dynamically select formatter and parse
            if(hasTime) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(dataString, formatterWithTimeSingleDigit);
                    return dateTime.format(outputFormatterWithTime);
                } catch (DateTimeParseException e1) {
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(dataString, formatterWithTimeDoubleDigit);
                        return dateTime.format(outputFormatterWithTime);
                    } catch(DateTimeParseException e2) {
                        return "Fail to parse: " + dataString;
                    }
                }
            } else {
                // parse without time
                try {
                    LocalDate date = LocalDate.parse(dataString, formatterWithoutTimeSingleDigit);
                    return date.format(outputFormatterWithoutTime);
                } catch (DateTimeParseException e3) {
                    try {
                        LocalDate date = LocalDate.parse(dataString, formatterWithoutTimeDoubleDigit);
                        return date.format(outputFormatterWithoutTime);
                    } catch(DateTimeParseException e4) {
                        return "Failed to parse(invalid date): " + dataString;
                    }
                }
            }
        } catch(Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
