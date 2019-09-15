package exceptions;

public class CranberryException extends Exception {
    public static final int DEFAULT_EXIT_STATUES = 12;
    public final int EXIT_STATUES;

    CranberryException(String message, int EXIT_STATUES) {
        super(message);
        this.EXIT_STATUES = EXIT_STATUES;
    }

    CranberryException(int EXIT_STATUES) {
        this.EXIT_STATUES = EXIT_STATUES;
    }

}
