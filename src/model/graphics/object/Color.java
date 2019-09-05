package model.graphics.object;

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

    public void add(Color c) {
       // this.r = (this.r + c.r) > 1 ? 1 : (this.r + c.r);
    }

    public void subtract(Color c) {

    }

    public void multiply(double a) {

    }

    public void divide(double a) {

    }

}
