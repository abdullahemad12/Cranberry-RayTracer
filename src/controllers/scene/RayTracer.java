package controllers.scene;

import app.format.Printer;
import exceptions.ColorOverflowException;
import exceptions.PointOutOfRangeException;
import model.graphics.Intersection;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.graphics.Sample;
import model.graphics.light.Light;
import model.graphics.object.AggregateShape;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;

import java.util.List;

/**
 * The ray tracer class is the class where the highlevel work of ray tracing is done. The class is responsible for
 * producing a ray for every given sample and returning a color for that sample to be committed in the film.
 * @author Abdullah Emad
 * @version 1.0
 */
public class RayTracer {

    public static final double ERROR_EPSILON = 0.0001;

    private AggregateShape aggregateShape;
    private List<Light> lights;
    private int maxDepth = 5;
    /**
     * The camera of the world
     */
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


    void addLight(Light light) {
        this.lights.add(light);
    }

    /**
     * Generates a color for a given sample
     * @param sample the sample of interest
     * @return the generated color
     */
    Color trace(Sample sample) {
        Ray ray = camera.generateRay(sample);
        return trace(ray, maxDepth);
    }

    private Color trace(Ray ray, int depth) {

        if(depth == 0) {
            return new Color(0, 0,0);
        }
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
            System.exit(e.EXIT_STATUES);
        }

        assert(intersection != null);

        // Perform standard Color Shading
        Color color = performColorShading(intersection);

        // compute reflection component
        Ray reflectionRay = computeReflectionRay(ray, intersection.getLocalGeo());
        Color reflection = trace(reflectionRay, depth - 1);
        Color ks = intersection.getShape().getBRDF().getSpecular();
        reflection = reflection.multiply(ks);

        try{
            color = color.add(reflection);
        } catch (ColorOverflowException e) {
            try {
                color = color.add(new Color(0, 0, 0));
            } catch(ColorOverflowException e1) {
                Printer.printError("FATAL Error: should never throw this exception!");
                e1.printStackTrace();
                System.exit(e1.EXIT_STATUES);
            }
        }

        return color;

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
            e.printStackTrace();
            System.exit(e.EXIT_STATUES);
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
                System.exit(e.EXIT_STATUES);
            }
        }
        return color;
    }

    /**
     * Computes a reflected ray for reflection computations
     * @param originalRay the original ray to be reflected
     * @param lg the point at which the reflection should start
     * @return the reflected ray
     */
    private Ray computeReflectionRay(Ray originalRay, LocalGeo lg) {
        Vector direction = originalRay.getDir();
        Normal normal = lg.getNormal();

        Vector newDirection = direction.subtract(normal.multiply(2.0 * normal.dot(direction)));
        newDirection = newDirection.normalize();

        Point pos = lg.getPos();
        Ray tmp = new Ray(pos, newDirection);
        try {
            // add epsilon for numerical errors
            pos = tmp.ray(ERROR_EPSILON);
        } catch (PointOutOfRangeException e){
            e.printStackTrace();
            System.exit(e.EXIT_STATUES);
        }

        return new Ray(pos, newDirection);
    }
}
