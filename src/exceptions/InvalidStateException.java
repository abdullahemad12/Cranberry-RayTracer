package exceptions;

import controllers.parsing.States;
import controllers.parsing.Transitions;

public class InvalidStateException extends Exception{

    private String message;
    public InvalidStateException(States oldState, Transitions transition){
        super("Invalid File Syntax!");
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
            message = "Invalid File Syntax!\n Came Across an Invalid command";
        }
    }
    public InvalidStateException(String command) {
        message = "Invalid File Syntax!\n Came Across an Invalid command:\n" + command;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
