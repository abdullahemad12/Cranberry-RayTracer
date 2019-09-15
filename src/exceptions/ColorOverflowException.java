package exceptions;

/**
 * The ColorOverflowException is thrown when the color overflows due to an arithmetic operation
 * @author Abdullah Emad
 * @version 1.0
 */
public class ColorOverflowException extends CranberryException{
    public ColorOverflowException(double color){
        super("Color value have exceeded 1. Current value: " + color, 1);
    }
}
