package model.graphics.object;

/**
 * The BRDF Color Material stores the following color components for a specific Object in the world
 * <ol>
 *  <li>Ambient<li/>
 *  <li>Emission<li/>
 *  <li>Specular<li/>
 *  <li>Diffuse<li/>
 *  <li>Reflective<li/>
 *  <li>Shininess<li/>
 * <ol/>
 *
 * @author Abdullah Emad
 * @version 1.0
 */
public class BRDF {
    /**
     * Diffuse
     */
    private Color kd;
    /**
     * Specular
     */
    private Color ks;
    /**
     * Ambient
     */
    private Color ka;
    /**
     * Reflective
     */
    private Color kr;
    /**
     * Emission
     */
    private Color ke;
    /**
     * Diffuse
     */
    private double shininess;

    /**
     *
     * @param kd kd
     * @param ks ks
     * @param ka ka
     * @param kr kr
     * @param ke ke
     * @param shininess shininess
     */
    public BRDF(Color kd, Color ks, Color ka, Color kr, Color ke, double shininess) {
        this.kd = kd;
        this.ks = ks;
        this.ka = ka;
        this.kr = kr;
        this.ke = ke;
        this.shininess = shininess;
    }

    /**
     *
     * @return kd
     */
    public Color getDiffuse(){
        return this.kd;
    }

    /**
     *
     * @return ks
     */
    public Color getSpecular(){
        return this.ks;
    }

    /**
     *
     * @return ka
     */
    public Color getAmbient(){
        return this.ka;
    }

    /**
     *
     * @return kr
     */
    public Color getReflection(){
        return this.kr;
    }

    /**
     *
     * @return shininess
     */
    public double getShininess() { return this.shininess; }

    /**
     *
     * @return ke
     */
    public Color getEmission() { return this.ke; }
}
