import controllers.scene.Camera;
import exceptions.InvalidSampleException;
import exceptions.PointOutOfRangeException;
import model.graphics.*;
import model.graphics.object.AggregateShape;
import model.graphics.object.Shape;
import model.graphics.object.Sphere;
import model.graphics.object.Triangle;
import model.math.Point;
import model.math.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class AggregateShapeTest {
    private Camera camera;
    private Point eye;
    private Point center;
    private Vector up;

    private static final int width = 1024;
    private static final int height = 1024;
    private static final int fovy = 45;

    @Before
    public void setUp() {
        center = new Point(5, 0, 0);
        eye = new Point(-5, 0, 0);
        up = new Vector(0, 5, 0);

        camera = new Camera(eye, center, up, new ScreenDimensions(width, height), fovy);

    }


    @Test
    public void AggregateShapeTest1() throws InvalidSampleException {
        AggregateShape as = new AggregateShape();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Sample sample = new Sample(i, j);
                Ray ray = camera.generateRay(sample);
                Assert.assertFalse(as.doesIntersect(ray));
                try {
                    as.intersect(ray);
                    Assert.fail("Should throw an Exception when no Intersection Occured!");
                } catch(PointOutOfRangeException e) {
                    Assert.assertTrue(true);
                }

            }
        }
        Sample sample = new Sample(100, 400);
    }

    @Test
    public void AggregateShapeTest2() throws InvalidSampleException {
        AggregateShape as = new AggregateShape();
        Sphere sphere = new Sphere(center, 2.2, null);

        as.addShape(sphere);

        Sample sample = new Sample((width/ 2) + 1, (height / 2) + 1);
        Ray ray = camera.generateRay(sample);

        Assert.assertTrue(as.doesIntersect(ray));

        try {
            Intersection intersection = as.intersect(ray);

            Assert.assertEquals(sphere, intersection.getShape());
            LocalGeo lg = sphere.intersect(ray);

            Assert.assertEquals(intersection.getLocalGeo().getPos(), lg.getPos());
            Assert.assertEquals(intersection.getLocalGeo().getNormal(), lg.getNormal());
        }catch (PointOutOfRangeException e) {
            Assert.fail("Should not throw exception but return an Intersection");
        }
    }

    @Test
    public void AggregateShapeTest3() throws InvalidSampleException {
        AggregateShape as = new AggregateShape();
        List<Shape> shapes = new LinkedList<>();
        for(int i = 0; i < 50 ; i++){
            Point p = new Point(center.getX() + i, center.getY(), center.getZ());
            Sphere sphere = new Sphere(p, 2.2, null);
            as.addShape(sphere);
            shapes.add(sphere);
        }

        Sample sample = new Sample(width / 2, height / 2);
        Ray ray = camera.generateRay(sample);

        Assert.assertTrue(as.doesIntersect(ray));

        try {
            Intersection intersection = as.intersect(ray);
            Assert.assertEquals(intersection.getShape(), shapes.get(0));
            LocalGeo lg = shapes.get(0).intersect(ray);
            Assert.assertEquals(lg, (intersection.getLocalGeo()));
        }catch(PointOutOfRangeException e) {
            Assert.fail("Should not throw an Exception");
        }
    }

    @Test
    public void AggregateShapeTest4() throws InvalidSampleException{
        AggregateShape as = new AggregateShape();
        List<Shape> shapes = new LinkedList<>();

        Shape targetShape = null;
        for(int i = 50; i >= 0 ; i--){
            Shape shape;
            if(i % 2 != 0) {
                Point p = new Point(center.getX() + i, center.getY(), center.getZ());
                shape = new Sphere(p, 0.5, null);
            }
            else {
                Point A = new Point(5 + i, 0, 4);
                Point B = new Point(5 + i, 0, -4);
                Point C = new Point(5 + i, 5, 2);

                shape = new Triangle(A, B, C, null);
            }
            targetShape = shape;
            shapes.add(shape);
        }

        as.addAllShapes(shapes);

        Sample sample = new Sample(width / 2, height / 2);

        Ray ray = camera.generateRay(sample);

        Assert.assertTrue(as.doesIntersect(ray));

        try {
            Intersection intersection = as.intersect(ray);
            Assert.assertEquals(targetShape, intersection.getShape());
            LocalGeo lg = targetShape.intersect(ray);
            Assert.assertEquals(lg, (intersection.getLocalGeo()));
        }catch (PointOutOfRangeException e) {
            Assert.fail("should not throw an Exception Here");
        } catch(Exception e){
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void AggregateShapeTest5() throws InvalidSampleException {
        List<Shape> shapes = new LinkedList<>();
        for(int i = 50; i >= 0 ; i--){
            Point p = new Point(center.getX() + i, center.getY(), center.getZ());
            Shape shape;
            if(i % 2 != 0) {
                shape = new Sphere(p, 0.5, null);
            }
            else {
                Point A = new Point(5 + i, 0, 4);
                Point B = new Point(5 + i, 0, -4);
                Point C = new Point(5 + i, 5, 2);

                shape = new Triangle(A, B, C, null);
            }


            shapes.add(shape);
        }

        AggregateShape as = new AggregateShape(shapes);

        Sample sample = new Sample(0, 0);

        Ray ray = camera.generateRay(sample);

        Assert.assertFalse(as.doesIntersect(ray));

        try {
            Intersection intersection = as.intersect(ray);
            Assert.fail("Should throw an Exception at this point");
        }catch (PointOutOfRangeException e) {
            Assert.assertTrue(true);
        }

    }
}
