package model.math;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
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

    public double dot(Vector vec){
        return x * vec.getX() + y * vec.getY() + z * vec.getZ();
    }

    public Vector cross(Vector vec){

        double i = (y * vec.getZ()) - (z * vec.getY());
        double j = - ((x * vec.getZ()) - (z * vec.getX()));
        double k = (x * vec.getY()) - (y * vec.getX());
        return new Vector(i, j, k);
    }

    public Vector add(Vector vec) {
        return new Vector(x + vec.getX(), y + vec.getY(), z + vec.getZ());
    }

    public Vector subtract(Vector vec){
        return new Vector(x - vec.getX(), y - vec.getY(), z - vec.getZ());
    }

    public Vector multiply(double scalar){
        return new Vector(scalar * x, scalar * y, scalar * z);
    }
    public Vector divide(double scalar){
        return new Vector(x / scalar, y / scalar, z / scalar);
    }

    public double magnitude(){
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public Normal normalize(){
        double magn = magnitude();
        return new Normal(x / magn, y / magn, z / magn);
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj) || obj instanceof Vector ? (Math.abs(((Vector)obj).getX() - this.x) <= 10e-3 &&
                Math.abs(((Vector)obj).getY() - this.y) <= 10e-3
                && Math.abs(((Vector)obj).getZ() - this.z) <= 10e-7) : false;
    }
}
