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
    protected ArrayList<Task> taskList;
    static protected int taskCount;
    private static final String DATA_FOLDER = "./data";
    private static final String FILE_PATH = DATA_FOLDER + "/duke.txt";

    ChatBotManager myChatBotManager = new ChatBotManager();
    String partition_line = myChatBotManager.getLine();

    // constructor
    public TaskManager() {
        taskList = new ArrayList<>();
        this.taskCount = 0;
        loadTasks();
    }

    // method to be overloaded
    public void addTask(String inputTask) {
        taskList.add(new ToDos(inputTask));
        myChatBotManager.addNewTask(taskList.get(taskCount), taskCount);
        taskCount = taskList.size();
        saveTasks();
    }

    // For todo task
    public void addTask(String inputTask, String by) {
        taskList.add(new Deadline(inputTask, by));
        myChatBotManager.addNewTask(taskList.get(taskCount), taskCount);
        taskCount = taskList.size();
        saveTasks();
    }

    // For deadline task
    public void addTask(String inputTask, String from, String to) {
        taskList.add(new Events(inputTask, from, to));
        myChatBotManager.addNewTask(taskList.get(taskCount), taskCount);
        taskCount = taskList.size();
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

        saveTasks();
    }

    public void removeTask(String[] words) {
        if (taskCount == 0) {
            myChatBotManager.emptyList();
            return;
        }
        String listIdString = words[1];
        int listIndex = Integer.parseInt(listIdString) - 1;
        Task removedTask = taskList.remove(listIndex);
        taskCount = taskList.size();
        myChatBotManager.removeTask(removedTask, taskCount);

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
                writer.write(taskList.get(i).toFileString() + "\n");
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
                        taskList.add(task);
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
        taskList = new ArrayList<>();
        taskCount = taskList.size();
        System.out.println("Task list reset to empty.");
    }
}
