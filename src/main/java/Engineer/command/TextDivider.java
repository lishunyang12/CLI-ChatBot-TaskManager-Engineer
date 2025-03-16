package Engineer.command;
import Engineer.command.parseDate;

public class TextDivider {
    parseDate dateParser = new parseDate();
    public String[] divideDeadline(String[] words){
        String inputTaskDeadline = "";
        String[] textString = new String[2];
        int separationIndex = -1;
        // find /by positioin
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("/by")) {
                separationIndex = i;
                break;
            }
        }

        if(separationIndex == -1) {
            throw new IllegalArgumentException("Error: '/by not found in input");
        }
        for(int i = 1; i < separationIndex; i++) {
            inputTaskDeadline += words[i] + " ";
        }

        String dateInput = words[separationIndex+1];
        if(separationIndex+2 != words.length) {
            dateInput += " " + words[separationIndex+2];
        }

        String formattedDate = dateParser.parseDateInput(dateInput);
        textString[0] = inputTaskDeadline;
        textString[1] = formattedDate;
        return textString;
    }

    public String[] divideEvent(String[] words){
        String inputTaskEvents = "";
        String from = "";
        String to = "";
        String[] textString = new String[3];

        int firstDivisionIndex = 0;
        int secondDivisionIndex = 0;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("/from")) {
                firstDivisionIndex = i;
            }
            if(words[i].equals("/to")) {
                secondDivisionIndex = i;
            }
        }
        for(int i = 0; i < firstDivisionIndex; i++) {
            inputTaskEvents += words[i] + " ";
        }
        for(int i = firstDivisionIndex+1; i < secondDivisionIndex; i++) {
            from += words[i] + " ";
        }
        for(int i = secondDivisionIndex+1; i < words.length; i++) {
            to += words[i] + " ";
        }
        //trim spaces in the end
        from = from.trim();
        to = to.trim();

        String formattedDateFrom = dateParser.parseDateInput(from);
        String formattedDateTo = dateParser.parseDateInput(to);
        textString[0] = inputTaskEvents;
        textString[1] = formattedDateFrom;
        textString[2] = formattedDateTo;
        return textString;
    }
}
