package model.graphics.object;

import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;

public interface Shape {
    LocalGeo intersect(Ray ray) throws PointOutOfRangeException;
    boolean doesIntersect(Ray ray);
    BRDF getBRDF();
}
