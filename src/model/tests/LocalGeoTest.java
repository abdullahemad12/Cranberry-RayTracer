import model.graphics.LocalGeo;
import model.math.Normal;
import model.math.Point;
import model.math.Vector;
import org.junit.Assert;
import org.junit.Test;

public class LocalGeoTest {

    @Test
    public void LocalGeoTest1(){
        double thit = 0.8;
        Normal n = new Vector(5.5, 6, 100.5).normalize();
        Point pos = new Point(77.12, 44.5, 2);
        LocalGeo lg = new LocalGeo(thit, pos, n);


        Assert.assertEquals(n, (lg.getNormal()));
        Assert.assertEquals(pos, (lg.getPos()));
        Assert.assertEquals(lg.getThit(), thit, 0.0000001);
    }

}
