package model.graphics.object;

import datastructures.OctaTree.BoundingBox;
import datastructures.OctaTree.OctaTreeObject;
import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;
import model.math.transformation.Matrix;

/**
 * The Triangle class represents a triangle in the world. The triangle is characterised by
 * three different point at which a connection will be drawn.
 *
 * <\br> <b>IMPORTANT: <b/> the three points A, B, C must be defined in counter clockwise direction
 * following the right hand rule in order for tracing and shading to work correctly
 *
 * @author Abdullah Emad
 * @version 1.0
 */
public class Triangle implements Shape, OctaTreeObject {

    /**
     * First point of the triangle
     */
    private Point A;
    /**
     * Second point of the triangle
     */
    private Point B;
    /**
     * third point of the triangle
     */
    private Point C;

    /**
     * The Material color of the triangle
     */
    private BRDF brdf;

    /**
     *
     * @param A first Point A
     * @param B second Point B
     * @param C third Point C
     * @param brdf the brdf material
     */
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
        Normal n = B.subtract(A).cross(C.subtract(A)).normalize();

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
        A = matrix.transform(A);
        B = matrix.transform(B);
        C = matrix.transform(C);
    }

    @Override
    public BRDF getBRDF() {
        return this.brdf;
    }

    @Override
    public boolean isOverlapping(BoundingBox boundingBox) {
        double minX = min(A.getX(), B.getX(), C.getX());
        double minY = min(A.getY(), B.getY(), C.getY());
        double minZ = min(A.getZ(), B.getZ(), C.getZ());

        double maxX = max(A.getX(), B.getX(), C.getX());
        double maxY = max(A.getY(), B.getY(), C.getY());
        double maxZ = max(A.getZ(), B.getZ(), C.getZ());

        Box b = new Box(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
        return b.isOverlapping(boundingBox);
    }

    private static double min(double a, double b, double c) {
        return Double.min(a, Double.min(b, c));
    }

    private static double max(double a, double b, double c) {
        return Double.max(a, Double.max(b, c));
    }
}
