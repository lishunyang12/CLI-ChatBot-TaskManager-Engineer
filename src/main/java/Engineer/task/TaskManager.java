package engineer.task;
import engineer.command.ChatBotManager;

public class TaskManager {
    protected static final int MAX_NUM = 100;
    protected Task[] taskList;
    static protected int taskCount;

    ChatBotManager myChatBotManager = new ChatBotManager();
    String partition_line = myChatBotManager.getLine();

    // constructor
    public TaskManager() {
        this.taskCount = 0;
        this.taskList = new Task[MAX_NUM];
    }

    // method to be overloaded
    public void addTask(String inputTask) {
        if(taskCount == 100) {
            myChatBotManager.earnFullList();
            return;
        }
        taskList[taskCount] = new ToDos(inputTask);
        myChatBotManager.addNewTask(taskList[taskCount], taskCount);
        taskCount++;
    }

    // For todo task
    public void addTask(String inputTask, String by) {
        if(taskCount == 100) {
            myChatBotManager.earnFullList();
            return;
        }
        taskList[taskCount] = new Deadline(inputTask, by);
        myChatBotManager.addNewTask(taskList[taskCount], taskCount);
        taskCount++;
    }

    // For deadline task
    public void addTask(String inputTask, String from, String to) {
        if(taskCount == 100) {
            myChatBotManager.earnFullList();
            return;
        }
        taskList[taskCount] = new Events(inputTask, from, to);
        myChatBotManager.addNewTask(taskList[taskCount], taskCount);
        taskCount++;
    }

    public void listAllTasks() {
       myChatBotManager.printAllTask(taskList, taskCount);
    }

    public void changeTaskStatus(String[] words) {
        if(taskCount == 0) {
            myChatBotManager.emptyList();
            return;
        }
        String command = words[0];
        String listIdString = words[1];

        int listIndex = Integer.parseInt(listIdString) - 1;
        if(listIndex < 0 | listIndex >= taskCount) {
            myChatBotManager.askForValidID();
            return;
        }

        // execute and show result as required
        System.out.println(partition_line);
        if(command.equals("mark")) {
            taskList[listIndex].markAsDone();
            System.out.println(myChatBotManager.textIndent("Nice! I've marked this task as done:"));
        } else {
            taskList[listIndex].markAsUndone();
            System.out.println(myChatBotManager.textIndent("Ok, I've marked this task as not done yet:"));
        }
        String Icon = taskList[listIndex].getStatusIcon();
        String description = taskList[listIndex].getDescription();
        System.out.println(myChatBotManager.textIndent(taskList[listIndex].toString()));
        System.out.println(partition_line);
    }

}
