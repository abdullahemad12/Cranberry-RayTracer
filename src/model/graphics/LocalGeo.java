package model.graphics;

import model.math.Normal;
import model.math.Point;

/**
 * The LocalGeo encodes information of the intersection point between a Ray and a Shape
 * @author Abdullah Emad
 * @version 1.0
 */
public class LocalGeo {

    /**
     * the distance from the ray's starting point
     */
    private double thit;
    /**
     * the point of intersection on the ray
     */
    private Point pos;
    /**
     * the normal to the point of intersection
     */
    private Normal normal;


    public LocalGeo(double thit, Point pos, Normal normal){
        this.thit = thit;
        this.pos = pos;
        this.normal = normal;
    }

    /**
     *
     * @return thit
     */
    public double getThit() {
        return this.thit;
    }

    /**
     *
     * @return pos
     */
    public Point getPos() {
        return this.pos;
    }

    /**
     *
     * @return normal
     */
    public Normal getNormal() {
        return this.normal;
    }

    @Override
    public boolean equals(Object obj){
        return this == obj || (obj instanceof LocalGeo) &&
                (this.thit == ((LocalGeo)obj).getThit() && this.pos.equals(((LocalGeo)obj).getPos())
                        && this.getNormal().equals(((LocalGeo)obj).getNormal()));
    }
}
