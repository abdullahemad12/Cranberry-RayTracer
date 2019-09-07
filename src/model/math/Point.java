package model.math;

public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z){
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


    public Vector subtract(Point p){
        return new Vector(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    public Point add(Vector vector){
        return new Point(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj) || obj instanceof Point ? (Math.abs(((Point)obj).getX() - this.x) <= 10e-3 &&
         Math.abs(((Point)obj).getY() - this.y) <= 10e-3
               && Math.abs(((Point)obj).getZ() - this.z) <= 10e-7) : false;
    }

}
