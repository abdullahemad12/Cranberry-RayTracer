package model.math;

public class Point extends MultiDimComponent{

    public Point(double x, double y, double z){
       super(x, y, z ,1);
    }

    /**
     * Subtracts a multidimensional component from this Point
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

    public Point subtract(Vector vector) {
        return new Point(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }

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

    public Point add(Vector vector) {
        return new Point(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

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
