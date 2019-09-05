package model.graphics.object;

import model.graphics.LocalGeo;
import model.graphics.Ray;

public interface Shape {
    LocalGeo intersect(Ray ray);
    boolean doesIntersect(Ray ray);
}
