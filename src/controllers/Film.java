package controllers;

import exceptions.IncompleteImageException;
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
     * @param width: the width of the screen
     * @param height: the height of the screen
     */
    public Film(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        nCommited = 0;
        pixelCommited = new boolean[height][width];
    }

    public Film(int width, int height, String outputFileName){
        this(width, height);
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
