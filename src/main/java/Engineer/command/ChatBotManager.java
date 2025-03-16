package Engineer.command;

import Engineer.task.*;

import java.util.ArrayList;

/**
 * Manages the interaction between the user and the chatbot.
 * This class handles the display of messages, task lists, and user prompts.
 */
public class ChatBotManager {
    private String indent = "    "; // Indentation for formatting output
    private String line = indent + "___________________________"; // Divider line for formatting output

    /**
     * Prints a text block surrounded by divider lines.
     *
     * @param text The text to be displayed.
     */
    private void sandwichByLine(String text) {
        System.out.println(line + "\n" + text + "\n" + line);
    }

    /**
     * Prints a text block with a divider line at the top and bottom.
     *
     * @param text The text to be displayed.
     */
    private void sandwichBylineversionTwo(String text) {
        System.out.println(line + "\n" + text + line);
    }

    /**
     * Returns the divider line used for formatting output.
     *
     * @return The divider line.
     */
    public String getLine() {
        return line;
    }

    /**
     * Adds indentation to the given text.
     *
     * @param text The text to be indented.
     * @return The indented text.
     */
    public String textIndent(String text) {
        return indent + text;
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param taskList The list of tasks to be displayed.
     * @param count    The number of tasks in the list.
     */
    public void printAllTask(ArrayList<Task> taskList, int count) {
        String text = textIndent("Here are the tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            int taskId = i + 1;
            if (i == count - 1) {
                text += textIndent(taskId + "." + taskList.get(i).toString());
            } else {
                text += textIndent(taskId + "." + taskList.get(i).toString() + "\n");
            }
        }
        sandwichByLine(text);
    }

    /**
     * Prints all tasks that match the given keyword.
     *
     * @param taskList The list of tasks to search.
     * @param keyword  The keyword to match in task descriptions.
     */
    public void printAllMatchingTask(ArrayList<Task> taskList, String keyword) {
        String text = textIndent("Here are the matching tasks in your list: \n");
        int taskId = 0;
        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.get(i).getDescription();
            String[] descriptionWords = description.split(" ");

            for (String word : descriptionWords) {
                if (word.equals(keyword)) {
                    taskId = taskId + 1;
                    text += textIndent(taskId + "." + taskList.get(i).toString() + "\n");
                    break; // Avoid printing the same task multiple times
                }
            }
        }
        sandwichBylineversionTwo(text);
    }

    /**
     * Displays task information along with the total number of tasks.
     *
     * @param text  The initial text to display.
     * @param count The total number of tasks.
     * @param task  The task to display.
     */
    public void showTaskInfo(String text, int count, Task task) {
        text += textIndent(" " + task.toString() + "\n");
        String tasksOrTask = count == 1 ? "task" : "tasks";
        text += textIndent("Now you have " + count + " " + tasksOrTask + " in the list.");
        sandwichByLine(text);
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param task  The task that was added.
     * @param count The total number of tasks after adding the new task.
     */
    public void addNewTask(Task task, int count) {
        String text = textIndent("Got it. I've added this task:\n");
        showTaskInfo(text, count + 1, task);
    }

    /**
     * Displays a message when a task is removed.
     *
     * @param task  The task that was removed.
     * @param count The total number of tasks after removing the task.
     */
    public void removeTask(Task task, int count) {
        String text = textIndent("Noted. I've removed this task:\n");
        showTaskInfo(text, count, task);
    }

    /**
     * Displays a welcome message when the chatbot starts.
     */
    public void sayHello() {
        String text = textIndent("Hello! I'm Engineer") + "\n" + textIndent("What can I do for you?");
        sandwichByLine(text);
    }

    /**
     * Displays a goodbye message when the chatbot ends.
     */
    public void sayGoodBye() {
        sandwichByLine(textIndent("Bye. Hope to see you again soon!"));
    }

    /**
     * Prompts the user to enter a task ID.
     *
     * @return The prompt message.
     */
    public String askForID() {
        return textIndent("Please enter a Task ID.");
    }

    /**
     * Prompts the user to enter a valid task type.
     *
     * @return The prompt message.
     */
    public String askForValidTask() {
        return textIndent("Please enter a valid task type name.");
    }

    /**
     * Prompts the user to enter a task description.
     *
     * @return The prompt message.
     */
    public String askForNonEmptyTask() {
        return textIndent("Please enter a task.");
    }

    /**
     * Prompts the user to enter a non-empty value.
     *
     * @return The prompt message.
     */
    public String askForNonEmptyValue() {
        return textIndent("Please enter a non-empty value.");
    }

    /**
     * Displays a message when the task list is full.
     */
    public void earnFullList() {
        System.out.println(textIndent("The task list is full, you cannot add more."));
    }

    /**
     * Displays a message when the task list is empty.
     */
    public void emptyList() {
        System.out.println(textIndent("There is no task now!"));
    }
}