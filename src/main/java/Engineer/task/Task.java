package Engineer.task;

/**
 * Represents a generic task in the task management system.
 * This is an abstract class that serves as the base for specific task types (e.g., ToDos, Deadlines, Events).
 */
public abstract class Task {
    protected String description; // The description of the task
    protected boolean isDone;     // The completion status of the task

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The format is: [status] description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates the task is done, and " " indicates the task is not done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Converts the task to a string format for saving to a file.
     * The format depends on the specific task type:
     * - ToDos: "T | status | description"
     * - Deadlines: "D | status | description | deadline"
     * - Events: "E | status | description | start time | end time"
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Parses a string from the file and creates a Task object.
     * The string format should match the format used in toFileString().
     *
     * @param fileString The string representation of the task from the file.
     * @return A Task object.
     * @throws IllegalArgumentException If the file string is invalid.
     */
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format");
            }
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid event format");
            }
            String from = parts[3];
            String to = parts[4];
            task = new Events(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type in file");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}