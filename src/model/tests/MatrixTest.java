import model.math.Point;
import model.math.Vector;
import model.math.transformation.Matrix;
import model.math.transformation.Rotation;
import model.math.transformation.Scale;
import model.math.transformation.Translation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixTest extends Matrix{
    public MatrixTest(){
        super(initMat());
    }


    private static double[][] initMat(){
        double[][] mat = new double[4][4];
        mat[0][0] = 133.54;
        mat[0][1] = 155.6;
        mat[0][2] = 12;
        mat[0][3] = 0;
        mat[1][0] = 1.5;
        mat[1][1] = 0.5;
        mat[1][2] = 5;
        mat[1][3] = 0;
        mat[2][0] = 55;
        mat[2][1] = 44;
        mat[2][2] = 5;
        mat[2][3] = 0;
        mat[3][0] = 0;
        mat[3][1] = 0;
        mat[3][2] = 0;
        mat[3][3] = 1;
        return mat;
    }
    private double[][] initInverseTestMat() {
        return new double[][]{{10.4, -4.5, 0, 0},
                                        {55, -12.2, 2, 0},
                                        {0, 77, 78, 0},
                                        {0, 0, 0, 1}};
    }

    private Matrix translate;
    private Matrix rotate;
    private Matrix scale;
    private Matrix inverse;
    private Vector vec;
    private Point point;

    @Before
    public void setUp(){
        this.mat = initMat();
        translate = new Translation(12.5, 15.55, -33);
        rotate = new Rotation(1, 0, 0, 45);
        scale = new Scale(2, 1, 3.3);
        vec = new Vector(5, 12.5, 22.2);
        point = new Point(5, 12.5, 22.2);

    }


    @Test
    public void rotationTest(){
        double cos = Math.cos(Math.toRadians(45));
        double sin = Math.sin(Math.toRadians(45));

        this.mat = new double[][]{{1, 0, 0, 0},
                                             {0, cos, -sin, 0},
                                             {0, sin, cos, 0},
                                             {0, 0, 0, 1}};
        Assert.assertTrue(this.rotate.equals(this));
    }
    @Test
    public void addTest() {
        Matrix res = this.add(scale);
        this.mat = new double[][] {{135.54, 155.6, 12, 0}, {1.5, 1.5, 5, 0}, {55, 44, 8.3, 0}, {0, 0, 0, 1}};
        Assert.assertTrue(res.equals(this));
    }

    @Test
    public void subtractTest() {
        Matrix res = this.subtract(scale);
        this.mat = new double[][] {{131.54, 155.6, 12, 0}, {1.5, -0.5, 5, 0}, {55, 44, 1.7, 0}, {0, 0, 0, 1}};
        Assert.assertTrue(res.equals(this));
    }

    @Test
    public void multiplyTest() {
        Matrix comp = scale.multiply(rotate).multiply(translate);

        this.mat = new double[][]{{2, 0, 0, 25},
                { 0, 0.7071067812, -0.7071067812, 34.33003422726},
                {0, 2.33345237796, 2.33345237796, -40.718743995402},
                {0,0, 0, 1}};

        Assert.assertTrue(comp.equals(this));

    }

    @Test
    public void multiplyTest2() {
        Matrix comp = scale.multiply(rotate).multiply(translate);

        Vector res = comp.multiply(vec);


        Assert.assertEquals(res.getX(), 10.0, 0);
        Assert.assertEquals(res.getY(), -6.858935777509508, 0.00001);
        Assert.assertEquals(res.getZ(), 80.97079751367156, 0.00001);

    }

    @Test
    public void multiplyTest3(){

        Matrix comp = scale.multiply(rotate).multiply(translate);

        Point res = comp.multiply(point);


        Assert.assertEquals(res.getX(), 35.0, 0);
        Assert.assertEquals(res.getY(), 27.47109844962, 0.00001);
        Assert.assertEquals(res.getZ(), 40.25205351981, 0.00001);
    }

    @Test
    public void transposeTest() {
        double[][] expected = new double[][] {{133.54, 1.5, 55, 0}, {155.6, 0.5, 44, 0}, {12, 5, 5, 0}, {0, 0, 0, 1}};
        Matrix trans = this.transpose();

        this.mat = expected;


        Assert.assertTrue(this.equals(trans));

    }

    @Test
    public void invertTest() {
        double[][] expcted = new double[][]{{-0.14162085167214055511, 0.04496103377073203224, -0.0011528470197623598009, 0},
                                            {-0.54952374608672483851, 0.10390994471458069673, -0.0026643575567841204289,0 },
                                            {0.54247856985484375083, -0.10257776593618863652, 0.01545071194708175991, 0},
                                            {0, 0, 0, 1}};
        this.mat = initInverseTestMat();

        Matrix inv = this.invert();
        this.mat = expcted;
        Assert.assertTrue(this.equals(inv));
    }

    @Test
    public void transformTest() {
    }

    @Test
    public void transformNormalTest() {
    }
}