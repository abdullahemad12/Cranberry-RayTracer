package exceptions;

public class ColorOverflowException extends CranberryException{
    private static final int exit_statues = 3;
    public ColorOverflowException(double color){
        super("Color value have exceeded 1. Current value: " + color, 1);
    }
}
