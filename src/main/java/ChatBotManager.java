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

    public String IndentedText(String text) {
        return indent + text;
    }

    public void printAllTask(Task[] taskList, int count) {
        String text = IndentedText("There are the tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            int taskId = i + 1;
            if(i == count-1) {
                text += IndentedText(taskId + "." + taskList[i].toString());
            } else {
                text += IndentedText(taskId + "." + taskList[i].toString() + "\n");
            }
        }
        sandwichByLine(text);
    }

    public void addNewTask(Task task, int count) {
        String text = IndentedText("Got it. I've added this task:\n");
        text += IndentedText(" " + task.toString() + "\n");
        text += IndentedText("Now you have " + ((int)count+1) + " tasks in the list.");
        sandwichByLine(text);
    }

    public void sayHello() {
        String text = IndentedText("Hello! I'm Engineer") + "\n" + IndentedText("what can I do for you?");
        sandwichByLine(text);
    }

    public void sayGoodBye() {
        sandwichByLine(IndentedText("Bye. Hope to see you again soon!"));
    }

    public void askForID() {
        System.out.println(IndentedText("please enter a Task ID." ));
    }

    public void askForValidID() {
        System.out.println(IndentedText("please enter a valid ID." ));
    }

    public void askForValidTask() {
        System.out.println(IndentedText("please enter a valid task." ));
    }

    public void askForNonEmptyValue() {
        System.out.println(IndentedText("please enter a non-empty value." ));
    }

    public void fullList() {
        System.out.println(IndentedText("The task list is full, you cannot add more." ));
    }

    public void emptyList() { System.out.println(IndentedText("There is no task now!"));}
}
