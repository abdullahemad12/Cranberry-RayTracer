package exceptions;

public class InvalidMathOperationException extends Exception{

    public InvalidMathOperationException(){
        super("Attempted to perform an Invalid Mathematical operation");
    }
}
