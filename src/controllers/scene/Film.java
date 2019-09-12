package controllers.scene;

import exceptions.IncompleteImageException;
import model.graphics.ScreenDimensions;
import model.graphics.object.Color;
import model.graphics.Sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Film class is responsible for writing
 * the final image of the ray tracer to a JPG file
 * @author Abdullah Emad
 * @version 1.0
 */
public class Film {
    private BufferedImage image;
    private int nCommited;
    private boolean[][] pixelCommited;
    private String outputFileName = "out.png";

    /**
     *
     * @param screenDimensions: The dimensions of the screen
     */
    public Film(ScreenDimensions screenDimensions) {
        int width = screenDimensions.getWidth();
        int height = screenDimensions.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        nCommited = 0;
        pixelCommited = new boolean[height][width];
    }

    /**
     *
     * @param screenDimensions the dimensions of the screen
     * @param outputFileName the output filename
     */
    public Film(ScreenDimensions screenDimensions, String outputFileName){
        this(screenDimensions);
        this.outputFileName = outputFileName;
    }

    /**
     * Commits a given color to the pixel specified by the sample
     * @param sample A sample representing the x y coordinate of the pixel on the screen
     * @param color The color to be written to this pixel
     */
    public void commit(Sample sample, Color color) {

        int i = sample.getX();
        int j = sample.getY();

        int c = 0xFF << 24 | ((int)(((long)0xFF) * color.getR())) << 16 | ((int)(((long)0xFF) * color.getG())) << 8 |
                ((int)(((long)0xFF) * color.getB()));
        image.setRGB(i, j, c);

        if(!pixelCommited[j][i]){
            ++nCommited;
            pixelCommited[j][i] = true;
        }
    }

    /**
     * writes the Film into a jpg image file called named.jpg in the same directory
     * @throws Exception throws an Exception if the image was not fully committed or an IOException occurs
     */
    public void writeImage() throws Exception {
        if(nCommited != (image.getWidth() * image.getHeight())){
            throw new IncompleteImageException();
        }
        boolean ret = ImageIO.write(image, "png", new File(outputFileName));
        if(!ret){
            throw new IOException();
        }
    }
}
