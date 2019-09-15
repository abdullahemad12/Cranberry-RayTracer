package model.graphics.object;

import exceptions.ColorOverflowException;

public class Color {
    private double r;
    private double g;
    private double b;


    public Color(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    /**
     *
     * @param c: the color to be added
     * @throws ColorOverflowException throws an exception if the some is greater than 1
     * @return the resulting color as a Color object
     */
    public Color add(Color c) throws ColorOverflowException {
        if(this.r + c.r > 1){
            throw new ColorOverflowException(this.r + c.r);
        }
        if(this.g + c.g > 1) {
            throw new ColorOverflowException(this.g + c.g);
        }
        if(this.b + c.b > 1) {
            throw new ColorOverflowException(this.b + c.b);
        }
        double r = (this.r + c.r);
        double g = (this.g + c.g);
        double b = (this.b + c.b);

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

    public Color pow(double a) {
        double r = Math.pow(this.r, a);
        double g = Math.pow(this.g, a);
        double b = Math.pow(this.b, a);

        return new Color(r, g, b);
    }

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

    public void subtract(Color c) {
        this.r = (this.r - c.r) % 1.00000001;
        this.g = (this.g - c.g) % 1.00000001;
        this.b = (this.b - c.b) % 1.00000001;
    }


}
