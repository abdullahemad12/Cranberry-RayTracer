package model.graphics;

import model.graphics.object.Shape;

/**
 * @author Abdullah Emad
 * The Intersection class is a data holder for a Shape and the localGeo of it's intersection with a ray
 */
public class Intersection {
    private Shape shape;
    private LocalGeo localGeo;

    public Intersection(Shape shape, LocalGeo localGeo) {
        this.shape = shape;
        this.localGeo = localGeo;
    }

    public Shape getShape() {
        return this.shape;
    }

    public LocalGeo getLocalGeo() {
        return this.localGeo;
    }
}
