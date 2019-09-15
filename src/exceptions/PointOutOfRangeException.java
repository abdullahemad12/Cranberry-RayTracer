package exceptions;

/**
 * the PointOutOfRangeException is thrown from the ray class when the given distance is out of range
 */
public class PointOutOfRangeException extends CranberryException {
    public PointOutOfRangeException(){
        super("The given t is out of range", 5);
    }
}
