package model.screen;

import exceptions.InvalidSampleException;

public class Sample {

    private int x;
    private int y;


    public Sample(int x, int y) throws InvalidSampleException

    {
        this.x = x;
        this.y = y;

        if(x < 0 || y < 0){
            throw new InvalidSampleException(this);
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
