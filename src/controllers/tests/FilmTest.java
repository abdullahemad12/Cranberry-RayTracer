import controllers.Film;
import exceptions.IncompleteImageException;
import exceptions.InvalidSampleException;
import model.graphics.object.Color;
import model.screen.Sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FilmTest {
    private Film film;
    private static final int width = 1000;
    private static final int height = 500;

    @Before
    public void setUp() {
        film = new Film(width, height);
    }

    @Test
    public void writeImageTest1() {
        try{
            film.writeImage();
            Assert.fail("should thrown an Exception");
        }
        catch(IncompleteImageException e){
            Assert.assertTrue(true);
        }
        catch(Exception e){

            Assert.fail("Should not throw this exception: \n" + e.getMessage());
        }
    }

    @Test
    public void writeImageTest2() throws InvalidSampleException {

        // genarate a picture with white background and red square inside
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Color color ;
                if(     (j >= 200 && j <= 300 && i >= 80 && i <= 420) ||
                        (j >= 700 && j <= 800 && i >= 80 && i <= 420) ||
                        (i >= 80 && i <= 180 && j >= 200 && j <= 800) ||
                        (i >= 320 && i <= 420 && j >= 200 && j <= 800)){
                   color = new Color(1,  0 , 0.2);
                }
                else {
                    color = new Color(1, 1, 1);
                }
                Sample sample = new Sample(j, i);

                film.commit(sample, color);
            }
        }
        try{
            film.writeImage();
        }
        catch(Exception e){
            Assert.fail("Should not throw an Exception! " + e.getMessage());
        }

        BufferedImage image = null;
        try{
            image = ImageIO.read(new File("out.png"));
        }
        catch(Exception e){
            Assert.fail("should not throw an Exception! " + e.getMessage());
        }
        Assert.assertEquals(width, image.getWidth());
        Assert.assertEquals(height, image.getHeight());

        int color1 = 0xFF << 24 | 0xFF << 16 | ((int)((long)0xFF * 0.2));
        int color2 = 0xFFFFFFFF;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int color = image.getRGB(j, i);
                if(     (j >= 200 && j <= 300 && i >= 80 && i <= 420) ||
                        (j >= 700 && j <= 800 && i >= 80 && i <= 420) ||
                        (i >= 80 && i <= 180 && j >= 200 && j <= 800) ||
                        (i >= 320 && i <= 420 && j >= 200 && j <= 800)){
                    Assert.assertEquals(color1, color);
                }
                else{
                    Assert.assertEquals(color2, color);
                }
            }
        }
    }
}
