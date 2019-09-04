package model.screen;
import exceptions.InvalidSampleException;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Sampler implements Iterable<Sample>{
    private Sample[][] screen;

    public Sampler(int width, int height) throws InvalidSampleException {
        screen = new Sample[height][width];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                screen[i][j] = new Sample(j, i);
            }
        }
    }

    @NotNull @Override
    public Iterator<Sample> iterator(){
        return new SamplerIterator(screen);
    }

    private class SamplerIterator implements Iterator<Sample>{

        Sample[][] screen;
        int ptr;
        SamplerIterator(Sample[][] screen){
            this.screen = screen;
            this.ptr = 0;
        }

        @Override
        public boolean hasNext() {
            return ptr <= ((screen.length * screen[0].length) - 1);
        }

        @Override
        public Sample next() {
            int i = ptr / screen[0].length;
            int j = ptr % screen[0].length;
            ++ptr;
            return screen[i][j];
        }

    }

}


