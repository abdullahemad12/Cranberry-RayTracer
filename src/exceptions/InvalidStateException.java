package exceptions;

import controllers.parsing.States;
import controllers.parsing.Transitions;

/**
 * InvalidStateException is thrown from the parser when the input file contains error
 */
public class InvalidStateException extends CranberryException{

    private String message;

    /**
     *
     * @param oldState the old state of the Finite state machine
     * @param transition the transition action that produced this action
     * @param lineNumber the line number which took this action
     */
    public InvalidStateException(States oldState, Transitions transition, int lineNumber){
        super("Invalid File Syntax!", 4);

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
        super(4);
        message = command;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
