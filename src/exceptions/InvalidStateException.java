package exceptions;

import controllers.parsing.States;
import controllers.parsing.Transitions;

public class InvalidStateException extends Exception{

    private String message;
    public InvalidStateException(States oldState, Transitions transition, int lineNumber){
        super("Invalid File Syntax!");

        String lineNumberMessage = "Line " + lineNumber + " : ";
        if(oldState == States.START){
            message = "Invalid File Syntax!\nExpected Command: size but found " + transition.getValue() + " instead";
        }
        else if(oldState == States.INTERMEDIATE){
            message = "Invalid File Syntax!\nExpected Command: Camera but found " + transition.getValue() + " instead";
        }
        else if(oldState ==  States.INTERMEDIATE1){
            if(transition == Transitions.MAX_DEPTH) {
                message = "Invalid File Syntax!\nDuplicate value for Max Depth";
            }
            else {
                message = "Invalid File Syntax!\nExpected Command: Camera but found " + transition.getValue() + " instead";
            }
        }
        else if(oldState == States.INTERMEDIATE2) {
            if(transition == Transitions.OUTPUT) {
                message = "Invalid File Syntax!\nDuplicate value for Max Depth";
            }
            else {
                message = "Invalid File Syntax!\nExpected Command: Camera but found " + transition.getValue() + " instead";
            }
        }
        else if(oldState == States.INTERMEDIATE3) {
            if(transition == Transitions.MAX_DEPTH || transition == Transitions.OUTPUT) {
                message = "Invalid File Syntax!\nDuplicate value for " + transition.getValue();
            }
            else {
                message = "Invalid File Syntax!\nExpected Command: Camera but found " + transition.getValue() + " instead";
            }
        }
        else{
            message = "Invalid File Syntax!\nCame Across an Invalid command";
        }

        message = lineNumberMessage + message;
    }
    public InvalidStateException(String command) {
        message = command;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
