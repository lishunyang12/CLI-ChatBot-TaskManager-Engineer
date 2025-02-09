import java.util.Scanner;

public class Engineer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager myTaskManager = new TaskManager();
        ChatBotManager myChatBotManager = new ChatBotManager();
        TextDivider myTextDivider = new TextDivider();
        myChatBotManager.sayHello();
        String inputMessage = scanner.nextLine(); // read input
        boolean isKeepAsking = true;
        // echo
        do {
            String[] words = inputMessage.split(" ");
            String firstWord = words[0];
            if (!inputMessage.isEmpty()) {
                switch(firstWord) {
                case "bye":
                    myChatBotManager.sayGoodBye();
                    isKeepAsking = false;
                    break;
                case "mark":
                case "unmark":
                    if (words.length == 1) {
                        myChatBotManager.askForID();
                    } else if (words.length == 2) {
                        myTaskManager.changeTaskStatus(words);
                    } else {
                        myChatBotManager.askForValidTask();
                    }
                    break;
                case "list":
                    // print out all tasks
                    myTaskManager.listAllTasks();
                    break;
                case "todo":
                    String inputTaskTodo = words[1] + " " + words[2];
                    myTaskManager.addTask(inputTaskTodo);
                    break;
                case "deadline":
                    String[] textStringDeadline = myTextDivider.deadline_divider(words);;
                    String inputTaskDeadline = textStringDeadline[0];
                    String by = textStringDeadline[1];
                    myTaskManager.addTask(inputTaskDeadline, by);
                    break;
                case "event":
                    String[] textStringEvent = myTextDivider.event_divider(words);
                    String inputTaskEvents = textStringEvent[0];
                    String from = textStringEvent[1];
                    String to = textStringEvent[2];
                    myTaskManager.addTask(inputTaskEvents, from, to);
                    break;
                default:
                    myChatBotManager.askForValidTask();
                }
            } else {
                myChatBotManager.askForNonEmptyValue();
            }
        inputMessage = scanner.nextLine();
        } while(isKeepAsking);
        scanner.close();  // stop receiving input
    }
}