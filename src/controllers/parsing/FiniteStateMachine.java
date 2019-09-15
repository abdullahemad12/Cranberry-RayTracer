package controllers.parsing;

import exceptions.InvalidStateException;

/**
 * The FiniteStateMachine acts helps the parser check for syntax errors
 * @author Abdullah Emad
 * @version 1.0
 */
public class FiniteStateMachine {
    /**
     * the current state of the finite machine
     */
    private States currentState;
    /**
     * the state transition function
     */
    private States[][] transitionMatrix;

    public FiniteStateMachine() {
        currentState = States.START;
        transitionMatrix = new States[States.values().length][Transitions.values().length];

        for(int i = 0; i < transitionMatrix.length; i++){
            for(int j = 0; j < transitionMatrix[i].length; j++) {
                transitionMatrix[i][j] = States.ERROR;
            }
        }
        /*Start State transitions*/
        transitionMatrix[States.START.getValue()][Transitions.BLANK_LINE.getId()] = States.START;
        transitionMatrix[States.START.getValue()][Transitions.COMMENT.getId()] = States.START;
        transitionMatrix[States.START.getValue()][Transitions.SIZE.getId()] = States.INTERMEDIATE;


        transitionMatrix[States.INTERMEDIATE.getValue()][Transitions.COMMENT.getId()] = States.INTERMEDIATE;
        transitionMatrix[States.INTERMEDIATE.getValue()][Transitions.BLANK_LINE.getId()] = States.INTERMEDIATE;
        transitionMatrix[States.INTERMEDIATE.getValue()][Transitions.MAX_DEPTH.getId()] = States.INTERMEDIATE1;
        transitionMatrix[States.INTERMEDIATE.getValue()][Transitions.OUTPUT.getId()] = States.INTERMEDIATE2;
        transitionMatrix[States.INTERMEDIATE.getValue()][Transitions.CAMERA.getId()] = States.FINAL;

        transitionMatrix[States.INTERMEDIATE1.getValue()][Transitions.CAMERA.getId()] = States.FINAL;
        transitionMatrix[States.INTERMEDIATE1.getValue()][Transitions.OUTPUT.getId()] = States.INTERMEDIATE3;
        transitionMatrix[States.INTERMEDIATE1.getValue()][Transitions.COMMENT.getId()] = States.INTERMEDIATE1;
        transitionMatrix[States.INTERMEDIATE1.getValue()][Transitions.BLANK_LINE.getId()] = States.INTERMEDIATE1;

        transitionMatrix[States.INTERMEDIATE2.getValue()][Transitions.CAMERA.getId()] = States.FINAL;
        transitionMatrix[States.INTERMEDIATE2.getValue()][Transitions.MAX_DEPTH.getId()] = States.INTERMEDIATE3;
        transitionMatrix[States.INTERMEDIATE2.getValue()][Transitions.COMMENT.getId()] = States.INTERMEDIATE2;
        transitionMatrix[States.INTERMEDIATE2.getValue()][Transitions.BLANK_LINE.getId()] = States.INTERMEDIATE2;


        transitionMatrix[States.INTERMEDIATE3.getValue()][Transitions.CAMERA.getId()] = States.FINAL;
        transitionMatrix[States.INTERMEDIATE3.getValue()][Transitions.COMMENT.getId()] = States.INTERMEDIATE3;
        transitionMatrix[States.INTERMEDIATE3.getValue()][Transitions.BLANK_LINE.getId()] = States.INTERMEDIATE3;

        transitionMatrix[States.FINAL.getValue()][Transitions.COMMENT.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.BLANK_LINE.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.SPHERE.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.MAX_VERTS.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.VERTEX.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.TRI.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.TRANSLATE.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.ROTATE.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.SCALE.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.PUSH_TRANSFORM.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.POP_TRANSFORM.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.DIRECTIONAL.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.POINT.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.ATTENUATION.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.AMBIENT.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.DIFFUSE.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.SPECULAR.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.SHININESS.getId()] = States.FINAL;
        transitionMatrix[States.FINAL.getValue()][Transitions.EMISSION.getId()] = States.FINAL;

    }


    private Transitions stringToTransitions(String command) {
        Transitions[] transitions = Transitions.values();
        for(Transitions transition : transitions){
            if(transition.getValue().equals(command)){
                return transition;
            }
        }

        return null;
    }

    /**
     * Takes an action given a transition command
     * @param command the transition command
     * @param lineNumber the line number at which this transition command was parsed
     * @return the transition as an ENUM on success
     * @throws InvalidStateException when the machine end up in the ERROR state
     */
    public Transitions takeAction(String command, int lineNumber) throws InvalidStateException {
        Transitions transitions = stringToTransitions(command);
        if(transitions == null){
            throw new InvalidStateException("Line " + lineNumber +  " : Unknown Command: " + command + "!");
        }

        States previousState = currentState;
        currentState = transitionMatrix[currentState.getValue()][transitions.getId()];

        if(currentState == States.ERROR){
            throw new InvalidStateException(previousState, transitions, lineNumber);
        }
        return transitions;
    }



}
