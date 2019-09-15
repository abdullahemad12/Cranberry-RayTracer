package exceptions;

public class PointOutOfRangeException extends CranberryException {
    public PointOutOfRangeException(){
        super("The given t is out of range", 5);
    }
}
