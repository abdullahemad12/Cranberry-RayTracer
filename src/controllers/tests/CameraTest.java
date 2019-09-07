import controllers.Camera;
import model.graphics.Ray;
import model.math.Point;
import model.math.Vector;
import model.screen.Sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CameraTest {

    private Camera camera;

    private static final int width = 1024;
    private static final int height = 512;
    private static final int fovy = 35;
    private Point eye;
    @Before
    public void setUp() {
        Point eye = new Point(0.5, 4, -4);
        Point center = new Point(0, -1, 0);
        Vector up = new Vector(0, 5, 6.125);
        camera = new Camera(eye, center, up, width, height, fovy);
        this.eye = eye;
    }

    @Test
    public void generateRayTest1() {
        int j1 = 0;

        int j2 = 256;

        int j3 = 512;

        int j4 = 786;

        int j5 = 1023;

        int i1 = 0;

        int i2 = 128;

        int i3 = 256;

        int i4 = 384;

        int i5 = 511;


        Vector v1 = new Vector(0.44999670129972635, -0.506355930408552, 0.7355995109836971);
        Vector v2 = new Vector(0.225546332422296, -0.7606032709404, 0.6087787086993234);
        Vector v3 = new Vector(-0.07701951936717581, -0.6717128860511611, 0.7367969817726379);
        Vector v4 = new Vector(-0.38812341860272415, -0.8027578087465711, 0.45270311731700946);
        Vector v5 = new Vector(-0.5766951169874475, -0.7661999358829759, 0.2834791002803521);

        Ray r1E = new Ray(eye, v1);
        Ray r2E = new Ray(eye, v2);
        Ray r3E = new Ray(eye, v3);
        Ray r4E = new Ray(eye, v4);
        Ray r5E = new Ray(eye, v5);


        try {
            Ray r1 = camera.generateRay(new Sample(j1, i1));
            Ray r2 = camera.generateRay(new Sample(j2, i3));
            Ray r3 = camera.generateRay(new Sample(j3, i2));
            Ray r4 = camera.generateRay(new Sample(j4, i4));
            Ray r5 = camera.generateRay(new Sample(j5, i5));

            for(double t = 0; t <= 1.0; t = t + 0.01){
                Assert.assertTrue(r1E.ray(t).equals(r1.ray(t)));
                Assert.assertTrue(r2E.ray(t).equals(r2.ray(t)));
                Assert.assertTrue(r3E.ray(t).equals(r3.ray(t)));
                Assert.assertTrue(r4E.ray(t).equals(r4.ray(t)));
                Assert.assertTrue(r5E.ray(t).equals(r5.ray(t)));
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();

            Assert.fail("This should not throw an Exception");
        }
    }

    @Test
    public void generateRayTest2() {
        try {
            Ray r1 = camera.generateRay(new Sample(width, height));
            Assert.fail("This did not throw an Exception when it was supposed to!");
        }
        catch (IndexOutOfBoundsException e){
              Assert.assertTrue(true);
        }
        catch(Exception e){
            Assert.fail("Should only throw an Index out of bound Exception");
        }
    }
}
