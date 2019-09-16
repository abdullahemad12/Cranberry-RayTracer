package model.graphics.object;

import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.math.Point;
import model.math.Vector;
import model.math.transformation.Matrix;

/**
 * The class Box represents a box/cubiod in the world. The box class itself is not yet Integrated
 * with the ray tracer itself therefore some of its calculations are not complete such as the normal vector
 * of the LocalGeo produced from the ray-box intersection. However, this class is used by the Octa tree data structure
 * @author Abdullah Emad
 * @version 1.0
 */
public class Box implements Shape {

    private Point A;
    private Point B;

    public Box(Point A, Point B) {
        this.A = A;
        this.B = B;
    }

    /**
     *
     * @return A
     */
    public Point getA() { return A; }

    /**
     *
     * @return B
     */
    public Point getB() { return B; }

    @Override
    public boolean isOverlapping(Box boundingBox) {
        boolean cond1 = this.B.getX() < boundingBox.getA().getX();
        boolean cond2 = boundingBox.getB().getX() < this.getA().getX();

        boolean cond3 = this.B.getY() < boundingBox.getA().getY();
        boolean cond4 = boundingBox.getB().getY() < this.getA().getY();

        boolean cond5 = this.B.getZ() < boundingBox.getA().getZ();
        boolean cond6 = boundingBox.getB().getZ() < this.getA().getZ();

        return !(cond1 || cond2 || cond3 || cond4 || cond5 || cond6);
    }

    @Override
    public LocalGeo intersect(Ray ray) throws PointOutOfRangeException {

        Vector T_1, T_2; // vectors to hold the T-values for every direction
        double t_near = Double.MIN_VALUE; // maximums defined in float.h
        double t_far = Double.MAX_VALUE;

        Vector direction = ray.getDir();
        Point rayOrigin = ray.getPos();
        // Test if the ray is parallel to the x y z planes
        if(direction.getX() == 0){
            if(direction.getX() < A.getX() || direction.getX() > B.getX()) {
                throw new PointOutOfRangeException();
            }
        }
        if(direction.getY() == 0){
            if(direction.getY() < A.getY() || direction.getY() > B.getY()) {
                throw new PointOutOfRangeException();
            }
        }
        if(direction.getZ() == 0){
            if(direction.getZ() < A.getZ() || direction.getZ() > B.getZ()) {
                throw new PointOutOfRangeException();
            }
        }

        T_1 = A.subtract(rayOrigin).divide(direction);
        T_2 = B.subtract(rayOrigin).divide(direction);

        double[] arr1 = new double[]{T_1.getX(), T_1.getY(), T_1.getZ()};
        double[] arr2 = new double[]{T_2.getX(), T_2.getY(), T_2.getZ()};

        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] > arr2[i]) {
                swap(arr1, arr2, i);
            }

            if(arr1[i] > t_near) {
                t_near = arr1[i];
            }

            if(arr2[i] < t_far) {
                t_far = arr2[i];
            }

            if(t_near > t_far || (t_far < 0)) {
                throw new PointOutOfRangeException();
            }

        }
        /*TODO: the normal should be calulcated in the future*/
        if(t_near >= 0) {
            return new LocalGeo(t_near, ray.ray(t_near), null);
        }
        return new LocalGeo(t_far, ray.ray(t_far), null);
    }

    @Override
    public boolean doesIntersect(Ray ray) {
        try{
            intersect(ray);
        }catch (PointOutOfRangeException e) {
            return false;
        }
        return true;
    }

    @Override
    public void transform(Matrix matrix) {
        A = matrix.transform(A);
        B = matrix.transform(B);
    }

    @Override
    public BRDF getBRDF() {
        return null;
    }

    @Override
    public Box calculateBoundingBox() {
        return this;
    }

    public double getVolume(){
        double w = Math.abs(B.getX()- A.getX());
        double h = Math.abs(B.getY() - A.getY());
        double l = Math.abs(B.getZ() - A.getZ());
        return w * h * l;
    }
    private static void swap(double[] arr1, double[] arr2, int i) {
        double tmp = arr1[i];
        arr1[i] = arr2[i];
        arr2[i] = tmp;
    }
}
