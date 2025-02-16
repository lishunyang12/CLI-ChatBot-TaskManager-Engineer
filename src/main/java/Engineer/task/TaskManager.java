package engineer.task;
import engineer.command.ChatBotManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    protected static final int MAX_NUM = 100;
    protected Task[] taskList;
    static protected int taskCount;
    private static final String DATA_FOLDER = "./data";
    private static final String FILE_PATH = DATA_FOLDER + "/duke.txt";

    ChatBotManager myChatBotManager = new ChatBotManager();
    String partition_line = myChatBotManager.getLine();

    // constructor
    public TaskManager() {
        this.taskCount = 0;
        this.taskList = new Task[MAX_NUM];
        loadTasks();
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
        saveTasks();
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
        saveTasks();
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
        saveTasks();
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

        saveTasks();
    }

    // Method to save tasks to file
    private void saveTasks() {
        try {
            // Ensure teh data folder exists
            File folder = new File(DATA_FOLDER);
            if(!folder.exists()) {
                folder.mkdir(); // Create the folder if it doesn't exist
            }

            //Write tasks to file
            FileWriter writer = new FileWriter(FILE_PATH);
            for(int i = 0; i < taskCount; i++) {
                writer.write(taskList[i].toFileString() + "\n");
            }
            writer.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to load tasks from file
    private void loadTasks() {
        try {
            if(!Files.exists(Paths.get(FILE_PATH))) {
                System.out.println("No file found");
                return;
            }

            //Read tasks from file
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                if(!line.trim().isEmpty()) {
                    Task task = Task.fromFileString(line);
                    if(task != null) {
                        taskList[taskCount] = task;
                        taskCount++;
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("Error loading tasks" + e.getMessage());
            resetTaskList();
        } catch(IllegalArgumentException e) {
            System.out.println("Corrupted file: " + e.getMessage());
            resetTaskList();
        };
    }

    // Helper method to reset the task list
    private void resetTaskList() {
        taskList = new Task[MAX_NUM];
        taskCount = 0;
        System.out.println("Task list reset to empty.");
    }
}
