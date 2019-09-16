package model.graphics.object;

import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.math.transformation.Matrix;

/**
 * The Shape interface provides the signature of the neccessary functions that are needed by the ray tracer
 * to draw any shape.
 * The shape Interface should be implemented by any class the represents an object in the
 * world
 * @author Abdullah Emad
 * @version 1.0
 */
public interface Shape {
    /**
     * The intersect function calculates the intersection of ray with this shape. It is recommended
     * to check if the ray actually intersects with this Shape using doesIntersect() before calling this method
     *
     * @param ray the ray that should be intersected by the object
     * @return LocalGeo containing information of the intersection
     * @throws PointOutOfRangeException if the ray does not intersect
     */
    LocalGeo intersect(Ray ray) throws PointOutOfRangeException;

    /**
     * Checks if the given ray intersects with this Shape
     *
     * @param ray the ray to be intersected
     * @return true if the ray intersects with this shape, false otherwise
     */
    boolean doesIntersect(Ray ray);

    /**
     * Performs the given transformation on this shape
     *
     * @param matrix the matrix that represents the desired transformation
     */
    void transform(Matrix matrix);

    /**
     * Getter for the BRDF
     *
     * @return the BRDF of the shape
     */
    BRDF getBRDF();

    /**
     * computes the bounding box of this Shape
     *
     * @return the bounding box as a Box
     */
    Box calculateBoundingBox();

    /**
     * Checks if the bounding box of the shape overlaps with the given bounding box
     *
     * @param boundingBox the bounding box to be checked
     * @return true if it overlaps, false otherwise
     */
    boolean isOverlapping(Box boundingBox);
}
