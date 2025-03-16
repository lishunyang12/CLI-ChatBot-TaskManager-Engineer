package Engineer.command;
import Engineer.task.*;

import java.util.ArrayList;

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

    public void printAllTask(ArrayList<Task> taskList, int count) {
        String text = textIndent("There are the tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            int taskId = i + 1;
            if(i == count-1) {
                text += textIndent(taskId + "." + taskList.get(i).toString());
            } else {
                text += textIndent(taskId + "." + taskList.get(i).toString() + "\n");
            }
        }
        sandwichByLine(text);
    }

    public void showTaskInfo(String text, int count, Task task) {
        text += textIndent(" " + task.toString() + "\n");
        String tasksOrTask = count == 1 ? "task" : "tasks";
        text += textIndent("Now you have " + count + " " + tasksOrTask + " in the list.");
        sandwichByLine(text);
    }

    public void addNewTask(Task task, int count) {
        String text = textIndent("Got it. I've added this task:\n");
        showTaskInfo(text, count+1, task);
    }

    public void removeTask(Task task, int count) {
        String text = textIndent("Noted. I've removed this task:\n");
        showTaskInfo(text, count, task);
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

    public String askForValidTask() {
        return textIndent("please enter a valid task type name." );
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
