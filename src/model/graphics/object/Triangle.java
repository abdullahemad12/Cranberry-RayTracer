package model.graphics.object;

import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;
import model.math.transformation.Matrix;

public class Triangle implements Shape{

    private Point A;
    private Point B;
    private Point C;
    private BRDF brdf;

    public Triangle(Point A, Point B, Point C, BRDF brdf) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.brdf = brdf;
    }

    @Override
    public LocalGeo intersect(Ray ray) throws PointOutOfRangeException {
        /*           (C - A) x (B - A)
         * n =     --------------------
         *          |(C - A) x (B - A)|
         */
        Normal n = C.subtract(A).cross(B.subtract(A)).normalize();

        Point zeros = new Point(0, 0, 0);
        Point p0 = ray.getPos();
        Vector p1 = ray.getDir();
        double tn =   A.subtract(zeros).dot(n) - p0.subtract(zeros).dot(n);
        double td = p1.dot(n);

        /*no Intersection possible*/
        if(td == 0) {
            throw new PointOutOfRangeException();
        }
        double t = tn / td;

        // will throw an Exception if t is out of range
        Point p = ray.ray(t);

        /*
         * at this point the ray intersect with the plane of the triangle
         * and the intersection is in front of the camera, now it is time to
         * check if the intersection is actually inside the triangle using the
         * barycentric coordinates
         */

        double ABC = B.subtract(A).cross(C.subtract(A)).magnitude() / 2.0;
        double ABP = A.subtract(p).cross(B.subtract(p)).magnitude() / 2.0;
        double ACP = A.subtract(p).cross(C.subtract(p)).magnitude() / 2.0;
        double BCP = B.subtract(p).cross(C.subtract(p)).magnitude() / 2.0;


        double alpha = ABP / ABC;
        double beta = ACP / ABC;
        double gamma = BCP / ABC;


        if(alpha > 1 || beta > 1 || gamma > 1) {
            throw new PointOutOfRangeException();
        }

        if(alpha < 0 || beta < 0 || gamma < 0) {
            throw new PointOutOfRangeException();
        }
        // Allow for some epsilon error (0.0001)
        if(Math.abs((alpha + gamma + beta) - 1) > 0.0001){
            throw new PointOutOfRangeException();
        }


        return new LocalGeo(t, p, n);
    }

    @Override
    public boolean doesIntersect(Ray ray) {
        try {
            intersect(ray);
        }
        catch (PointOutOfRangeException e){
            return false;
        }

        return true;
    }

    @Override
    public void transform(Matrix matrix) {
        Vector newA = A.subtract(new Point(0, 0, 0));
        Vector newB = B.subtract(new Point(0, 0, 0));
        Vector newC = C.subtract(new Point(0,0, 0));

        newA = matrix.transform(newA);
        newB = matrix.transform(newB);
        newC = matrix.transform(newC);

        A = new Point(newA.getX(), newA.getY(), newA.getZ());
        B = new Point(newB.getX(), newB.getY(), newB.getZ());
        C = new Point(newC.getX(), newC.getY(), newC.getZ());
    }

    @Override
    public BRDF getBRDF() {
        return this.brdf;
    }
}
