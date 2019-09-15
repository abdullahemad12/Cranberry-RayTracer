package model.math;

/**
 * The class MultiDimComponent represents a Multidimensional component
 * characterised by its x y z location in a 3D space
 * @author Abdullah Emad
 */
abstract public class MultiDimComponent {

    double x;
    double y;
    double z;
    double w;

    /**
     *
     * @param x the x location on the x-axis
     * @param y the y location on the y-axis
     * @param z the z location on the z-axis
     * @param w the value of the homogeneous coordinate
     */
    public MultiDimComponent(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     *
     * @return x
     */
    public double getX() { return x; }

    /**
     *
     * @return y
     */
    public double getY() { return y; }

    /**
     *
     * @return z
     */
    public double getZ() { return z; }

    /**
     *
     * @return w
     */
    public double getW() { return w; }



    public double dot(MultiDimComponent mdc){
        return x * mdc.getX() + y * mdc.getY() + z * mdc.getZ();
    }

    abstract public MultiDimComponent add(MultiDimComponent mdc);
    abstract public MultiDimComponent subtract(MultiDimComponent mdc);

}
