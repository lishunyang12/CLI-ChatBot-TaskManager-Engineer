package Engineer.task;

/**
 * Represents a task with a specific deadline.
 * This class extends the Task class and adds a deadline field.
 */
public class Deadline extends Task {
    protected String by; // The deadline of the task

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format is: [D][status] description (by: deadline).
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task for saving to a file.
     * The format is: D | status | description | deadline.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}