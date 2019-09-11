package controllers.parsing;

public enum Transitions {
    BLANK_LINE(0, ""), COMMENT(1, "#"), SIZE(2, "size"), OUTPUT(3, "output"), MAX_DEPTH(4, "maxdepth"), CAMERA(5, "camera"),
    PUSH_TRANSFORM(6, "pushTransform"), POP_TRANSFORM(7, "popTransform"), SCALE(8, "scale"), ROTATE(9, "rotate"),
    TRANSLATE(10, "translate"), TRI(11, "tri"), VERTEX(12, "vertex"), MAX_VERTS(13, "maxverts"), SPHERE(14, "sphere"),
    DIRECTIONAL(15, "directional"), POINT(16, "point"), ATTENUATION(17, "attenuation"), AMBIENT(18, "ambient"),
    DIFFUSE(19, "diffuse"), SPECULAR(20, "specular"), SHININESS(21, "shininess"), EMISSION(22, "emission");

    private int id;
    private String value;
    Transitions(int id, String value){
        this.id = id;
        this.value = value;
    }

    public int getId(){
        return this.id;
    }

    public String getValue() {
        return this.value;
    }
}
