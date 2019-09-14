package model.graphics.object;

public class BRDF {
    private Color kd;
    private Color ks;
    private Color ka;
    private Color kr;
    private Color ke;
    private double shininess;

    public BRDF(Color kd, Color ks, Color ka, Color kr, Color ke, double shininess) {
        this.kd = kd;
        this.ks = ks;
        this.ka = ka;
        this.kr = kr;
        this.ke = ke;
        this.shininess = shininess;
    }


    public Color getDiffuse(){
        return this.kd;
    }

    public Color getSpecular(){
        return this.ks;
    }

    public Color getAmbient(){
        return this.ka;
    }

    public Color getReflection(){
        return this.kr;
    }

    public double getShininess() { return this.shininess; }

    public Color getEmission() { return this.ke; }
}
