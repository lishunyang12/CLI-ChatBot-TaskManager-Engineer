package engineer.task;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    };

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Converts the task to a string format for saving to a file.
     * Format: "T | 1 | read book" (for todos)
     *         "D | 0 | return book | June 6th" (for deadlines)
     *         "E | 0 | project meeting | Aug 6th 2-4pm" (for events)
     */
    public abstract String toFileString();

    /**
     * Parses a string from the file and creates a Task object.
     *
     * @param fileString The string representation of the task from the file.
     * @return A Task object.
     * @throws IllegalArgumentException If the file string is invalid.
     */
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if(parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch(type) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            if(parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format");
            }
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if(parts.length<5) {
                throw new IllegalArgumentException("Invalid event format");
            }
            String from = parts[3];
            String to = parts[4];
            task = new Events(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type in file");
        }

        if(isDone) {
            task.markAsDone();
        }
        return task;
    }
}

