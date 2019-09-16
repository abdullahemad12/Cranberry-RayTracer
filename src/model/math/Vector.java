package model.math;

/**
 * The Vector class represents a 3D vector in the space with it's homogeneous
 * coordinate value set to 0. Therefore translations will not apply to a vector
 * @author Abdullah Emad
 * @version 1.0
 */
public class Vector extends MultiDimComponent{


    public Vector(double x, double y, double z){
        super(x, y, z, 0);
    }


    /**
     * <b>EFFECTS:</b> Computes the cross product between this Vector and Vector vec
     * in the following order: this x vec
     * @param vec the second vector in the cross product
     * @return the resulting vector of the cross product
     */
    public Vector cross(Vector vec){
        double i = (y * vec.getZ()) - (z * vec.getY());
        double j = - ((x * vec.getZ()) - (z * vec.getX()));
        double k = (x * vec.getY()) - (y * vec.getX());
        return new Vector(i, j, k);
    }

    /**
     * <b>EFFECTS:</b> performs an addition between this vector and another multiDimComponent
     * @param mdc the other multidimensional component to be added to this vector
     * @return return this + mdc as a vector if mdc was a vector or a de homogenised point otherwise
     */
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

    /**
     * <b>EFFECTS:</b> Adds a vector to this vector
     * @param vector the vector to be added
     * @return the result of the addition as a vector
     */
    public Vector add(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    /**
     * <b>EFFECTS:</b> Adds a point to this vector
     * @param point the point to be added
     * @return the result of adding the point with this vector as a point
     */
    public Point add(Point point) {
        return new Point(x + point.getX(), y + point.getY(), z + point.getZ());
    }

    /**
     * <b>EFFECTS:</b> Subtracts a multidimensional component from this vector in the
     * following order: this - mdc
     * @param mdc the multidimensional component to be subtracted
     * @return the result of the subtraction as a vector if mdc was a vector, or as a de homogenised point otherwise
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
     * <b>EFFECTS: </b> subtracts a vector from this vector
     * @param vector the vector to be subtracted
     * @return the result of subtracting the vector as a vector
     */
    public Vector subtract(Vector vector) {
        return new Vector(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }

    /**
     * <b>EFFECTS: </b> subtracts a point from this vector
     * @param point the point to be subtracted from this vector
     * @return the result of subtracting the point as a point
     */
    public Point subtract(Point point) {
        return new Point(point.getX() - x, point.getY() - y, point.getZ() - z);
    }

    /**
     * <b>EFFECTS: </b> performs scalar multiplication
     * @param scalar the scalar to be multiplied element-wise to the vector
     * @return the result of multiplication as vector
     */
    public Vector multiply(double scalar){
        return new Vector(scalar * x, scalar * y, scalar * z);
    }

    /**
     * <b>EFFECTS: </b> performs scalar division
     * @param scalar the scalar to divide the vector element-wise with
     * @return the result of division as a vector
     */
    public Vector divide(double scalar){
        return new Vector(x / scalar, y / scalar, z / scalar);
    }

    /**
     * <b>EFFECTS: <b/>performs an element-wise division operation on two vectors this / vec
     * @param vec the vector that divides this
     * @return the result of the division as a Vector
     */
    public Vector divide(Vector vec) {
        return new Vector(x / vec.x, y / vec.y, z / vec.z);
    }

    /**
     * <b>EFFECTS: <b/> calculate the magnitude of this vector
     * @return the length of the vector
     */
    public double magnitude(){
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    /**
     * <b>EFFECTS: <b/> normalizes the vector so it has a length of 1
     * @return the normalized vector as a normal
     */
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
