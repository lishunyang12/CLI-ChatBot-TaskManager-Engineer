package Engineer.exception;

/**
 * A custom exception class for handling errors specific to the Engineer application.
 * This exception is used to provide meaningful error messages to the user.
 */
public class EngineerException extends Exception {

    /**
     * Constructs a new EngineerException with the specified error message.
     *
     * @param message The error message to be displayed.
     */
    public EngineerException(String message) {
        super(message);
    }
}