package model.graphics;

import model.graphics.object.Shape;

/**
 * @author Abdullah Emad
 * The Intersection class is a data holder for a Shape and the localGeo of it's intersection with a ray
 */
public class Intersection {
    /**
     * the shape that was intersected
     */
    private Shape shape;
    /**
     * the localGeo of the intersection
     */
    private LocalGeo localGeo;

    public Intersection(Shape shape, LocalGeo localGeo) {
        this.shape = shape;
        this.localGeo = localGeo;
    }

    /**
     *
     * @return shape
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     *
     * @return localGeo
     */
    public LocalGeo getLocalGeo() {
        return this.localGeo;
    }
}
