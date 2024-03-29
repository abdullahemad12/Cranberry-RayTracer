import controllers.parsing.Parser;
import exceptions.UnknownFileExtensionException;
import model.graphics.ScreenDimensions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {
    private Parser parser;
    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parserTest1(){
        try {
            parser.parseFile("/home/abdullah/Dropbox/OSS/Computer Graphics/Homework3/scene3");
            Assert.fail("Should throw an Exception!");
        } catch(UnknownFileExtensionException e) {
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("should throw an UnknownFileExtension Exception instead");
        }

        try{
            parser.parseFile("/home/abdullah/Dropbox/OSS/Computer Graphics/sece.cbg");
            Assert.fail("should Throw an IOException");
        } catch (IOException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("should throw an IOException Exception instead");
        }
    }
    @Test
    public void parserTest2() {
        try {
            parser.parseFile("scenesTests/scene1.cbg");
        } catch(Exception e) {
            e.printStackTrace();
            Assert.fail("should not throw an Exception");
        }
        ScreenDimensions sd = parser.getScreenDimensions();
        Assert.assertEquals(640, sd.getWidth());
        Assert.assertEquals(480, sd.getHeight());

        double[] camera = parser.getCameraParameters();
        double[] expectedCamera = new double[]{0, -4, 4, 0, -1, 0, 0, 1, 1, 45};
        Assert.assertArrayEquals(expectedCamera, camera, 0);

        Assert.assertEquals(5, parser.getMaxdepth());

        Assert.assertNull(parser.getOutputfilename());

        Assert.assertEquals(66, parser.getShapes().size());


    }

    @Test
    public void parserTest3() {
        try {
            parser.parseFile("scenesTests/scene2.cbg");
            Assert.fail("Should throw an Exception");
        } catch(Exception e) {
            Assert.assertTrue(true);
        }
    }
    @Test
    public void parserTest4() {
        try {
            parser.parseFile("scenesTests/scene3.cbg");
            Assert.fail("Should throw an Exception");
        } catch(Exception e) {
            Assert.assertTrue(true);
        }
    }
    @Test
    public void parserTest5() {
        try {
            parser.parseFile("scenesTests/scene4.cbg");
            Assert.fail("Should throw an Exception");
        } catch(Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void parserTest6() {
        try {
            parser.parseFile("scenesTests/scene5.cbg");
            Assert.fail("Should throw an Exception");
        } catch(Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void parserTest7() {
        try {
            parser.parseFile("scenesTests/scene6.cbg");
        } catch(Exception e) {
            Assert.fail("Should not throw an Exception");
        }

        ScreenDimensions sd = parser.getScreenDimensions();
        Assert.assertEquals(1024, sd.getWidth());
        Assert.assertEquals(512, sd.getHeight());

        Assert.assertEquals(10, parser.getMaxdepth());

        Assert.assertEquals("image.png", parser.getOutputfilename());

        double[] camera = parser.getCameraParameters();
        double[] expectedCamera = new double[]{0, -4.5, 4.12, 0, -1, 0, 0, 1, 1, 40};
        Assert.assertArrayEquals(expectedCamera, camera, 0);

        Assert.assertEquals(2, parser.getLights().size());

    }

}
