package exceptions;

import model.screen.Sample;

public class InvalidSampleException extends Exception {

    private Sample sample;
    public InvalidSampleException(Sample sample){
        super("Invalid Sample with values x: " + sample.getX() + " y: " + sample.getY());
        this.sample = sample;
    }

    public Sample getSample(){
        return sample;
    }
}
