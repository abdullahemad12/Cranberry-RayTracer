package exceptions;

public class IncompleteImageException extends CranberryException {

    public IncompleteImageException() {
        super("The file is not yet ready to write all the pixels out!", 2);
    }
}
