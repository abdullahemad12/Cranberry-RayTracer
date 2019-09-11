package model.graphics.light;

import model.graphics.object.Color;
import model.math.Point;

public class PointLight extends Light {

    private Point p;

    public PointLight(Point p, Color c) {
        super(c);
        this.p = p;
    }
}
