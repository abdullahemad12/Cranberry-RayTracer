package exceptions;

public class PointOutOfRangeException extends Exception {
    public PointOutOfRangeException(){
        super("The given t is out of range");
    }
}
