package exceptions;

/**
 * The general Exception class in cranberry ray tracer
 * @author Abdullah Emad
 * @version 1.0
 */
abstract public class CranberryException extends Exception {
    /**
     * the default exit status for the program on Errors
     */
    public static final int DEFAULT_EXIT_STATUES = 12;
    /**
     * the exit status on Error
     */
    public final int EXIT_STATUES;

    CranberryException(String message, int EXIT_STATUES) {
        super(message);
        this.EXIT_STATUES = EXIT_STATUES;
    }

    CranberryException(int EXIT_STATUES) {
        this.EXIT_STATUES = EXIT_STATUES;
    }

}
