package controllers.scene;

import app.format.Printer;
import exceptions.ColorOverflowException;
import exceptions.PointOutOfRangeException;
import model.graphics.Intersection;
import model.graphics.Ray;
import model.graphics.light.Light;
import model.graphics.object.AggregateShape;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.math.Vector;

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
    Intersection trace(Ray ray) {
        return trace(ray, maxDepth);
    }

    private Intersection trace(Ray ray, int depth) {

        // no Intersection with any object, return a black color
        if(!aggregateShape.doesIntersect(ray)) {
            return null;
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
        return intersection;
    }

    /**
     * Performs vertex shading on the given intersection through iterating
     * over all the lights and calculating the phong and lambert terms
     *
     * @param intersection the intersection that was computed perviously
     * @param eyeDir the direction from the intersection to the eye
     * @return the color produced from vertex Shading
     */
    Color performColorShading(Intersection intersection, Vector eyeDir) {
        BRDF brdf = intersection.getShape().getBRDF();
        Color color = null;
        try {
            color = brdf.getAmbient().add(brdf.getEmission());
        } catch(ColorOverflowException e) {
            Printer.printError("Fatal Error: ");
            e.printStackTrace();
            System.exit(4);
        }
        for(Light light : lights) {
            try {
                color = color.add(light.computeLight(intersection, eyeDir));
            } catch(ColorOverflowException e) {
                Printer.printError("Fatal Error: ");
                e.printStackTrace();
                System.exit(4);
            }
        }
        return color;
    }
}
