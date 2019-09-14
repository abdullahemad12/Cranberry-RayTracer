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

public class PointLight extends Light {

    private Point position;

    public PointLight(Point position, Color c) {
        super(c);
        this.position = position;
    }

    @Override
    public Ray generateLightRay(Intersection intersection) {
        return null;
    }

    @Override
    public Color computeLight(Intersection intersection, Vector eyeDir) throws ColorOverflowException {
        LocalGeo lg = intersection.getLocalGeo();
        Normal normal = lg.getNormal();
        Point myPos = lg.getPos();
        BRDF brdf = intersection.getShape().getBRDF();

        Vector direction = position.subtract(myPos);
        double distance = direction.magnitude();

        direction = direction.normalize();
        Vector halfVec = direction.add(eyeDir).normalize();

        Color phong = super.computePhongComponent(halfVec, normal, brdf, distance);
        Color lambert = super.computeLambertComponent(direction, normal, brdf, distance);
        return phong.add(lambert);
    }
}
