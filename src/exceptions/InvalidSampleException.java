package exceptions;

import model.graphics.Sample;

/**
 * The InvalidSampleException is thrown when the sample takes a value less than zero
 * @author Abdullah Emad
 * @version 1.0
 */
public class InvalidSampleException extends CranberryException {

    private Sample sample;
    public InvalidSampleException(Sample sample){
        super("Invalid Sample with values x: " + sample.getX() + " y: " + sample.getY(), 3);
        this.sample = sample;
    }

    public Sample getSample(){
        return sample;
    }
}
