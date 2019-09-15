package model.graphics.light;

/**
 * The Attenuation class represents the attenuation of the lights
 * @author Abdullah Emad
 * @version 1.0
 */
public class Attenuation {
    /**
     * the constant term
     */
    private double constant;
    /**
     * the linear term
     */
    private double linear;
    /**
     * the quadratic term
     */
    private double quadratic;

    public Attenuation() {
        constant = 1;
        linear = 0;
        quadratic = 0;
    }

    public Attenuation(double constant, double linear, double quadratic){
        this.constant = constant;
        this.linear = linear;
        this.quadratic = quadratic;
    }

    /**
     * calculates the attenuation at distance d
     * @param d the distance at which the attenuation should be calulated
     * @return the attenuation as a double
     */
    public double computeAttenuation(double d) {
        double dsq = d * d;

        return 1 / (constant + (linear * d) + (quadratic * dsq));
    }
}
