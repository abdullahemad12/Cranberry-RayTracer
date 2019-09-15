package controllers.scene;

import controllers.parsing.Parser;
import exceptions.InvalidStateException;
import exceptions.UnknownFileExtensionException;
import model.graphics.Sample;
import model.graphics.ScreenDimensions;
import model.graphics.object.AggregateShape;
import model.graphics.object.Color;
import model.math.Point;
import model.math.Vector;

import java.io.IOException;

/**
 * <p>The Scene class represents the main controller that controllers the actual ray tracer and other controllers.
 * The scene class initiates all the method calls to all other controllers since the input file was parsed until the
 * image was written</p>
 * <p>The Scene class starts by parsing the input file using the parser and then initializes all the other controllers
 * After initialization the scene class iterates over the samples until they are all exhausted. For each sample it asks
 * the ray tracer to produce the color for this sample. Afterwards, it asks the file to commit this color. After
 * all the samples were exhausted, the scene will finally ask the Film to write the image to file</p>
 * @author Abdullah Emad
 * @version 1.0
 */
public class Scene {


    private RayTracer raytracer;
    private Sampler sampler;
    private Film film;
    private Parser parser;

    /**
     *
     * @param filepath the input file
     * @throws InvalidStateException on file syntax errors
     * @throws IOException on IO problems
     * @throws UnknownFileExtensionException on unknown input files
     */
    public Scene(String filepath) throws InvalidStateException, IOException, UnknownFileExtensionException {
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


    /**
     * renders the scene and writes it as a png to the given path in the input file
     * @throws Exception when something goes wrong while tracing
     */
    public void render() throws Exception {
        for(Sample sample : sampler) {
            Color color = raytracer.trace(sample);
            film.commit(sample, color);
        }
        film.writeImage();
    }



    /**
     * <b>REQUIRES: <b/>parser.parseFile() should be called first
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
