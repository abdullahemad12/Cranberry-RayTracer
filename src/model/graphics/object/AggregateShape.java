package model.graphics.object;

import exceptions.PointOutOfRangeException;
import model.graphics.Intersection;
import model.graphics.LocalGeo;
import model.graphics.Ray;

import java.util.HashSet;
import java.util.List;

public class AggregateShape {

    private HashSet<Shape> shapes;

    public AggregateShape() {
        this.shapes = new HashSet<>();
    }
    public AggregateShape(List<Shape> shapes) {
        this.shapes = new HashSet<>();
        addAllShapes(shapes);
    }
    public void addAllShapes(List<Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void clearShapes() {
        shapes.clear();
    }

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

    public boolean doesIntersect(Ray ray){
        for(Shape shape : shapes){
            if(shape.doesIntersect(ray)) {
                return true;
            }
        }
        return false;
    }

}
