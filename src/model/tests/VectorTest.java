import model.math.Vector;
import org.junit.*;

public class VectorTest {
    private Vector vec1;
    private Vector vec2;

    @Before
    public void setUp(){
        vec1 = new Vector(12.5, 55.01, 456223.73);
        vec2 = new Vector(74.99, 44.923423, 4543.245);
    }
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
        double ExpectedAns = 2072739589;

        double ActualAns = vec1.dot(vec2);

        Assert.assertEquals(ExpectedAns, ActualAns, 0.9);

        ActualAns = vec2.dot(vec1);

        Assert.assertEquals(ExpectedAns, ActualAns, 0.9);

    }


    @Test
    public void crossProduct(){
        Vector vec12 = new Vector(-20245207.7, 34155426.95, -3563.657113);
        Vector vec21 = new Vector(20245207.7, -34155426.95, 3563.657113);

        Vector res12 = vec1.cross(vec2);
        Vector res21 = vec2.cross(vec1);

        Assert.assertEquals(res12.getX(), vec12.getX(), 0.01);
        Assert.assertEquals(res12.getY(), vec12.getY(), 0.01);
        Assert.assertEquals(res12.getZ(), vec12.getZ(), 0.01);

        Assert.assertEquals(res21.getX(), vec21.getX(), 0.01);
        Assert.assertEquals(res21.getY(), vec21.getY(), 0.01);
        Assert.assertEquals(res21.getZ(), vec21.getZ(), 0.01);

    }

    @Test
    public void addVector(){
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
    public void subtractVector(){
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

    @Test
    public void muliplyScalar(){
        Vector exp1 = new Vector(252.75, 1112.3022, 9224843.821);
        Vector exp2 = new Vector(1516.2978, 908.3516131, 91864.4139);

        double scalar = 20.22;
        Vector res1 = vec1.multiply(scalar);
        Vector res2 = vec2.multiply(scalar);

        Assert.assertEquals(exp1.getX(), res1.getX(), 0.0001);
        Assert.assertEquals(exp1.getY(), res1.getY(), 0.0001);
        Assert.assertEquals(exp1.getZ(), res1.getZ(), 0.001);

        Assert.assertEquals(exp2.getX(), res2.getX(), 0.0001);
        Assert.assertEquals(exp2.getY(), res2.getY(), 0.0001);
        Assert.assertEquals(exp2.getZ(), res2.getZ(), 0.0001);

    }




    @Test
    public void divideScalar(){
        Vector exp1 = new Vector(0.6181998022, 2.720573689, 22562.99357);
        Vector exp2 = new Vector(3.708704253, 2.221732097, 224.6906528);


        double scalar = 20.22;
        Vector res1 = vec1.divide(scalar);
        Vector res2 = vec2.divide(scalar);

        Assert.assertEquals(exp1.getX(), res1.getX(), 0.000001);
        Assert.assertEquals(exp1.getY(), res1.getY(), 0.000001);
        Assert.assertEquals(exp1.getZ(), res1.getZ(), 0.000001);

        Assert.assertEquals(exp2.getX(), res2.getX(), 0.000001);
        Assert.assertEquals(exp2.getY(), res2.getY(), 0.000001);
        Assert.assertEquals(exp2.getZ(), res2.getZ(), 0.000001);

    }


    @Test
    public void magnitude(){
        double exp1 = 456223.7335;
        double exp2 = 4544.085909;

        double res1 = vec1.magnitude();
        double res2 = vec2.magnitude();

        Assert.assertEquals(exp1, res1, 0.0001);
        Assert.assertEquals(exp2, res2, 0.0001);
    }
    @Test
    public void normalize(){
        Vector exp1 = new Vector(0.0000274, 0.00012058, 1);
        Vector exp2 = new Vector(0.016503, 0.009886, 0.9998);

        Vector res1 = vec1.normalize();
        Vector res2 = vec2.normalize();

        Assert.assertEquals(exp1.getX(), res1.getX(), 0.0001);
        Assert.assertEquals(exp1.getY(), res1.getY(), 0.0001);
        Assert.assertEquals(exp1.getZ(), res1.getZ(), 0.0001);

        Assert.assertEquals(exp2.getX(), res2.getX(), 0.0001);
        Assert.assertEquals(exp2.getY(), res2.getY(), 0.0001);
        Assert.assertEquals(exp2.getZ(), res2.getZ(), 0.0001);
    }
}