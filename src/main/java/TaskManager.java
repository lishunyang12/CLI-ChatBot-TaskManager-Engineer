public class TaskManager {
    protected int MAXNUM = 100;
    protected Task[] taskList;
    protected int count;
    ChatBotManager myChatBotManager = new ChatBotManager();
    String partition_line = myChatBotManager.getLine();

    // constructor
    public TaskManager() {
        this.count = 0;
        this.taskList = new Task[MAXNUM];
    }

    public void addTask(String inputTask) {
        if(count == 100) {
            myChatBotManager.fullList();
            return;
        }
        taskList[count] = new ToDos(inputTask);
        myChatBotManager.addNewTask(taskList[count], count);
        count++;
    }

    public void addTask(String inputTask, String by) {
        if(count == 100) {
            myChatBotManager.fullList();
            return;
        }
        taskList[count] = new Deadline(inputTask, by);
        myChatBotManager.addNewTask(taskList[count], count);
        count++;
    }

    public void addTask(String inputTask, String from , String to) {
        if(count == 100) {
            myChatBotManager.fullList();
            return;
        }
        taskList[count] = new Events(inputTask, from, to);
        myChatBotManager.addNewTask(taskList[count], count);
        count++;
    }

    public void listAllTasks() {
       myChatBotManager.printAllTask(taskList, count);
    }

    public void changeTaskStatus(String[] words) {
        if(count == 0) {
            myChatBotManager.emptyList();
            return;
        }
        String command = words[0];
        String listIdString = words[1];

        int listIndex = Integer.parseInt(listIdString) - 1;
        if(listIndex < 0 | listIndex >= count) {
            myChatBotManager.askForValidID();
            return;
        }

        // execute and show result as required
        System.out.println(partition_line);
        if(command.equals("mark")) {
            taskList[listIndex].markAsDone();
            System.out.println(myChatBotManager.IndentedText("Nice! I've marked this task as done:"));
        } else {
            taskList[listIndex].markAsUndone();
            System.out.println(myChatBotManager.IndentedText("Ok, I've marked this task as not done yet:"));
        }
        String Icon = taskList[listIndex].getStatusIcon();
        String description = taskList[listIndex].getDescription();
        System.out.println(myChatBotManager.IndentedText(taskList[listIndex].toString()));
        System.out.println(partition_line);
    }

}
