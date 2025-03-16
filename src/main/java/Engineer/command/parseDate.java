package Engineer.command;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Parses date and time strings into formatted date/time representations.
 * This class supports multiple date and time formats and provides flexible parsing.
 */
public class parseDate {

    /**
     * Parses a date/time string into a formatted string.
     * Supports the following formats:
     * - yyyy/M/d HHmm (e.g., 2025/3/12 1600)
     * - yyyy/MM/dd HHmm (e.g., 2025/03/12 1600)
     * - yyyy/M/d (e.g., 2025/3/12)
     * - yyyy/MM/dd (e.g., 2025/03/12)
     *
     * @param dataString The date/time string to parse.
     * @return A formatted date/time string (e.g., "Mar 12 2025 16:00" or "Mar 12 2025").
     *         Returns an error message if parsing fails.
     */
    public String parseDateInput(String dataString) {
        if (dataString == null) {
            return "Error: Input is empty.";
        }

        try {
            // Define possible input formatters
            DateTimeFormatter formatterWithTimeSingleDigit = DateTimeFormatter.ofPattern("yyyy/M/d HHmm", Locale.ENGLISH);
            DateTimeFormatter formatterWithTimeDoubleDigit = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm", Locale.ENGLISH);
            DateTimeFormatter formatterWithoutTimeSingleDigit = DateTimeFormatter.ofPattern("yyyy/M/d", Locale.ENGLISH);
            DateTimeFormatter formatterWithoutTimeDoubleDigit = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);

            // Define possible output formatters
            DateTimeFormatter outputFormatterWithTime = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH);
            DateTimeFormatter outputFormatterWithoutTime = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);

            // Check if the input contains a time portion
            boolean hasTime = dataString.contains(" ");

            // Dynamically select formatter and parse
            if (hasTime) {
                // Try parsing with single-digit month/day format
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(dataString, formatterWithTimeSingleDigit);
                    return dateTime.format(outputFormatterWithTime);
                } catch (DateTimeParseException e1) {
                    // Try parsing with double-digit month/day format
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(dataString, formatterWithTimeDoubleDigit);
                        return dateTime.format(outputFormatterWithTime);
                    } catch (DateTimeParseException e2) {
                        return "Failed to parse: " + dataString;
                    }
                }
            } else {
                // Parse without time portion
                try {
                    LocalDate date = LocalDate.parse(dataString, formatterWithoutTimeSingleDigit);
                    return date.format(outputFormatterWithoutTime);
                } catch (DateTimeParseException e3) {
                    try {
                        LocalDate date = LocalDate.parse(dataString, formatterWithoutTimeDoubleDigit);
                        return date.format(outputFormatterWithoutTime);
                    } catch (DateTimeParseException e4) {
                        return "Failed to parse (invalid date): " + dataString;
                    }
                }
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}