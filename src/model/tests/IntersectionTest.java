import model.graphics.Intersection;
import model.graphics.LocalGeo;
import model.graphics.object.Shape;
import model.graphics.object.Sphere;
import model.math.Point;
import model.math.Vector;
import org.junit.Assert;
import org.junit.Test;

public class IntersectionTest {

    @Test
    public void IntersectionCreationTest(){
        LocalGeo lg = new LocalGeo(10 , new Point(10 ,4, 5), new Vector(1, 0 ,0).normalize());
        Shape shape = new Sphere(new Point(0, 10, 20), 10, null);

        Intersection intersection = new Intersection(shape, lg);

        Assert.assertEquals(lg, intersection.getLocalGeo());
        Assert.assertEquals(shape, intersection.getShape());
    }
}
