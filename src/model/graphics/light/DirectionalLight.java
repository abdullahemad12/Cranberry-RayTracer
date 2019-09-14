package model.graphics.light;

import exceptions.ColorOverflowException;
import model.graphics.Intersection;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;

public class DirectionalLight extends Light {
    private Vector v;
    public DirectionalLight(Vector v, Color c) {
        super(c);
        this.v = v;
    }

    @Override
    public double getDistanceFromLight(Intersection intersection) {
         // infinity
        return Double.MAX_VALUE;
    }

    @Override
    public Ray generateLightRay(Intersection intersection) {
        Vector dir = v.normalize();
        Point p = (Point) intersection.getLocalGeo().getPos().add(0.0001);
        return new Ray(p, dir);
    }

    @Override
    public Color computeLight(Intersection intersection, Vector eyeDir) throws ColorOverflowException {
        LocalGeo lg = intersection.getLocalGeo();
        BRDF brdf = intersection.getShape().getBRDF();
        Normal normal = lg.getNormal();
        assert(Math.abs(normal.magnitude() - 1) < 0.0000001);

        Vector direction = v.normalize();
        Vector halfVec = direction.add(eyeDir).normalize();
        Color phong = computePhongComponent(halfVec, normal, brdf, Double.MAX_VALUE);
        Color lambert = computeLambertComponent(direction, normal, brdf, Double.MAX_VALUE);
        return phong.add(lambert);
    }
}
