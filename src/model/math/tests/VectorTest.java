package model.math.tests;


import model.math.Vector;
import org.junit.*;

public class VectorTest {

    @Test
    public void vectorCreation(){
        double x = 1, y = 12.2, z = 33.5643;
        Vector vec = new Vector(x, y, z);
        Assert.assertEquals(x, vec.getX(), 0);
        Assert.assertEquals(y, vec.getY(), 0);
        Assert.assertEquals(z, vec.getZ(), 0);
    }

    @Test
    public void dotProduct(){
        Vector vec1 = new Vector(12.5, 55.01, 456223.73);
        Vector vec2 = new Vector(74.99, 44.923423, 4543.245);
        double ans = 0;
    }
}