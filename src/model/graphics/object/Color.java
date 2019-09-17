package model.graphics.object;

import exceptions.ColorOverflowException;

/**
 * The color class represents a RGB color normalized in the range between 0 and 1 inclusive.
 * @author Abdullah Emad
 * @version 1.0
 */
public class Color {
    private double r;
    private double g;
    private double b;


    /**
     *
     * @param r red
     * @param g green
     * @param b blue
     */
    public Color(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     *
     * @return r
     */
    public double getR() {
        return r;
    }

    /**
     *
     * @return g
     */
    public double getG() {
        return g;
    }

    /**
     *
     * @return b
     */
    public double getB() {
        return b;
    }

    /**
     * Adds two colors together
     * @param c: the color to be added
     * @throws ColorOverflowException throws an exception if the some is greater than 1
     * @return the resulting color as a Color object
     */
    public Color add(Color c) throws ColorOverflowException {

        double r = (this.r + c.r);
        double g = (this.g + c.g);
        double b = (this.b + c.b);

        r = r > 1 ? 1 : r;
        g = g > 1 ? 1 : g;
        b = b > 1 ? 1 : b;

        if(r > 1) {
            throw new ColorOverflowException(r);
        }
        if(g > 1) {
            throw new ColorOverflowException(g);
        }
        if(b > 1) {
            throw new ColorOverflowException(b);
        }


        return new Color(r, g, b);
    }

    /**
     * Multiples two colors
     * @param c color to be multiplied
     * @return the resulting color as a Color object
     */
    public Color multiply(Color c) {
        double r = this.r * c.r;
        double g = this.g * c.g;
        double b = this.b * c.b;
        return new Color(r,g ,b);
    }

    /**
     * Multiplies the color by a constant
     * @param a the multiplication factor
     * @return the resulting Color
     * @throws ColorOverflowException if the result of the multiplication results in a value > 1
     */
    public Color multiply(double a) throws ColorOverflowException {
        double r = this.r * a;
        double g = this.g * a;
        double b = this.b * a;


        if(r > 1) {
            throw new ColorOverflowException(r);
        }
        if(g > 1) {
            throw new ColorOverflowException(g);
        }
        if(b > 1) {
            throw new ColorOverflowException(b);
        }

        return new Color(r, g, b);
    }

    /**
     * subtracts a color from this
     * @param c color to be subtracted
     * @deprecated
     */
    @Deprecated
    public void subtract(Color c) {
        this.r = (this.r - c.r) % 1.00000001;
        this.g = (this.g - c.g) % 1.00000001;
        this.b = (this.b - c.b) % 1.00000001;
    }


}
