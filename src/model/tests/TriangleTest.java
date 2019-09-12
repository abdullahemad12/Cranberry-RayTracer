import controllers.scene.Camera;
import exceptions.InvalidSampleException;
import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.graphics.ScreenDimensions;
import model.graphics.object.Triangle;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;
import model.graphics.Sample;
import controllers.scene.Sampler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TriangleTest {
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
    public void triangleIntersectionTest() throws InvalidSampleException {

        int startW = (width / 2) - 1;
        int endW = (width / 2) + 1;

        int startH = (height / 2) - 1;
        int endH = (height / 2) + 1;

        Point A = new Point(5, 0, 4);
        Point B = new Point(5, 0, -4);
        Point C = new Point(5, 5, 2);


        Triangle triangle = new Triangle(A, B, C, null);


        for(int i = startW; i < endW; i++){
            for(int j = startH; j < endH; j++) {
                Sample sample = new Sample(i, j);

                Ray ray = camera.generateRay(sample);
                Point expected_p = null;
                try {
                    expected_p = ray.ray(10.00001);
                }
                catch(PointOutOfRangeException e){
                    Assert.fail("Should not throw an Exception");
                }

                Normal expected_normal = new Normal(-1, 0, 0);

                Assert.assertTrue(triangle.doesIntersect(ray));

                try {
                    LocalGeo lg = triangle.intersect(ray);
                    Assert.assertNotNull(lg);
                    boolean normalEq = lg.getNormal().equals(expected_normal);
                    Assert.assertTrue(normalEq);
                    boolean posEq = lg.getPos().equals(expected_p);
                    Assert.assertTrue(posEq);
                } catch(PointOutOfRangeException e) {
                    Assert.fail("This should not throw an Exception!");
                }

            }
        }


    }

    @Test
    public void triangleIntersectionTest1() throws InvalidSampleException {
        Point A = new Point(5, 0, 4);
        Point B = new Point(5, 0, -4);
        Point C = new Point(5, 5, 2);


        Triangle triangle = new Triangle(A, B, C, null);


        Sample sample;
        Ray ray;

        sample = new Sample(0, 0);

        ray = camera.generateRay(sample);

        Assert.assertFalse(triangle.doesIntersect(ray));

        try{
            triangle.intersect(ray);
            Assert.fail("Should throw an Exception because the ray does not intersect");
        }
        catch (PointOutOfRangeException e) {
            Assert.assertTrue(true);
        }

        sample = new Sample(width - 1, height - 1);


        ray = camera.generateRay(sample);

        Assert.assertFalse(triangle.doesIntersect(ray));

        try{
            triangle.intersect(ray);
            Assert.fail("Should throw an Exception because the ray does not intersect");
        }
        catch (PointOutOfRangeException e) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void triangleNonIntersectionTest() throws InvalidSampleException {
        // this triangle is parallel to the eye location
        Point A = new Point(10, 0, 0);
        Point B = new Point(0, 0, 0);
        Point C = new Point(5, 5, 0);

        Triangle triangle = new Triangle(A, B, C, null);


        Sampler sampler = new Sampler(new ScreenDimensions(width, height));

        for(Sample sample : sampler) {
            Ray ray = camera.generateRay(sample);

            Assert.assertFalse("Intersects at pixel (" +  sample.getX() + ", " + sample.getY() + ")", triangle.doesIntersect(ray));

            try{
                triangle.intersect(ray);
                Assert.fail("Should throw an Exception since the ray does not intersect with the triangle");
            } catch(PointOutOfRangeException e) {
                Assert.assertTrue(true);
            }

        }


    }
}
