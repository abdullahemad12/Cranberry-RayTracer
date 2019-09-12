package controllers.parsing;

import exceptions.InvalidStateException;
import exceptions.UnkownFileExtensionException;
import model.graphics.ScreenDimensions;
import model.graphics.light.Attenuation;
import model.graphics.light.DirectionalLight;
import model.graphics.light.Light;
import model.graphics.light.PointLight;
import model.graphics.object.*;
import model.math.Point;
import model.math.Vector;
import model.math.transformation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Abdullah Emad
 * @version 1.1
 * The parser class is responsible for parsing the .cbg file and reading the world information and
 * objects from that file
 */
public class Parser {

    private ScreenDimensions screenDimensions;
    private int maxdepth = 5;
    private String outputfilename = null;
    private double[] cameraParameters;
    private List<Shape> shapes;
    private int maxverts = 0;
    private double[][] vertices;
    private int verticesCount;
    private Matrix transformMatrix;
    private Stack<Matrix> stack;
    private List<Light> lights;
    private FiniteStateMachine fsm;
    private boolean parsed;
    private Attenuation attenuation;

    private Color ka;
    private Color kd;
    private Color kr;
    private Color ks;
    private Color ke;


    public ScreenDimensions getScreenDimensions() {
        return screenDimensions;
    }

    public int getMaxdepth() {
        return maxdepth;
    }

    public String getOutputfilename() {
        return outputfilename;
    }

