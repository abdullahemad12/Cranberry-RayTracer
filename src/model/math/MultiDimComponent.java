package model.math;

abstract public class MultiDimComponent {

    double x;
    double y;
    double z;
    double w;

    public MultiDimComponent(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getX() { return x; }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public double getW(){
        return w;
    }

    /**
     * use to elimante numerical errors
     * @param scalar a double less than 0.0001
     * @return
     */
    public MultiDimComponent add(double scalar) {
        double x = this.x + scalar;
        double y = this.y + scalar;
        double z = this.z + scalar;

        if(w == 0) {
            return new Vector(x, y, z);
        }
        else {
           return new Point(x, y, z);
        }
    }

    public double dot(MultiDimComponent mdc){
        return x * mdc.getX() + y * mdc.getY() + z * mdc.getZ();
    }

    abstract public MultiDimComponent add(MultiDimComponent mdc);
    abstract public MultiDimComponent subtract(MultiDimComponent mdc);

}
