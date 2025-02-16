package engineer.task;
import engineer.command.ChatBotManager;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> taskList;
    static protected int taskCount;

    ChatBotManager myChatBotManager = new ChatBotManager();
    String partition_line = myChatBotManager.getLine();

    // constructor
    public TaskManager() {
        taskList = new ArrayList<>();
        this.taskCount = 0;
    }

    // method to be overloaded
    public void addTask(String inputTask) {
        taskList.add(new ToDos(inputTask));
        myChatBotManager.addNewTask(taskList.get(taskCount), taskCount);
        taskCount = taskList.size();
    }

    // For todo task
    public void addTask(String inputTask, String by) {
        taskList.add(new Deadline(inputTask, by));
        myChatBotManager.addNewTask(taskList.get(taskCount), taskCount);
        taskCount = taskList.size();
    }

    // For deadline task
    public void addTask(String inputTask, String from, String to) {
        taskList.add(new Events(inputTask, from, to));
        myChatBotManager.addNewTask(taskList.get(taskCount), taskCount);
        taskCount = taskList.size();
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

        // execute and show result as required
        System.out.println(partition_line);
        if(command.equals("mark")) {
            taskList.get(listIndex).markAsDone();
            System.out.println(myChatBotManager.textIndent("Nice! I've marked this task as done:"));
        } else {
            taskList.get(listIndex).markAsUndone();
            System.out.println(myChatBotManager.textIndent("Ok, I've marked this task as not done yet:"));
        }
        String Icon = taskList.get(listIndex).getStatusIcon();
        String description = taskList.get(listIndex).getDescription();
        System.out.println(myChatBotManager.textIndent(taskList.get(listIndex).toString()));
        System.out.println(partition_line);
    }

    public void removeTask(String[] words) {
        if(taskCount == 0) {
            myChatBotManager.emptyList();
            return;
        }
        String listIdString = words[1];
        int listIndex = Integer.parseInt(listIdString) - 1;
        Task removedTask = taskList.remove(listIndex);
        taskCount = taskList.size();
        myChatBotManager.removeTask(removedTask, taskCount);
    }
}
