package model.graphics.light;

import exceptions.ColorOverflowException;
import model.graphics.Intersection;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.math.Normal;
import model.math.Vector;

public class DirectionalLight extends Light {
    private Vector v;
    public DirectionalLight(Vector v, Color c) {
        super(c);
        this.v = v;
    }

    @Override
    public Ray generateLightRay(Intersection intersection) {
        return null;
    }

    @Override
    public Color computeLight(Intersection intersection, Vector eyeDir) throws ColorOverflowException {
        LocalGeo lg = intersection.getLocalGeo();
        BRDF brdf = intersection.getShape().getBRDF();
        Normal normal = lg.getNormal();

        Vector direction = v.normalize();
        Vector halfVec = direction.add(eyeDir).normalize();
        Color phong = computePhongComponent(halfVec, normal, brdf, Double.MAX_VALUE);
        Color lambert = computeLambertComponent(direction, normal, brdf, Double.MAX_VALUE);
        return phong.add(lambert);
    }
}
