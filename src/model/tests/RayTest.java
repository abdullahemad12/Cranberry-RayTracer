import exceptions.PointOutOfRangeException;
import model.graphics.Ray;
import model.math.Point;
import model.math.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RayTest {

    @Before
    public void setUp(){
        Ray.setTMax(100);
        Ray.setTMin(-100);
    }
    /*
     * Make sure the ray method throws Exception when necessary and only when necessary
     */
    @Test
    public void rayExceptionTest(){
        Vector v = new Vector(3, 5, 6);
        Point p = new Point(1, 0, 1);

        Ray r = new Ray(p, v);

        try {
            r.ray(100.1);
            Assert.fail("Should throw an Exception when given 100.1");
        } catch (PointOutOfRangeException e) {
            Assert.assertTrue(true);
        }

        try{
            r.ray(-100.1);
            Assert.fail("Should throw an Exception when given -100.1");
        } catch (PointOutOfRangeException e){
            Assert.assertTrue(true);

        }

        try{
            r.ray(150);
            Assert.fail("Should throw an Exception when given 150");

        } catch (PointOutOfRangeException e){
            Assert.assertTrue(true);

        }

        try{
            r.ray(150);
            Assert.fail("Should throw an Exception when given -150");

        } catch (PointOutOfRangeException e){
            Assert.assertTrue(true);

        }

        try{
            r.ray(100);
        } catch (PointOutOfRangeException e){
            Assert.fail("Should not throw an Exception when given 100");

        }
        try{
            r.ray(-100);
        } catch (PointOutOfRangeException e){
            Assert.fail("Should not throw an Exception when given -100");

        }
        try{
            r.ray(50);
        } catch (PointOutOfRangeException e){
            Assert.fail("Should not throw an Exception when given 50");

        }
        try{
            r.ray(0);
        } catch (PointOutOfRangeException e){
            Assert.fail("Should not throw an Exception when given 0");

        }
        try{
            r.ray(-50);
        } catch (PointOutOfRangeException e){
            Assert.fail("Should not throw an Exception when given -50");

        }
    }

    @Test
    public void rayTest1(){
        Vector v = new Vector(3, 5.5, 6);
        Point p = new Point(1, 0, -1);

        Ray r = new Ray(p, v);

        double t = 10.2;

        Point expected = new Point(31.6, 56.1, 60.2);

        try {
            Point actual = r.ray(t);
            Assert.assertTrue(expected.equals(actual));

        }catch (PointOutOfRangeException e){
            Assert.assertTrue(true);

        }

    }
    @Test
    public void rayTest2(){
        Vector v = new Vector(3, 5.5, 6);
        Point p = new Point(1, 0, -1);

        Ray r = new Ray(p, v);

        double t = -122.1;

        Point expected = new Point(-365.3, -671.55, -733.6);

        try {
            Point actual = r.ray(t);
            Assert.assertTrue(expected.equals(actual));

        }catch (PointOutOfRangeException e){
            Assert.assertTrue(true);

        }

    }
}
