package model.graphics;

import model.math.Normal;
import model.math.Point;

public class LocalGeo {

    private double thit;
    private Point pos;
    private Normal normal;

    public LocalGeo(double thit, Point pos, Normal normal){
        this.thit = thit;
        this.pos = pos;
        this.normal = normal;
    }


    public double getThit() {
        return this.thit;
    }

    public Point getPos() {
        return this.pos;
    }

    public Normal getNormal() {
        return this.normal;
    }
}
