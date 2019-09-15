package exceptions;

import model.graphics.Sample;

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