    public double[] getCameraParameters() {
        return cameraParameters;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public List<Light> getLights() {
        return lights;
    }

    public Attenuation getAttenuation() {
        return attenuation;
    }

    private double shininess;
    public Parser() {
        initialize();
    }

    private void initialize(){
        maxdepth = 5;
        outputfilename = null;
        maxverts = 0;
        vertices = null;
        verticesCount = 0;
        cameraParameters = new double[10];
        shapes = new LinkedList<>();
        transformMatrix = new Identity();
        stack = new Stack<>();
        lights = new LinkedList<>();
        fsm = new FiniteStateMachine();
        parsed = false;
        ka = new Color(0.2, 0.2, 0.2);
        kd = new Color(0, 0, 0);
        kr = new Color(0, 0, 0);
        ks = new Color(0, 0, 0);
        ke = new Color(0,0,0);
        shininess = 1;
        attenuation = new Attenuation();
    }

    public void parseFile(String filename) throws IOException, InvalidStateException, UnkownFileExtensionException {
        if(parsed) {
            initialize();
        }

        String[] nameComponents = filename.split("\\.");
        if(nameComponents.length != 2 || !nameComponents[1].equals("cbg")){
            throw new UnkownFileExtensionException(filename);
        }

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while((line = br.readLine()) != null) {
            line = line.trim().replaceAll("\\s+", " ");
            String[] command = line.split(" ");
            command = trimCommand(command);
            Transitions transition = fsm.takeAction(command[0]);

            switch(transition) {
                case SIZE: parseSize(command); break;
                case MAX_DEPTH: parseMaxDepth(command); break;
                case OUTPUT: parseOutput(command); break;
                case CAMERA: parseCamera(command); break;
                case SPHERE: parseSphere(command); break;
                case MAX_VERTS: parseMaxVert(command); break;
                case VERTEX: parseVertex(command); break;
                case TRI: parseTriangle(command); break;
                case TRANSLATE: parseTranslate(command); break;
                case ROTATE: parseRotate(command); break;
                case SCALE: parseScale(command); break;
                case PUSH_TRANSFORM: pushTransform(command); break;
                case POP_TRANSFORM: popTransform(command); break;
                case DIRECTIONAL: parseDirectionLight(command); break;
                case POINT: parsePointLight(command); break;
                case ATTENUATION: parseAttentuation(command); break;
                case AMBIENT: parseAmbient(command); break;
                case DIFFUSE: parseDiffuse(command); break;
                case SPECULAR: parseSpecular(command); break;
                case SHININESS: parseShininess(command); break;
                case EMISSION: parseEmission(command); break;
            }
        }


        br.close();

        parsed = true;
    }

    /*************************
     *     command parsing   *
     *************************/

    private void parseSize(String[] command) throws InvalidStateException {
        if(command.length != 3) {
            throw new InvalidStateException("\"size\" command expects two parameters but was given: " + (command.length - 1));
        }
        int width = Integer.parseInt(command[1]);
        int height = Integer.parseInt(command[2]);
        screenDimensions = new ScreenDimensions(width, height);
    }

    private void parseMaxDepth(String[] command) throws InvalidStateException {
        if(command.length != 2) {
            throw new InvalidStateException("\"maxdepth\" command expects one parameters but was given: " + (command.length - 1));
        }
        maxdepth = Integer.parseInt(command[1]);
    }

    private void parseOutput(String[] command) throws InvalidStateException {
        if(command.length != 2) {
            throw new InvalidStateException("\"output\" command expects one parameters but was given: " + (command.length - 1));
        }
        outputfilename = command[1];

        String[] check = outputfilename.split("\\.");
        if(!check[check.length - 1].equals("png")) {
            throw new InvalidStateException("\"output\" command error: the only supported image format is png");
        }

    }

    private void parseCamera(String[] command) throws InvalidStateException {
        if(command.length != 11) {
            throw new InvalidStateException("\"camera\" command expects ten parameters but was given: " + (command.length - 1));
        }

        for(int i = 1; i < command.length; i++) {
            cameraParameters[i-1] = Double.parseDouble(command[i]);
        }
    }

    private void parseSphere(String[] command) throws InvalidStateException {
        if(command.length != 5) {
            throw new InvalidStateException("\"sphere\" command expects four parameters but was given: " + (command.length - 1));
        }

        BRDF brdf = new BRDF(kd, ks, ka, kr, ke, shininess);

        double x = Double.parseDouble(command[1]);
        double y = Double.parseDouble(command[2]);
        double z = Double.parseDouble(command[3]);
        double r = Double.parseDouble(command[4]);
        Point center = new Point(x, y ,z);
        Sphere sphere = new Sphere(center, r, brdf);

        sphere.transform(transformMatrix);
        shapes.add(sphere);
    }

    private void parseTriangle(String[] command) throws InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"tri\" command expects three parameters but was given: " + (command.length - 1));
        }
        if(vertices == null || verticesCount != maxverts) {
            throw new InvalidStateException("All vertices Should be defined before calling the \"tri\" command");
        }

        int vertex1Index = Integer.parseInt(command[1]);
        int vertex2Index = Integer.parseInt(command[2]);
        int vertex3Index = Integer.parseInt(command[3]);

        double[] a = vertices[vertex1Index];
        double[] b = vertices[vertex2Index];
        double[] c = vertices[vertex3Index];
        Point A = new Point(a[0], a[1], a[2]);
        Point B = new Point(b[0], b[1], b[2]);
        Point C = new Point(c[0], c[1], c[2]);
        BRDF brdf = new BRDF(kd, ks, ka, kr, ke, shininess);

        Triangle triangle = new Triangle(A, B, C, brdf);
        triangle.transform(transformMatrix);

        shapes.add(triangle);

    }

    private void parseMaxVert(String[] command) throws InvalidStateException {
        if(command.length != 2) {
            throw new InvalidStateException("\"maxverts\" command expects one parameters but was given: " + (command.length - 1));
        }

        maxverts = Integer.parseInt(command[1]);

        vertices = new double[maxverts][3];
    }

    private void parseVertex(String[] command) throws InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"maxverts\" command expects three parameters but was given: " + (command.length - 1));
        }

        if(verticesCount >= maxverts) {
            throw new InvalidStateException(verticesCount + " vertices are already defined! you can define more!");
        }
        vertices[verticesCount][0] = Double.parseDouble(command[1]);
        vertices[verticesCount][1] = Double.parseDouble(command[2]);
        vertices[verticesCount++][2] = Double.parseDouble(command[3]);
    }

    private void pushTransform(String[] command) throws InvalidStateException{
        if(command.length != 1) {
            throw new InvalidStateException("\"pushTransform\" command expects zero parameters but was given: " + (command.length - 1));
        }

        stack.push(transformMatrix);
    }

    private void popTransform(String[] command) throws InvalidStateException {
        if(command.length != 1) {
            throw new InvalidStateException("\"popTransform\" command expects zero parameters but was given: " + (command.length - 1));
        }
        transformMatrix = stack.pop();
    }

    private void parseTranslate(String[] command) throws InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"translate\" command expects three parameters but was given: " + (command.length - 1));
        }

        double x = Double.parseDouble(command[1]);
        double y = Double.parseDouble(command[2]);
        double z = Double.parseDouble(command[3]);

        Matrix translate = new Translation(x, y, z);

        transformMatrix = transformMatrix.multiply(translate);
    }

    private void parseRotate(String[] command) throws InvalidStateException {
        if(command.length != 5) {
            throw new InvalidStateException("\"rotate\" command expects three parameters but was given: " + (command.length - 1));
        }
        double x = Double.parseDouble(command[1]);
        double y = Double.parseDouble(command[2]);
        double z = Double.parseDouble(command[3]);
        double angle = Double.parseDouble(command[4]);

        Matrix rotation = new Rotation(x, y, z, angle);
        transformMatrix = transformMatrix.multiply(rotation);

    }

    private void parseScale(String[] command) throws InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"scale\" command expects three parameters but was given: " + (command.length - 1));
        }
        double x = Double.parseDouble(command[1]);
        double y = Double.parseDouble(command[2]);
        double z = Double.parseDouble(command[3]);

        Matrix scale = new Scale(x, y, z);
        transformMatrix = transformMatrix.multiply(scale);
    }

    private void parseDirectionLight(String[] command) throws InvalidStateException {
        if(command.length != 7) {
            throw new InvalidStateException("\"directional\" command expects six parameters but was given: " + (command.length - 1));
        }

        double x = Double.parseDouble(command[1]);
        double y = Double.parseDouble(command[2]);
        double z = Double.parseDouble(command[3]);

        double r = Double.parseDouble(command[4]);
        double g = Double.parseDouble(command[5]);
        double b = Double.parseDouble(command[6]);

        Vector v = new Vector(x, y , z);
        Color c = new Color(r, g, b);

        DirectionalLight light = new DirectionalLight(v, c);

        lights.add(light);
    }

    private void parsePointLight(String[] command) throws InvalidStateException {
        if(command.length != 7) {
            throw new InvalidStateException("\"directional\" command expects six parameters but was given: " + (command.length - 1));
        }

        double x = Double.parseDouble(command[1]);
        double y = Double.parseDouble(command[2]);
        double z = Double.parseDouble(command[3]);

        double r = Double.parseDouble(command[4]);
        double g = Double.parseDouble(command[5]);
        double b = Double.parseDouble(command[6]);

        Point p = new Point(x, y, z);
        Color c = new Color(r, g, b);

        Light light = new PointLight(p, c);

        lights.add(light);

    }

    private void parseAttentuation(String[] command) throws InvalidStateException{
        if(command.length != 4) {
            throw new InvalidStateException("\"attenuation\" command expects three parameters but was given: " + (command.length - 1));
        }

        double constant = Double.parseDouble(command[1]);
        double linear = Double.parseDouble(command[2]);
        double quad = Double.parseDouble(command[3]);

        attenuation = new Attenuation(constant, linear, quad);
    }

    private void parseAmbient(String[] command) throws InvalidStateException{
        if(command.length != 4) {
            throw new InvalidStateException("\"ambient\" command expects three parameters but was given: " + (command.length - 1));
        }

        double r = Double.parseDouble(command[1]);
        double g = Double.parseDouble(command[2]);
        double b = Double.parseDouble(command[3]);

        ka = new Color(r, g, b);
    }

    private void parseDiffuse(String[] command) throws  InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"diffuse\" command expects three parameters but was given: " + (command.length - 1));
        }

        double r = Double.parseDouble(command[1]);
        double g = Double.parseDouble(command[2]);
        double b = Double.parseDouble(command[3]);

        kd = new Color(r, g, b);
    }


    private void parseSpecular(String[] command) throws  InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"specular\" command expects three parameters but was given: " + (command.length - 1));
        }

        double r = Double.parseDouble(command[1]);
        double g = Double.parseDouble(command[2]);
        double b = Double.parseDouble(command[3]);

        ks = new Color(r, g, b);
    }

    private void parseShininess(String[] command) throws InvalidStateException {
        if(command.length != 2) {
            throw new InvalidStateException("\"shininess\" command expects one parameters but was given: " + (command.length - 1));
        }

        shininess = Double.parseDouble(command[1]);
    }

    private void parseEmission(String[] command) throws InvalidStateException {
        if(command.length != 4) {
            throw new InvalidStateException("\"emission\" command expects three parameters but was given: " + (command.length - 1));
        }


        double r = Double.parseDouble(command[1]);
        double g = Double.parseDouble(command[2]);
        double b = Double.parseDouble(command[3]);

        ke = new Color(r, g, b);

    }

    /**
     * Used to remove comments from the command line
     * @param command the array of the command and its arguments to be trimmed
     */
    private static String[] trimCommand(String[] command) {
        int count = 0;
        for(int i = 0; i < command.length && !command[i].equals("#") && command[i].charAt(0) != '#'; i++){
            ++count;
        }

        String[] newCommand = new String[count];
        System.arraycopy(command, 0, newCommand, 0, newCommand.length);
        return newCommand;
    }

}
