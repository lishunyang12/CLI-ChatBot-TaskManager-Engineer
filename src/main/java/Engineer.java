import java.util.Scanner;

public class Engineer {
    public static void main(String[] args) {
        String partition_line = "-----------------------------------\n";
        Scanner scanner = new Scanner(System.in);

        System.out.println(partition_line + "Hello! I'm Engineer \n" + "what can I do dor you? \n");
        // wait for user input
        String inputTask = scanner.nextLine(); // read input

        Task[] list = new Task[100];
        int count = 0;
        // echo
        while(true) {
            // check if user wants to end the session
            if(inputTask.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon! \n" + partition_line);
                break;
            }

            // check if user wants to mark or unmark
            String[] words = inputTask.split(" ");

            if((words.length == 2 && (words[0].equals("mark") | words[0].equals("unmark")))) {
                // parse input as command and index
                String command = words[0];
                String listIdString = words[1];
                int listIndex = Integer.parseInt(listIdString) - 1;
                // execute and show result as required
                String Icon = list[listIndex].getStatusIcon();
                String description = list[listIndex].getDescription();

                System.out.println(partition_line);
                if(words[0].equals("mark")) {
                    list[listIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    list[listIndex].markAsUndone();
                    System.out.println("Ok, I've marked this task as not done yet:");
                }
                System.out.println("[" + Icon + "] " + description);
                System.out.println(partition_line);
            } else if (inputTask.equals("list")) {
                // print out all tasks
                for (int i = 0; i < count; i++) {
                    int TaskId = i + 1;
                    System.out.println(TaskId + "." + "[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
            } else if (!words[0].isEmpty()){// store the input and increment counter
                list[count] = new Task(inputTask);
                count++;
                System.out.println("added: " + inputTask);
            }

            inputTask = scanner.nextLine();
        }
        scanner.close();  // stop receiving input
    }
}
