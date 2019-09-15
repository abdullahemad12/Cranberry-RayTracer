package model.math;

/**
 * The class point represents a 3D point in space with its homogeneous coordinate value set to 1
 * so that translations apply to it
 * @author Abdullah Emad
 * @version 1.0
 */
public class Point extends MultiDimComponent{

    public Point(double x, double y, double z){
       super(x, y, z ,1);
    }

    /**
     * <b>EFFECTS: </b>Subtracts a multidimensional component from this Point
     * @param mdc the multidimensional component to be subtracted
     * @return the result of the subtraction
     */
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

    /**
     * <b>EFFECTS: </b>Subtracts a vector component from this Point
     * @param vector the vector to be subtracted
     * @return the result of the subtraction as a point
     */
    public Point subtract(Vector vector) {
        return new Point(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }

    /**
     * <b>EFFECTS: </b>Subtracts a point component from this Point
     * @param point the vector to be subtracted
     * @return the result of the subtraction as a Vector
     */
    public Vector subtract(Point point) {
        return new Vector(x - point.getX(), y - point.getY(), z - point.getZ());
    }


    /**
     * Adds a multidimensional component from this Point
     * @param mdc the multidimensional component to be added
     * @return the result of the addition
     */
    public MultiDimComponent add(MultiDimComponent mdc){
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

    /**
     * <b>EFFECTS: </b>Adds a vector component to this Point
     * @param vector the vector to be added
     * @return the result of the addition as a Point
     */
    public Point add(Vector vector) {
        return new Point(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    /**
     * <b>EFFECTS: </b>Adds a point component to this Point
     * @param point the vector to be added
     * @return the result of the addition as a Point
     */
    public Point add(Point point) {
        return new Point((x + point.getX()) / 2, (y + point.getY()) / 2, (z + point.getZ()) / 2);
    }


    @Override
    public boolean equals(Object obj) {

        return (super.equals(obj) || obj instanceof Point) && (Math.abs(((Point) obj).getX() - this.x) <= 10e-3 &&
                Math.abs(((Point) obj).getY() - this.y) <= 10e-3
                && Math.abs(((Point) obj).getZ() - this.z) <= 10e-7);
    }

}
