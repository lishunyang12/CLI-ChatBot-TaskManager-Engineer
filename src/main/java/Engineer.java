import java.util.Scanner;

public class Engineer {
    public static void main(String[] args) {
        String partition_line = "-----------------------------------\n";
        Scanner scanner = new Scanner(System.in);

        System.out.println(partition_line + "Hello! I'm Engineer \n" + "what can I do dor you? \n");
        // wait for user input
        String inputString = scanner.nextLine(); // read input

        // echo
        while(true) {
            if(inputString.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon! \n" + partition_line);
                break;
            }
            System.out.println(inputString);
            inputString = scanner.nextLine();
        }
        scanner.close();  // stop receiving input
    }
}
