package controllers.scene;

import exceptions.PointOutOfRangeException;
import model.graphics.Intersection;
import model.graphics.Ray;
import model.graphics.light.Light;
import model.graphics.object.AggregateShape;
import model.graphics.object.Color;

import java.util.List;

public class RayTracer {

    private AggregateShape aggregateShape;
    private List<Light> lights;
    private int maxDepth;

    public RayTracer(AggregateShape aggregateShape, List<Light> lights, int maxDepth) {
        this.aggregateShape = aggregateShape;
        this.lights = lights;
        this.maxDepth = maxDepth;
    }
    Color trace(Ray ray) {
        return trace(ray, maxDepth);
    }

    private Color trace(Ray ray, int depth) {

        // no Intersection with any object, return a black color
        if(!aggregateShape.doesIntersect(ray)) {
            return new Color(0, 0, 0);
        }
        Intersection intersection = null;
        try{
            intersection = aggregateShape.intersect(ray);
        } catch (PointOutOfRangeException e) {
            // at this point there must be an implementation error
            // thus print stack trace and terminate the program

            e.printStackTrace();
            System.exit(1);
        }

        assert(intersection != null);

        // for now return the ambient color of the intersected shape
        return intersection.getShape().getBRDF().getAmbient();
    }
}
