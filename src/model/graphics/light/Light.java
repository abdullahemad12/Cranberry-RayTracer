package model.graphics.light;

import exceptions.ColorOverflowException;
import model.graphics.Intersection;
import model.graphics.Ray;
import model.graphics.object.BRDF;
import model.graphics.object.Color;
import model.math.Normal;
import model.math.Vector;

abstract public class Light {
    private Color color;
    private Attenuation attenuation;
    public Light(Color color) {
        this.color = color;
        this.attenuation = new Attenuation();
    }

    public Light(Color color, Attenuation attenuation) {
        this.color = color;
        this.attenuation = attenuation;
    }

    abstract public double getDistanceFromLight(Intersection intersection);
    abstract public Ray generateLightRay(Intersection intersection);
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
