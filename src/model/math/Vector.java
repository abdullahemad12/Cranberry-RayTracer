package model.math;

public class Vector {
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }

}
