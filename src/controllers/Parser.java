package controllers;

import model.graphics.light.Light;
import model.graphics.object.Shape;
import model.math.transformation.Matrix;

import java.util.List;
import java.util.Stack;

public class Parser {

    private int width;
    private int height;
    private int maxdepth = 5;
    private String outputfilename = null;
    private double[] cameraParameters;
    private List<Shape> shapes;
    private int maxverts;
    private double[][] verticies;
    private Matrix trasformMatrix;
    private Stack<Matrix> stack;
    private List<Light> light;

}
