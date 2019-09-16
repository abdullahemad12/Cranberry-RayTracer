package datastructures.OctaTree;

import app.format.Printer;
import exceptions.CranberryException;
import model.graphics.object.Box;
import model.graphics.object.Shape;

import java.util.List;

class BoundingBox extends Box {
    private BoundingBox[] boundingBoxes;
    private List<Shape> shapes; // NonNull for leaf Boxes only

    /**
     * Creates the tree for the OcTree with this as the head
     * @param box A box representing the dimensions of this BoundingBox
     * @param shapes the shapes bounded by this bounding box
     */
    BoundingBox(Box box,  List<Shape> shapes) {
        super(box.getA(), box.getB());
        if(shapes.size() == 0) {
            Printer.printError("Fatal Error in BoundingBox.java: should never enter the condition in this line");
            System.exit(CranberryException.DEFAULT_EXIT_STATUES);
        }
        this.shapes = shapes;
        boundingBoxes = new BoundingBox[8];

    }

    void setBoundingBoxes(BoundingBox[] boundingBoxes){
        this.boundingBoxes = boundingBoxes;
    }
    BoundingBox[] getBoundingBoxes() { return this.boundingBoxes; }

    List<Shape> getShapes() {
        return this.shapes;
    }






}
