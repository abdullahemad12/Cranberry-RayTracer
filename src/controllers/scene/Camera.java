package controllers.scene;

import model.graphics.Ray;
import model.graphics.ScreenDimensions;
import model.math.Point;
import model.math.Vector;
import model.graphics.Sample;

/**
 * @author Abdullah Emad
 * @version 1.0
 * Class camera is reponsible for generating rays for the given corresponding pixel
 */
public class Camera {
    private ScreenDimensions screenDimensions;
    private Point eye;
    private Vector w;
    private Vector u;
    private Vector v;
    private double fovy; /*value is in radians*/
    private double fovx; /*value is in radians*/

    /**
     * Instantiate a new instance of the camera with the given eye, center
     * and up points and vector
     * @param eye the location of the eye (look-from) as a 3D point
     * @param center the location of the eye (look-at) as a 3D point
     * @param up the up vector
     * @param screenDimensions the dimensions of the screen
     * @param fovy the field of view in the y direction
     */
    public Camera(Point eye, Point center, Vector up, ScreenDimensions screenDimensions, double fovy) {
        Vector a = eye.subtract(center);
        Vector w = a.normalize();
        Vector u = up.cross(w).normalize();
        Vector v = u.cross(w);
        this.w = w;
        this.u = u;
        this.v = v;

        this.screenDimensions = screenDimensions;
        this.eye = eye;

        double aspect_ratio = (double)screenDimensions.getWidth() / (double)screenDimensions.getHeight();
        double fovy_rads = Math.toRadians(fovy);
        double fovy_rads_tan = Math.tan(fovy_rads / 2);
        double fovx_rads = 2 * Math.atan(aspect_ratio * fovy_rads_tan);

        this.fovy = fovy_rads;
        this.fovx = fovx_rads;
    }

    /**
     * Generates a ray that passes through the given pixel
     * @param sample the sample encodes the x, y location of the desired pixel
     * @return Ray shot through the given pixel
     */
    public Ray generateRay(Sample sample) throws IndexOutOfBoundsException{


        int j = sample.getX();
        int i = sample.getY();

        int width = screenDimensions.getWidth();
        int height = screenDimensions.getHeight();

        if(j >= width || i >= height) {
            throw new IndexOutOfBoundsException();
        }


        double alpha = Math.tan(fovx / 2.0) * (((double)j - ((double)width / 2.0)) / ((double)width / 2.0));
        double beta = Math.tan(fovy / 2.0) * ((((double)height / 2.0) - (double)i) / ((double)height / 2.0));

        Vector alphaU = u.multiply(alpha);
        Vector betaV = v.multiply(beta);

        Vector dir = alphaU.subtract(betaV).subtract(w);
        dir = dir.normalize();


        return new Ray(eye, dir);
    }

    Vector calculateEyeDirection(Point p) {
        return eye.subtract(p).normalize();
    }
}
