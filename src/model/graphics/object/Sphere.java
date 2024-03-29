package model.graphics.object;

import controllers.scene.RayTracer;
import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;
import model.math.transformation.Identity;
import model.math.transformation.Matrix;

/**
 * @author Abdullah Emad
 * This class represents a sphere in the world. A sphere is represented
 * by the coordinates of its center and the length of its radius
 * The Sphere class implements the Shape interface
 */
public class Sphere implements Shape {

    /**
     * the lighting material of the sphere
     */
    private BRDF brdf;
    /**
     * the center location of the sphere
     */
    private Point center;
    /**
     * the radius of the sphere
     */
    private double radius;
    private Matrix transformMatrix;

    /**
     *
     * @param center the center
     * @param radius the radius
     * @param brdf the brdf colors
     */
    public Sphere(Point center, double radius, BRDF brdf) {
        this.center = center;
        this.radius = radius;
        this.brdf = brdf;
        this.transformMatrix = new Identity();
    }


    @Override
    public LocalGeo intersect(Ray ray) throws PointOutOfRangeException {

        //transform the ray
        Matrix invTranform = this.transformMatrix.invert();
        Ray originalRay = ray;
        ray = ray.transform(invTranform);

        Point p0 = ray.getPos();
        Vector p1 = ray.getDir();

        Vector p0_c = p0.subtract(this.center);

        double a = p1.dot(p1);
        double b = 2.0 * p1.dot(p0_c);
        double c = p0_c.dot(p0_c) - Math.pow(this.radius, 2.0);

        double disc = Math.pow(b, 2.0) - (4.0 * a * c);

        // no Intersection possible
        if(disc < 0.0){
            throw new PointOutOfRangeException();
        }


        double x1 = (-b + Math.sqrt(disc)) / (2.0 * a);
        double x2 = (-b - Math.sqrt(disc)) / (2.0 * a);

        if(x1 < 0 && x2 < 0) {
            throw new PointOutOfRangeException();
        }

        double t;
        if(x1 < 0){
           t = x2;
        }
        else if(x2 < 0) {
            t = x1;
        }
        else {
            t = Double.min(x1, x2);
        }

        // Transform the Pos, it's normal and the ray by the transformMatrix
        Point pos = ray.ray(t);
        Normal normal = pos.subtract(center).normalize();



        pos = this.transformMatrix.transform(pos);

        normal = this.transformMatrix.transform(normal).normalize();


        t = originalRay.calculateT(pos);


        return new LocalGeo(t, pos, normal);
    }

    @Override
    public boolean doesIntersect(Ray ray) {
        try{
            intersect(ray);
        }
        catch (PointOutOfRangeException e) {
            return false;
        }

        return true;
    }

    @Override
    public void transform(Matrix matrix) {
        this.transformMatrix = matrix.multiply(this.transformMatrix);
    }

    @Override
    public BRDF getBRDF() {
        return this.brdf;
    }

    @Override
    public Box calculateBoundingBox() {
        Point A = new Point(this.center.getX() - radius - RayTracer.ERROR_EPSILON,
                this.center.getY() - radius - RayTracer.ERROR_EPSILON,
                this.center.getZ() - radius - RayTracer.ERROR_EPSILON);

        Point B = new Point(this.center.getX() + radius + RayTracer.ERROR_EPSILON,
                this.center.getY() + radius + RayTracer.ERROR_EPSILON,
                this.center.getZ() + radius + RayTracer.ERROR_EPSILON);
        return new Box(A, B);
    }

    @Override
    public boolean isOverlapping(Box boundingBox) {

        Box box = calculateBoundingBox();
        return box.isOverlapping(boundingBox);
    }
}
