package model.graphics.object;

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

    private HashSet<Shape> shapes;

    public AggregateShape() {
        this.shapes = new HashSet<>();
    }
    public AggregateShape(List<Shape> shapes) {
        this.shapes = new HashSet<>();
        addAllShapes(shapes);
    }

    /**
     * <b>EFFECTS: <b/>Adds a list of shapes to this aggregate shape
     * <b>MODIFIES: <b/>this
     * @param shapes the list of shapes to be added
     */
    public void addAllShapes(List<Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    /**
     * <b>EFFECTS: <b/>Adds one shape to this aggregate shape
     * <b>MODIFIES: <b/>this
     * @param shape the shape to be added
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    /**
     * <b>EFFECTS: <b/>deletes all the shapes from this aggregate shape
     * <b>MODIFIES: <b/> this
      */
    public void clearShapes() {
        shapes.clear();
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
        for(Shape shape : shapes){
            if(shape.doesIntersect(ray)) {
                return true;
            }
        }
        return false;
    }

}
