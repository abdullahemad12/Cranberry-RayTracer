package model.tests;

import model.graphics.LocalGeo;
import model.math.Normal;
import model.math.Point;
import org.junit.Assert;
import org.junit.Test;

public class LocalGeoTest {

    @Test
    public void LocalGeoTest(){
        double thit = 0.8;
        Normal n = new Normal(5.5, 6, 100.5);
        Point pos = new Point(77.12, 44.5, 2);
        LocalGeo lg = new LocalGeo(thit, pos, n);


        Assert.assertTrue(n.equals(lg.getNormal()));
        Assert.assertTrue(pos.equals(lg.getPos()));
        Assert.assertEquals(lg.getThit(), thit, 0.0000001);
    }

}
