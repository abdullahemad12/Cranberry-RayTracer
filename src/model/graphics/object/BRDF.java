package model.graphics.object;

public class BRDF {
    private Color kd;
    private Color ks;
    private Color ka;
    private Color kr;

    public BRDF(Color kd, Color ks, Color ka, Color kr) {
        this.kd = kd;
        this.ks = ks;
        this.ka = ka;
        this.kr = kr;
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
}
