package model.graphics;

import exceptions.InvalidSampleException;

/**
 * The Sample class hold the x, y location of a specific pixel on the screen
 * @author Abdullah Emad
 * @version 1.0
 */
public class Sample {

    private int x;
    private int y;

    /**
     *
     * @param x the x location of the pixel
     * @param y the y location of the pixel
     * @throws InvalidSampleException when the x or y are negative
     */
    public Sample(int x, int y) throws InvalidSampleException

    {
        this.x = x;
        this.y = y;

        if(x < 0 || y < 0){
            throw new InvalidSampleException(this);
        }
    }

    /**
     *
     * @return x
     */
    public int getX(){
        return x;
    }

    /**
     *
     * @return y
     */
    public int getY(){
        return y;
    }
}
