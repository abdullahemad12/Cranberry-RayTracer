package model.graphics.object;

import datastructures.OctaTree.Octree;
import exceptions.PointOutOfRangeException;
import model.graphics.Intersection;
import model.graphics.LocalGeo;
import model.graphics.Ray;

import java.util.HashSet;
import java.util.List;

/**
 * The Aggregate shape class stores all the shapes in the world and handles
 * all the intersections for all the shapes. The Ray tracer shall not communicate with the shapes directly
 * but with this class to intersect a ray.
 * @author Abdullah Emad
 * @version 1.0
 */
public class AggregateShape {

    private Octree octree;

    public AggregateShape(List<Shape> shapes) {
        octree = new Octree(shapes);

    }


    /**
     * <b>EFFECTS<b/>Tries to intersect the ray with all the shapes in this aggregate shape
     * and returns the closest one
     * <b>REQUIRES: <b/> doesIntersect to be used first
     * @param ray the ray to be intersected with the shapes
     * @return the closest shape that intersects the ray
     * @throws PointOutOfRangeException when no shape intersects with the
     */
    public Intersection intersect(Ray ray) throws PointOutOfRangeException {

        HashSet<Shape> shapes = octree.search(ray);

        if(shapes == null) {
            throw new PointOutOfRangeException();
        }
        Shape closestShape = null;
        LocalGeo closestLocalGeo = null;

        for(Shape shape : shapes) {
            if(shape.doesIntersect(ray)) {
                LocalGeo lg = shape.intersect(ray);
                if(closestLocalGeo == null) {
                    closestLocalGeo = lg;
                    closestShape = shape;
                }
                else if(lg.getThit() < closestLocalGeo.getThit()) {
                    closestLocalGeo = lg;
                    closestShape = shape;
                }
            }
        }
        if(closestLocalGeo == null) {
            throw new PointOutOfRangeException();
        }
        return new Intersection(closestShape, closestLocalGeo);
    }

    /**
     * Checks if the given ray intersects with any object
     * @param ray the ray to be intersects
     * @return true if the ray intersects with any object, false otherwise
     */
    public boolean doesIntersect(Ray ray){
        HashSet<Shape> shapes = octree.search(ray);
        if(shapes == null) {
            return false;
        }
        for(Shape shape : shapes){
            if(shape.doesIntersect(ray)) {
                return true;
            }
        }
        return false;
    }

}
