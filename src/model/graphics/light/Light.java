package model.graphics.light;

import model.graphics.object.Color;

public class Light {
    private Color c;
    private Attenuation attenuation;
    public Light(Color c) {
        this.c = c;
        this.attenuation = new Attenuation();
    }

    public Light(Color c, Attenuation attenuation) {
        this.c = c;
        this.attenuation = attenuation;
    }

    public Color computePhongComponent(){
        return null;
    }
}
