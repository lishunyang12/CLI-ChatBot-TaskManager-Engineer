package engineer.command;
import engineer.task.*;

public class ChatBotManager {
    private String indent = "    ";
    private String line = indent + "___________________________";
//    TaskManager myTaskManager = new TaskManager();

    private void sandwichByLine(String text) {
        System.out.println(line + "\n" + text + "\n" + line);
    }

    public String getLine() {
        return line;
    }
    public String textIndent(String text) {
        return indent + text;
    }

    public void printAllTask(Task[] taskList, int count) {
        String text = textIndent("There are the tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            int taskId = i + 1;
            if(i == count-1) {
                text += textIndent(taskId + "." + taskList[i].toString());
            } else {
                text += textIndent(taskId + "." + taskList[i].toString() + "\n");
            }
        }
        sandwichByLine(text);
    }

    public void addNewTask(Task task, int count) {
        String text = textIndent("Got it. I've added this task:\n");
        text += textIndent(" " + task.toString() + "\n");
        text += textIndent("Now you have " + ((int)count+1) + " tasks in the list.");
        sandwichByLine(text);
    }

    public void sayHello() {
        String text = textIndent("Hello! I'm Engineer") + "\n" + textIndent("what can I do for you?");
        sandwichByLine(text);
    }

    public void sayGoodBye() {
        sandwichByLine(textIndent("Bye. Hope to see you again soon!"));
    }

    public String askForID() {
       return textIndent("please enter a Task ID." );
    }

    public String askForValidID() {
        return textIndent("please enter a valid ID." );
    }

    public String askForValidTask() {
        return textIndent("please enter a valid task." );
    }

    public String askForNonEmptyTask() {
        return textIndent("please enter a task." );
    }

    public String askForNonEmptyValue() {
        return textIndent("please enter a non-empty value.");
    }

    public void earnFullList() {
        System.out.println(textIndent("The task list is full, you cannot add more." ));
    }

    public void emptyList() { System.out.println(textIndent("There is no task now!"));}
}
