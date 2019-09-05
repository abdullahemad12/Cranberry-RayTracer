package model.tests;

import model.graphics.object.Color;
import org.junit.Before;

public class ColorTest {

    private Color color;
    private static final double r = 0.322;
    private static final double g = 0.2;
    private static final double b = 0.4;

    @Before
    public void setUp() {
        color = new Color(r, g, b);
    }
}
