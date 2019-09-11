package model.graphics.light;

import model.graphics.object.Color;
import model.math.Vector;

public class DirectionalLight extends Light {
    private Vector v;
    public DirectionalLight(Vector v, Color c) {
        super(c);
        this.v = v;
    }
}
