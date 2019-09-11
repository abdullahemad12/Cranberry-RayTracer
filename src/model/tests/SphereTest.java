import controllers.scene.Camera;
import exceptions.InvalidSampleException;
import exceptions.PointOutOfRangeException;
import model.graphics.LocalGeo;
import model.graphics.Ray;
import model.graphics.object.Sphere;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;
import model.graphics.Sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SphereTest {

    private Sphere sphere;
    private Camera camera;
    private Point eye;
    private Point center;

    private static final int width = 1024;
    private static final int height = 1024;
    private static final int fovy = 45;

    @Before
    public void setUp() {
        Point center = new Point(5, 0, 0);
        Point eye = new Point(-5, 0, 0);
        Vector up = new Vector(0, 5, 0);

        sphere = new Sphere(center, 2.2, null);

        camera = new Camera(eye,  center,  up,  width,  height,  fovy);

        this.eye = eye;
        this.center = center;
    }


    @Test
    public void SphereIntersectionTest1() throws InvalidSampleException {


        int startW = (width / 2) - 1;
        int endW = (width / 2) + 1;

        int startH = (height / 2) - 1;
        int endH = (height / 2) + 1;


        for(int i = startW ; i <= endW; i++) {
            for(int j = startH; j <= endH; j++) {
                Sample sample = new Sample(i, j);
                Ray ray = camera.generateRay(sample);
                Assert.assertTrue(sphere.doesIntersect(ray));

                LocalGeo lg = null;


                try {
                    lg = sphere.intersect(ray);
                } catch (PointOutOfRangeException e) {
                    Assert.fail("Should Intersect!");
                }

                Assert.assertNotNull(lg);

                double t_excpected = 7.8;

                try {
                    Point pos_expected = ray.ray(t_excpected);


                    Vector n = pos_expected.subtract(center).normalize();
                    Assert.assertEquals(lg.getPos(), (pos_expected));

                    Assert.assertEquals(lg.getNormal(), n);

                } catch(PointOutOfRangeException e) {
                    Assert.fail("Should not throw a PointOutOfRangeException!");
                }



            }
        }

    }
    @Test
    public void SphereIntersectionTest2() throws InvalidSampleException {
        this.sphere = new Sphere(this.eye, 2.2, null);

        Sample s = new Sample((width / 2) + 1, (height / 2) + 1);


        Ray ray = camera.generateRay(s);

        Assert.assertTrue(sphere.doesIntersect(ray));

        double t_expected = 2.2;

        try {
            Point pos_expected = ray.ray(t_expected);

            LocalGeo lg = sphere.intersect(ray);
            Normal n = pos_expected.subtract(eye).normalize();


            Assert.assertEquals(lg.getNormal(), (n));
            Assert.assertEquals(lg.getPos(), (pos_expected));

        } catch (PointOutOfRangeException e) {
            Assert.fail("Should not throw a PointOutOfRangeException!");
        }


    }


    @Test
    public void SphereIntersectionTest3() throws Exception {
      Sample s = new Sample(2,3);

      Ray ray = camera.generateRay(s);
      try{
          sphere.intersect(ray);
          Assert.fail("Should throw a Point Out of Range Exception!");
      } catch(PointOutOfRangeException e) {
          Assert.assertTrue(true);
      }
      catch(Exception e) {
          Assert.fail("Should only throw a pointOutOfRangeException");
      }

      Assert.assertFalse(sphere.doesIntersect(ray));

    }
}


