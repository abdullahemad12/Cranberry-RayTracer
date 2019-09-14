package model.math;

public class Vector extends MultiDimComponent{


    public Vector(double x, double y, double z){
        super(x, y, z, 0);
    }



    public Vector cross(Vector vec){

        double i = (y * vec.getZ()) - (z * vec.getY());
        double j = - ((x * vec.getZ()) - (z * vec.getX()));
        double k = (x * vec.getY()) - (y * vec.getX());
        return new Vector(i, j, k);
    }

    public MultiDimComponent add(MultiDimComponent mdc) {
        double x = this.x + mdc.x;
        double y = this.y + mdc.y;
        double z = this.z + mdc.z;
        double w = this.w + mdc.w;

        if(w == 0) {
            return new Vector(x, y, z);
        }
        else{
            return new Point(x / w, y / w, z / w);
        }

    }
    public Vector add(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    public Point add(Point point) {
        return new Point(x + point.getX(), y + point.getY(), z + point.getZ());
    }

    public MultiDimComponent subtract(MultiDimComponent mdc) {
        double x = this.x - mdc.x;
        double y = this.y - mdc.y;
        double z = this.z - mdc.z;
        double w = this.w - mdc.w;

        if(w == 0) {
            return new Vector(x, y, z);
        }
        else{
            return new Point(x / w, y / w, z / w);
        }
    }

    public Vector subtract(Vector vector) {
        return new Vector(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }

    public Point subtract(Point point) {
        return new Point(point.getX() - x, point.getY() - y, point.getZ() - z);
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

        return (super.equals(obj) || obj instanceof Vector) && (Math.abs(((Vector) obj).getX() - this.x) <= 10e-3 &&
                Math.abs(((Vector) obj).getY() - this.y) <= 10e-3
                && Math.abs(((Vector) obj).getZ() - this.z) <= 10e-7);
    }
}
