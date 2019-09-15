package model.graphics;

import exceptions.PointOutOfRangeException;
import model.math.Point;
import model.math.Vector;
import model.math.transformation.Matrix;

/**
 * The Ray class represents a ray casted in the world. A ray is characterised by two properties:
 * <ol>
 *    <li>The position at which the ray starts<li/>
 *    <li>the direction at which it ends<li/>
 * <ol/>
 * @author Abdullah Emad
 * @version 1.0
 */
public class Ray {

    /**
     * The minimum value of t to be considered by the ray (default zero)
     */
    private static double t_min = 0;
    /**
     * the maximum value of t to be considered by the ray (default 100)
     */
    private static double t_max = 100;

    public static void setTMin(double t_min){
        Ray.t_min = t_min;
    }
    public static void setTMax(double t_max){
        Ray.t_max = t_max;
    }

    /**
     * the position at which the ray starts
     */
    private Point pos;
    /**
     * the direction of the ray
     */
    private Vector dir;

    /**
     *
     * @param pos ray position
     * @param dir ray direction
     */
    public Ray(Point pos, Vector dir){
        this.pos = pos;
        this.dir = dir;
    }

    /**
     * Calculates a point at a distance t from pos in dir direction
     * @param t a distance from pos
     * @return the point at the distance t
     * @throws PointOutOfRangeException when t is less than t_mini or greater than t_max
     */
    public Point ray(double t) throws PointOutOfRangeException{
        // ray(t) = pos + t*dir
        if(t < t_min || t > t_max){
            throw new PointOutOfRangeException();
        }
        return this.pos.add(this.dir.multiply(t));
    }

    /**
     * Calculates the distance of Point p from the point pos
     * @param p the point of interest
     * @return the distance t as a double
     */
    public double calculateT(Point p){

        Vector dist = p.subtract(pos);

        return dist.magnitude();
    }

    /**
     *
     * @return pos
     */
    public Point getPos() { return pos;}

    /**
     *
     * @return dir
     */
    public Vector getDir() { return dir; }

    /**
     * Transforms this ray given the transformation Matrix
     * @param matrix the Transformation Matrix
     * @return the transformed ray
     */
    public Ray transform(Matrix matrix) {

        Point pos =  matrix.transform(this.pos);
        Vector dir = matrix.transform(this.dir).normalize();
        return new Ray(pos, dir);
    }
}
