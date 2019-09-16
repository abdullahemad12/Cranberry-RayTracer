package datastructures.OctaTree;

import controllers.scene.RayTracer;
import model.graphics.Ray;
import model.graphics.object.Box;
import model.graphics.object.Shape;
import model.math.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Octree {

    private BoundingBox head;
    private double size;

    private static int MAX_DEPTH;

    public Octree(List<Shape> shapes) {
        MAX_DEPTH = (int) (3 * Math.log(shapes.size()) / Math.log(8));
        Box biggestBB = computeBoundingBox(shapes);

        BoundingBox boundingBox = new BoundingBox(biggestBB, shapes);

        initTree(boundingBox, 0);
        head = boundingBox;
        size = shapes.size();
    }

    /**
     * initializes the head of the tree
     * @param boundingBox currentBoundingBox
     * @param cur_depth the current depth in the recursion
     */
    private void initTree(BoundingBox boundingBox, int cur_depth) {
        if(cur_depth >= MAX_DEPTH) {
            return;
        }

        Point A = boundingBox.getA();
        Point B = boundingBox.getB();
        List<Shape> shapes = boundingBox.getShapes();

        double avgX = Math.abs(A.getX() - B.getX()) / 2.0;
        double avgY = Math.abs(A.getY() - B.getY()) / 2.0;
        double avgZ = Math.abs(A.getZ() - B.getZ()) / 2.0;


        Box b0 = new Box(A, new Point(A.getX() + avgX, A.getY() + avgY, A.getZ() + avgZ));
        Box[] boxes = new Box[8];
        for(int i = 0; i < boxes.length; i++) {
            int xF = i & 0x1;
            int yF = (i & 0x2) >> 1;
            int zF = (i & 0x4) >> 2;

            Point minP = new Point(A.getX() + (avgX * xF), A.getY() + (avgY * yF), A.getZ() + (avgZ * yF));

            Point maxP = new Point((A.getX() + avgX) + (avgX * xF), A.getY() + avgY + (avgY * yF), A.getZ() + avgZ
                    + (avgZ * zF));

            boxes[i] = new Box(minP, maxP);


        }
        BoundingBox[] boundingBoxes = new BoundingBox[8];
        for(int i = 0; i < boxes.length; i++){
            List<Shape> subShapes = new ArrayList<>();
            for(Shape shape : shapes) {
                if(shape.isOverlapping(boxes[i])) {
                    subShapes.add(shape);
                }
            }
            if(!subShapes.isEmpty()) {
                boundingBoxes[i] = new BoundingBox(boxes[i], subShapes);
                initTree(boundingBoxes[i], cur_depth + 1);
            }
            else{
                boundingBoxes[i] = null;
            }
        }
        boundingBox.setBoundingBoxes(boundingBoxes);

    }

    /**
     * Returns all the objects that intersects with this Ray (possibly with other objects as well)
     * @param ray The ray to be intersected with the bounding boxes
     * @return HashSet of intersecting Shapes
     */
    public HashSet<Shape> search(Ray ray) {
        return this.searchHelper(this.head, ray);
    }

    private HashSet<Shape> searchHelper(BoundingBox boundingBox, Ray ray){
        if(boundingBox == null){
            return null;
        }
        if(!boundingBox.doesIntersect(ray)) {
            return null;
        }

        HashSet<Shape> ret = new HashSet<>();
        BoundingBox[] boundingBoxes = boundingBox.getBoundingBoxes();
        if(boundingBoxes == null) {
            ret.addAll(boundingBox.getShapes());
            return ret;
        }

        for(int i = 0; i < boundingBoxes.length; i++) {
            HashSet<Shape> set = searchHelper(boundingBoxes[i], ray);
            if(set != null) {
                ret.addAll(set);
            }
        }

        // must be a leaf
        if(ret.size() == 0) {
            ret.addAll(boundingBox.getShapes());
        }
        return ret;
    }


    /**
     * Computes a 3D box that surrounds all the shapes
     * @param shapes the list of shapes to be bounded
     * @return the bounding box as a Box
     */
    private static Box computeBoundingBox(List<Shape> shapes) {
        Point min = computeMinPoint(shapes);
        Point max = computeMaxPoint(shapes);

        return new Box(min, max);
    }
    private static Point computeMinPoint(List<Shape> shapes) {

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double minZ = Double.MAX_VALUE;

        for(Shape shape : shapes) {
            Box box =  shape.calculateBoundingBox();
            if(box.getA().getX() < minX) {
                minX = box.getA().getX();
            }
            if(box.getA().getY() < minY) {
                minY = box.getA().getY();
            }
            if(box.getA().getZ() < minZ) {
                minZ = box.getA().getZ();
            }
        }
        return new Point(minX - RayTracer.ERROR_EPSILON, minY - RayTracer.ERROR_EPSILON, minZ - RayTracer.ERROR_EPSILON);
    }

    private static Point computeMaxPoint(List<Shape> shapes) {
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double maxZ = Double.MIN_VALUE;
        for(Shape shape : shapes) {
            Box box = shape.calculateBoundingBox();
            if(box.getB().getX() > maxX) {
                maxX = box.getB().getX();
            }
            if(box.getB().getY() > maxY) {
                maxY = box.getB().getY();
            }
            if(box.getB().getZ() > maxZ) {
                maxZ = box.getB().getZ();
            }
        }
        return new Point(maxX + RayTracer.ERROR_EPSILON, maxY + RayTracer.ERROR_EPSILON, maxZ + RayTracer.ERROR_EPSILON);
    }

}
