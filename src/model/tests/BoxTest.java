import model.graphics.Ray;
import model.graphics.object.Box;
import model.math.Point;
import model.math.Vector;
import org.junit.Assert;
import org.junit.Test;

public class BoxTest {

    @Test
    public void boxIntersectionTest1() {
        Box b = new Box(new Point(1, 1, 1), new Point(3, 3, 3));

        Vector direction = new Vector(2, 2, 2).normalize();
        Point center = new Point(0, 0, 0);

        Ray ray = new Ray(center, direction);

        Assert.assertTrue(b.doesIntersect(ray));
    }

    @Test
    public void boxIntersectionTest2() {
        Box b = new Box(new Point(1, 1, 1), new Point(3, 3, 3));

        Vector direction = new Vector(4, 4, 4).normalize();
        Point center = new Point(4, 0, 0);

        Ray ray = new Ray(center, direction);

        Assert.assertFalse(b.doesIntersect(ray));
    }

    @Test
    public void boxIntersectionTest3() {
        Box b = new Box(new Point(1, 1, 1), new Point(3, 3, 3));

        Vector direction = new Vector(2.5, 1.5, 2).normalize();
        Point center = new Point(1, 0.5, 0.5);

        Ray ray = new Ray(center, direction);

        Assert.assertTrue(b.doesIntersect(ray));
    }

    @Test
    public void boxOverlappingTest1() {
        Box b1 = new Box(new Point(1, 1, 1), new Point(3, 3, 3));

        Box b2 = new Box(new Point(2, 2, 2), new Point(2.5, 2.5, 5));

        Assert.assertTrue(b1.isOverlapping(b2));
    }

    @Test
    public void boxOverlappingTest2() {
        Box b1 = new Box(new Point(4, 4, 5), new Point(7, 5, 6));

        Box b2 = new Box(new Point(2, 2, 2), new Point(2.5, 2.5, 5));

        Assert.assertFalse(b1.isOverlapping(b2));
    }

    @Test
    public void boxOverlappingTest3() {
        Box b1 = new Box(new Point(4, 4, 5), new Point(7, 5, 6));

        Box b2 = new Box(new Point(5, 5, 5.5), new Point(9, 20, 50));

        Assert.assertTrue(b1.isOverlapping(b2));

    }

}
