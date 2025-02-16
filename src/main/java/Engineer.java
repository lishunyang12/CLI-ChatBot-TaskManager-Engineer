import java.util.Scanner;

public class Engineer {
    static Scanner scanner = new Scanner(System.in);
    static TaskManager myTaskManager = new TaskManager();
    static ChatBotManager myChatBotManager = new ChatBotManager();
    static TextDivider myTextDivider = new TextDivider();

    public static void printResponse() throws EngineerException{
        boolean isKeepAsking = true;
        do {
            String inputMessage = scanner.nextLine(); // read input
            String[] words = inputMessage.split(" ");
            String firstWord = words[0];
            if (inputMessage.isEmpty()) {
                throw new EngineerException(myChatBotManager.askForNonEmptyValue());
            }
            switch(firstWord) {
            case "bye":
                myChatBotManager.sayGoodBye();
                isKeepAsking = false;
                break;
            case "mark":
            case "unmark":
                if (words.length == 1) {
                    throw new EngineerException(myChatBotManager.askForID());
                } else if (words.length == 2) {
                    myTaskManager.changeTaskStatus(words);
                } else {
                    throw new EngineerException(myChatBotManager.askForValidTask());
                }
                break;
            case "list":
                // print out all tasks
                myTaskManager.listAllTasks();
                break;
            case "todo":
                if (words.length == 1) {
                    throw new EngineerException(myChatBotManager.askForNonEmptyTask());
                }
                String inputTaskTodo = "";
                for(String word: words) {
                    inputTaskTodo += (word + " ");
                }
                myTaskManager.addTask(inputTaskTodo);
                break;
            case "deadline":
                if (words.length == 1) {
                    throw new EngineerException(myChatBotManager.askForNonEmptyTask());
                }
                String[] textStringDeadline = myTextDivider.divideDeadline(words);;
                String inputTaskDeadline = textStringDeadline[0];
                String by = textStringDeadline[1];
                myTaskManager.addTask(inputTaskDeadline, by);
                break;
            case "event":
                if (words.length == 1) {
                    throw new EngineerException(myChatBotManager.askForNonEmptyTask());
                }
                String[] textStringEvent = myTextDivider.divideEvent(words);
                String inputTaskEvents = textStringEvent[0];
                String from = textStringEvent[1];
                String to = textStringEvent[2];
                myTaskManager.addTask(inputTaskEvents, from, to);
                break;
            default:
                throw new EngineerException(myChatBotManager.askForValidTask());
            }
        } while(isKeepAsking);
    }

    public static void main(String[] args) {
        myChatBotManager.sayHello();
        try {
            printResponse();
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();  // stop receiving input
    }
}