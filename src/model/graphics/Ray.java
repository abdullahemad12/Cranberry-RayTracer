package model.graphics;

import exceptions.PointOutOfRangeException;
import model.math.Point;
import model.math.Vector;

public class Ray {

    private static double t_min;
    private static double t_max;
    public static void setTMin(double t_min){
        Ray.t_min = t_min;
    }
    public static void setTMax(double t_max){
        Ray.t_max = t_max;
    }

    private Point pos;
    private Vector dir;
    public Ray(Point pos, Vector dir){
        this.pos = pos;
        this.dir = dir;
    }

    public Point ray(double t) throws PointOutOfRangeException{
        // ray(t) = pos + t*dir
        if(t < t_min || t > t_max){
            throw new PointOutOfRangeException();
        }
        return this.pos.add(this.dir.multiply(t));
    }
}