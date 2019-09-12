package controllers.scene;

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
        return null;
    }
}
