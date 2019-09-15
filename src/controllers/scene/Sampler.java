package controllers.scene;
import app.format.Printer;
import exceptions.InvalidSampleException;
import model.graphics.Sample;
import model.graphics.ScreenDimensions;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Sampler class is an Iterable class that gives you one screen sample at a time
 * @author Abdullah Emad
 * @version 1.0
 */
public class Sampler implements Iterable<Sample>{
    /**
     * the dimension of the screen
     */
    private ScreenDimensions screenDimensions;

    public Sampler(ScreenDimensions dims) {
        this.screenDimensions = dims;
    }

    @NotNull @Override
    public Iterator<Sample> iterator(){
        return new SamplerIterator(screenDimensions);
    }

    private class SamplerIterator implements Iterator<Sample>{

        ScreenDimensions screenDimensions;
        int ptr;
        SamplerIterator(ScreenDimensions screenDimensions){
            this.screenDimensions = screenDimensions;
            this.ptr = 0;
        }

        @Override
        public boolean hasNext() {
            return ptr <= ((screenDimensions.getHeight() * screenDimensions.getWidth()) - 1);
        }

        @Override
        public Sample next() {
            int i = ptr / screenDimensions.getWidth();
            int j = ptr % screenDimensions.getWidth();
            ++ptr;
            Sample sample;
            try {
                sample = new Sample(j, i);
            } catch (InvalidSampleException e){
                sample = null;
            }
            Printer.clearScreen();
            Printer.println("Progress: " + 100.0 *  (double)ptr / (double)(screenDimensions.getHeight() * screenDimensions.getWidth()));
            return sample;
        }

    }

}


