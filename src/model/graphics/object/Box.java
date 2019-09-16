package model.graphics.object;

import datastructures.OctaTree.BoundingBox;
import datastructures.OctaTree.OctaTreeObject;
import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.math.Point;
import model.math.transformation.Matrix;

public class Box implements Shape, OctaTreeObject {

    private Point A;
    private Point B;

    public Box(Point A, Point B) {
        this.A = A;
        this.B = B;
    }


    @Override
    public boolean isOverlapping(BoundingBox boundingBox) {
        return false;
    }

    @Override
    public LocalGeo intersect(Ray ray) throws PointOutOfRangeException {
        return null;
    }

    @Override
    public boolean doesIntersect(Ray ray) {
        return false;
    }

    @Override
    public void transform(Matrix matrix) {
        A = matrix.transform(A);
        B = matrix.transform(B);
    }

    @Override
    public BRDF getBRDF() {
        return null;
    }
}
