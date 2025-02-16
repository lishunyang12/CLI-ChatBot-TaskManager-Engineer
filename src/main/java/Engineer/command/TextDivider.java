package engineer.command;
public class TextDivider {
    public String[] divideDeadline(String[] words){
        String inputTaskDeadline = "";
        String by = "";
        String[] textString = new String[2];
        int separationIndex = 0;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("/by")) {
                separationIndex = i;
            }
        }
        for(int i = 1; i < separationIndex; i++) {
            inputTaskDeadline += words[i] + " ";
        }
        for(int i = separationIndex+1; i < words.length; i++) {
            if(i == words.length-1) {
                by += words[i];
            } else {
                by += words[i] + " ";
            }
        }
        textString[0] = inputTaskDeadline;
        textString[1] = by;
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
            if(i == words.length-1) {
                to += words[i];
            } else {
                to += words[i] + " ";
            }
        }
        textString[0] = inputTaskEvents;
        textString[1] = from;
        textString[2] = to;
        return textString;
    }

}
