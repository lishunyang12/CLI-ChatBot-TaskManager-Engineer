package Engineer.command;

import Engineer.command.parseDate;

/**
 * Divides input strings into task descriptions and date/time components.
 * This class is responsible for parsing user input for deadlines and events.
 */
public class TextDivider {
    parseDate dateParser = new parseDate();

    /**
     * Divides a deadline input string into task description and deadline date.
     * The input string should contain a "/by" keyword followed by the deadline date.
     *
     * @param words The input string split into an array of words.
     * @return A String array where:
     *         - index 0: Task description.
     *         - index 1: Formatted deadline date.
     * @throws IllegalArgumentException If the "/by" keyword is not found in the input.
     */
    public String[] divideDeadline(String[] words) {
        String inputTaskDeadline = "";
        String[] textString = new String[2];
        int separationIndex = -1;

        // Find the position of "/by"
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                separationIndex = i;
                break;
            }
        }

        // Throw an exception if "/by" is not found
        if (separationIndex == -1) {
            throw new IllegalArgumentException("Error: '/by' not found in input.");
        }

        // Extract the task description
        for (int i = 1; i < separationIndex; i++) {
            inputTaskDeadline += words[i] + " ";
        }

        // Extract the date input
        String dateInput = words[separationIndex + 1];
        if (separationIndex + 2 != words.length) {
            dateInput += " " + words[separationIndex + 2];
        }

        // Parse and format the date
        String formattedDate = dateParser.parseDateInput(dateInput);
        textString[0] = inputTaskDeadline.trim(); // Remove trailing spaces
        textString[1] = formattedDate;
        return textString;
    }

    /**
     * Divides an event input string into task description, start date/time, and end date/time.
     * The input string should contain "/from" and "/to" keywords followed by the respective dates/times.
     *
     * @param words The input string split into an array of words.
     * @return A String array where:
     *         - index 0: Task description.
     *         - index 1: Formatted start date/time.
     *         - index 2: Formatted end date/time.
     * @throws IllegalArgumentException If "/from" or "/to" keywords are not found in the input.
     */
    public String[] divideEvent(String[] words) {
        String inputTaskEvents = "";
        String from = "";
        String to = "";
        String[] textString = new String[3];

        int firstDivisionIndex = -1;
        int secondDivisionIndex = -1;

        // Find the positions of "/from" and "/to"
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/from")) {
                firstDivisionIndex = i;
            }
            if (words[i].equals("/to")) {
                secondDivisionIndex = i;
            }
        }

        // Throw an exception if "/from" or "/to" is not found
        if (firstDivisionIndex == -1 || secondDivisionIndex == -1) {
            throw new IllegalArgumentException("Error: '/from' or '/to' not found in input.");
        }

        // Extract the task description
        for (int i = 0; i < firstDivisionIndex; i++) {
            inputTaskEvents += words[i] + " ";
        }

        // Extract the start date/time
        for (int i = firstDivisionIndex + 1; i < secondDivisionIndex; i++) {
            from += words[i] + " ";
        }

        // Extract the end date/time
        for (int i = secondDivisionIndex + 1; i < words.length; i++) {
            to += words[i] + " ";
        }

        // Trim trailing spaces
        from = from.trim();
        to = to.trim();

        // Parse and format the dates/times
        String formattedDateFrom = dateParser.parseDateInput(from);
        String formattedDateTo = dateParser.parseDateInput(to);
        textString[0] = inputTaskEvents.trim(); // Remove trailing spaces
        textString[1] = formattedDateFrom;
        textString[2] = formattedDateTo;
        return textString;
    }
}