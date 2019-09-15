package model.graphics.light;

import exceptions.ColorOverflowException;
import model.graphics.Intersection;
import model.graphics.Ray;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.math.Normal;
import model.math.Vector;

/**
 * The abstract class Light represents any type of light in the world
 * @author Abdullah Emad
 * @version 1.0
 */
abstract public class Light {
    /**
     * the color intensity of the light
     */
    private Color color;
    /**
     * the attenuation of the light
     */
    private Attenuation attenuation;

    /**
     *
     * @param color Color intensity
     */
    public Light(Color color) {
        this.color = color;
        this.attenuation = new Attenuation();
    }

    /**
     *
     * @param color Color intensity
     * @param attenuation attenuation
     */
    public Light(Color color, Attenuation attenuation) {
        this.color = color;
        this.attenuation = attenuation;
    }

    /**
     * <b>EFFECTS: <b/>computes the distance from the light to an intersection point as a scalar value
     * @param intersection the intersection point where the distance from the light will be computed
     * @return the distance from the light
     */
    abstract public double getDistanceFromLight(Intersection intersection);

    /**
     * <b>EFFECTS: <b/>generates a ray from the intersection point towards the light
     * @param intersection the intersection where the light ray will point to
     * @return the generated ray
     */
    abstract public Ray generateLightRay(Intersection intersection);

    /**
     * <b>EFFECTS: </b>computes the light intensity that should be seen at this intersection point
     * from the eye considering the brdf material of the objects
     * @param intersection the Intersection point
     * @param eyeDir the direction of the eye sight
     * @return the Color that should be seen by the eye
     * @throws ColorOverflowException when a numerical error occurs
     */
    abstract public Color computeLight(Intersection intersection, Vector eyeDir) throws ColorOverflowException;

    /**
     * Computes the phong component of the lighting
     * @param normal the normal to the surface of the intersected object
     * @param halfVec the half vector
     * @param brdf the brdf material colors
     * @param distance the distance between the intersection point and the light location
     * @return the color component from the phong calculation
     */
    Color computePhongComponent(Vector halfVec, Normal normal, BRDF brdf, double distance) throws ColorOverflowException {

        double attenuation = this.attenuation.computeAttenuation(distance);

        double nDotH = normal.dot(halfVec);

        double phongComp = Double.max(nDotH, 0.0);
        phongComp = Math.pow(phongComp, brdf.getShininess());

        Color specular = brdf.getSpecular();

        Color phong = color.multiply(phongComp);

        phong = phong.multiply(specular);

        phong = phong.multiply(attenuation); //attenuation * specular * lightColor * (max(nDotH, 0.0) ^ shininess)

        return phong;
    }

    /**
     * Computes the lambert component of the lighting
     * @param direction the direction from the intersection point to the light position
     * @param normal the normal to the surface of the intersection
     * @param brdf the brdf material colors
     * @param distance the distance from the intersection point to the light position
     * @return the lambert component
     * @throws ColorOverflowException Numerical errors
     */
    Color computeLambertComponent(Vector direction, Normal normal,  BRDF brdf, double distance) throws ColorOverflowException {
        double attenuation = this.attenuation.computeAttenuation(distance);
        double nDotL = normal.dot(direction);

        double lambertComp = Double.max(nDotL, 0.0);

        Color diffuse = brdf.getDiffuse();

        Color lambert = color.multiply(lambertComp);

        lambert = lambert.multiply(diffuse);
        lambert = lambert.multiply(attenuation); // attenuation * diffuse * lightColor *  max(nDotL, 0.0)

        return lambert;
    }
}
