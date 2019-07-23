package model.math.tests;


import model.math.Point;
import model.math.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PointTest {
    private Point vec1;
    private Point vec2;
    @Before
    public void setUp() {
        vec1 = new Point(12.5, 55.01, 456223.73);
        vec2 = new Point(74.99, 44.923423, 4543.245);
    }

    @Test
    public void creationTest(){
        double x = 1, y = 12.2, z = 33.5643;
        Point p = new Point(x, y, z);
        Assert.assertEquals(x, p.getX(), 0);
        Assert.assertEquals(y, p.getY(), 0);
        Assert.assertEquals(z, p.getZ(), 0);
    }
    @Test
    public void addTest(){
        Vector vec = new Vector(87.49, 99.933423, 460766.975);

        Vector res12 = vec1.add(vec2);
        Vector res21 = vec2.add(vec1);

        Assert.assertEquals(res12.getX(), vec.getX(), 0.000001);
        Assert.assertEquals(res12.getY(), vec.getY(), 0.000001);
        Assert.assertEquals(res12.getZ(), vec.getZ(), 0.000001);

        Assert.assertEquals(res21.getX(), vec.getX(), 0.000001);
        Assert.assertEquals(res21.getY(), vec.getY(), 0.000001);
        Assert.assertEquals(res21.getZ(), vec.getZ(), 0.000001);
    }

    @Test
    public void subtractTest(){
        Vector vec = new Vector(-62.49, 10.086577, 451680.485);

        Vector res12 = vec1.subtract(vec2);
        Vector res21 = vec2.subtract(vec1);

        Assert.assertEquals(res12.getX(), vec.getX(), 0.000001);
        Assert.assertEquals(res12.getY(), vec.getY(), 0.000001);
        Assert.assertEquals(res12.getZ(), vec.getZ(), 0.000001);

        Assert.assertEquals(res21.getX(), -vec.getX(), 0.000001);
        Assert.assertEquals(res21.getY(), -vec.getY(), 0.000001);
        Assert.assertEquals(res21.getZ(), -vec.getZ(), 0.000001);
    }
}