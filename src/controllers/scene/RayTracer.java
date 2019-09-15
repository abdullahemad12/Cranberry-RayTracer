package controllers.scene;

import app.format.Printer;
import exceptions.ColorOverflowException;
import exceptions.PointOutOfRangeException;
import model.graphics.Intersection;
import model.graphics.Ray;
import model.graphics.Sample;
import model.graphics.light.Light;
import model.graphics.object.AggregateShape;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.graphics.object.Shape;
import model.math.Point;
import model.math.Vector;

import java.util.List;

public class RayTracer {

    private AggregateShape aggregateShape;
    private List<Light> lights;
    private int maxDepth = 5;
    private Camera camera;

    public RayTracer(Camera camera) {
        this.camera = camera;
    }

    void setAggregateShape(AggregateShape aggregateShape) {
        this.aggregateShape = aggregateShape;
    }

    void setLights(List<Light> lights) {
        this.lights = lights;
    }

    void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    void addToAggregateShape(Shape shape) {
        aggregateShape.addShape(shape);
    }

    void addLight(Light light) {
        this.lights.add(light);
    }

    Color trace(Sample sample) {
        Ray ray = camera.generateRay(sample);
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

        // Perform standard Color Shading
        return performColorShading(intersection);
    }

    /**
     * Performs vertex shading on the given intersection through iterating
     * over all the lights and calculating the phong and lambert terms
     *
     * @param intersection the intersection that was computed perviously
     * @return the color produced from vertex Shading
     */
    private Color performColorShading(Intersection intersection) {
        Point p = intersection.getLocalGeo().getPos();
        Vector eyeDir = camera.calculateEyeDirection(p);
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

            // shadowing: cast a ray to the light and check if hits an object in the
            // way, if it does skip this light
          Ray lightRay = light.generateLightRay(intersection);
           if(aggregateShape.doesIntersect(lightRay)) {
                try{
                    Intersection lightIntersection = aggregateShape.intersect(lightRay);
                    if(lightIntersection.getLocalGeo().getThit() < light.getDistanceFromLight(intersection)){
                        continue;
                    }
                } catch (PointOutOfRangeException e) {
                    e.printStackTrace();
                    System.exit(3);
                }
            }


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
