package Engineer.task;

/**
 * Represents a todo task in the task management system.
 * This class extends the Task class and provides specific implementations for todo tasks.
 */
public class ToDos extends Task {

    /**
     * Constructs a new ToDos task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDos task.
     * The format is: [T][status] description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDos task for saving to a file.
     * The format is: T | status | description.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}