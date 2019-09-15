package model.graphics;

/**
 * @author Abdullah Emad
 * @version 1.0
 * This class acts as a Data holder for the width and height of the screen
 */
public class ScreenDimensions {


    private int width;
    private int height;

    /**
     *
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public ScreenDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     *
     * @return height
     */
    public int getHeight() {
        return this.height;
    }
}

