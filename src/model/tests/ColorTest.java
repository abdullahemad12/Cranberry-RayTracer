import exceptions.ColorOverflowException;
import model.graphics.object.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColorTest {

    private Color color;
    private static final double r = 0.322;
    private static final double g = 0.2;
    private static final double b = 0.4;

    @Before
    public void setUp() {
        color = new Color(r, g, b);
    }


    @Test
    public void colorCreationTest(){
        Assert.assertEquals(color.getR(), r, 0.0001);
        Assert.assertEquals(color.getG(), g, 0.0001);
        Assert.assertEquals(color.getB(), b, 0.0001);

    }


    @Test
    public void colorAdditionTest(){
        Color color2 = new Color(0.5, 0.7, 0.6);

        try {
            color = color.add(color2);
        } catch (ColorOverflowException e) {
            Assert.fail("should not throw an Exception");
        }


        Assert.assertEquals(color.getR(), r + 0.5, 0.001);
        Assert.assertEquals(color.getG(), g + 0.7, 0.001);
        Assert.assertEquals(color.getB(), b + 0.6, 0);

    }



    public void colorAdditionTest1(){
        Color color2 = new Color(0.9, 0.7, 0.6);


        try {
            color.add(color2);
            Assert.fail("Should throw an Exception");
        } catch (ColorOverflowException e) {
            Assert.assertTrue(true);
        }

    }


    public void colorSubtractionTest(){
        Color color2 = new Color(0.5, 0.7, 0.6);

        try {
            color.add(color2);
            Assert.fail("Should throw an Exception");
        } catch (ColorOverflowException e) {
            Assert.assertTrue(true);
        }


    }

}
