package model.graphics.light;

import controllers.scene.RayTracer;
import exceptions.ColorOverflowException;
import exceptions.PointOutOfRangeException;
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
    public double getDistanceFromLight(Intersection intersection) {
        Vector dir = this.position.subtract(intersection.getLocalGeo().getPos());
        return dir.magnitude();
    }

    @Override
    public Ray generateLightRay(Intersection intersection) {

        Vector dir = this.position.subtract(intersection.getLocalGeo().getPos());
        dir = dir.normalize();
        Point pos = intersection.getLocalGeo().getPos();
        Ray tmp = new Ray(pos, dir);

        try{
            pos = tmp.ray(RayTracer.ERROR_EPSILON);
        } catch(PointOutOfRangeException e) {
            e.printStackTrace();
            System.exit(3);
        }

        return new Ray(pos, dir);
    }

    @Override
    public Color computeLight(Intersection intersection, Vector eyeDir) throws ColorOverflowException {
        LocalGeo lg = intersection.getLocalGeo();
        Normal normal = lg.getNormal();
        assert(Math.abs(normal.magnitude() - 1) < 0.0000001);
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
