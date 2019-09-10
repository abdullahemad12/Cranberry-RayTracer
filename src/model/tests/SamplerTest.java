import exceptions.InvalidSampleException;
import model.graphics.Sample;
import controllers.Sampler;
import org.junit.Assert;
import org.junit.Test;

public class SamplerTest {
    @Test
    public void SampleCreationAndIterationTest() throws InvalidSampleException {
        int height = 200;
        int width = 100;
        Sampler sampler = new Sampler(width, height);



        int i = 0;
        int j = 0;
        for(Sample sample : sampler){
            Assert.assertEquals(i++, sample.getX());
            Assert.assertEquals(j, sample.getY());

            if(i == width){
                ++j;
                i = 0;
            }
        }

        Assert.assertEquals(height, j);
        Assert.assertEquals(0, i);


    }

}
