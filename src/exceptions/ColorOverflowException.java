package exceptions;

public class ColorOverflowException extends Exception{

    public ColorOverflowException(double color){
        super("Color value have exceeded 1. Current value: " + color);
    }
}
