package Engineer.task;

/**
 * Represents an event task with a start time and end time.
 * This class extends the Task class and adds fields for the start and end times.
 */
public class Events extends Task {
    protected String from; // The start time of the event
    protected String to;   // The end time of the event

    /**
     * Constructs a new Events task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Events task.
     * The format is: [E][status] description (from: start time to: end time).
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Events task for saving to a file.
     * The format is: E | status | description | start time | end time.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}