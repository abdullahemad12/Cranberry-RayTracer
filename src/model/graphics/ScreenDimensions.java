package model.graphics;

/**
 * @author Abdullah Emad
 * This class acts as a Data holder for the width and height of the screen
 */
public class ScreenDimensions {

    private int width;
    private int height;

    public ScreenDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}

