package controllers.scene;

import controllers.parsing.Parser;
import exceptions.InvalidStateException;
import exceptions.UnkownFileExtensionException;
import model.graphics.Sample;
import model.graphics.ScreenDimensions;
import model.graphics.object.AggregateShape;
import model.graphics.object.Color;
import model.math.Point;
import model.math.Vector;

import java.io.IOException;

public class Scene {

    private RayTracer raytracer;
    private Sampler sampler;
    private Film film;
    private Parser parser;

    public Scene(String filepath) throws InvalidStateException, IOException, UnkownFileExtensionException {
        parser = new Parser();
        parser.parseFile(filepath);

        Camera camera = initCamera();
        sampler = new Sampler(parser.getScreenDimensions());
        initFilm();
        AggregateShape aggregateShape = new AggregateShape(parser.getShapes());
        raytracer = new RayTracer(camera);
        raytracer.setAggregateShape(aggregateShape);
        raytracer.setLights(parser.getLights());
        raytracer.setMaxDepth(parser.getMaxdepth());

    }



    public void render() throws Exception {
        for(Sample sample : sampler) {
            Color color = raytracer.trace(sample);
            film.commit(sample, color);
        }
        film.writeImage();
    }



    /**
     * REQUIRES: parser.parseFile() should be called first
     * This function initialize the camera object
     */
    private Camera initCamera() {
        double[] cameraParameters = parser.getCameraParameters();
        ScreenDimensions dims = parser.getScreenDimensions();
        Point eye = new Point(cameraParameters[0], cameraParameters[1], cameraParameters[2]);
        Point center = new Point(cameraParameters[3], cameraParameters[4], cameraParameters[5]);
        Vector up = new Vector(cameraParameters[6], cameraParameters[7], cameraParameters[8]);
        double fovy = cameraParameters[9];

        return new Camera(eye, center, up, dims, fovy);
    }

    /**
     * REQUIRES: parser.parseFile() should be called first
     * This function initialize the film object
     */
    private void initFilm() {
        if(parser.getOutputfilename() != null) {
            film = new Film(parser.getScreenDimensions(), parser.getOutputfilename());
        }
        else{
            film = new Film(parser.getScreenDimensions());
        }
    }
}
