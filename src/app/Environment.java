package app;

public enum Environment {
    APP_NAME("Cranberry Ray Tracer"), APP_VERSION("1.0"), APP_SHORT_NAME("cbtracer");
    String value;
    Environment(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
