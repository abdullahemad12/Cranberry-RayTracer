package model.graphics.light;

public class Attenuation {
    private double constant;
    private double linear;
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
}
