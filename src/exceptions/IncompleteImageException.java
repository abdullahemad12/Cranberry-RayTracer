package exceptions;

/**
 * The InCompleteImageException is thrown when some colors where not committed to the Film
 * but the film was requested to write the Image to the file
 * @author Abdullah Emad
 * @version 1.0
 */
public class IncompleteImageException extends CranberryException {

    public IncompleteImageException() {
        super("The file is not yet ready to write all the pixels out!", 2);
    }
}
