package model.tests;

import model.screen.Sample;
import org.junit.Assert;
import org.junit.Test;
import exceptions.InvalidSampleException;
public class SampleTest {
    @Test
    public void sampleCreationTest () throws InvalidSampleException{
        Sample s = new Sample(12, 50);

        Assert.assertEquals(12, s.getX());
        Assert.assertEquals(50, s.getY());

        Sample s1 = new Sample(12, 12);
        Assert.assertEquals(12, s1.getX());
        Assert.assertEquals(12, s1.getY());

        Sample s2 = new Sample(0, 0);
        Assert.assertEquals(0, s2.getX());
        Assert.assertEquals(0, s2.getY());
    }

    @Test
    public void invalidSampleTest(){

        try{
            Sample s = new Sample(-12, 0);
            Assert.fail("Should throw an Error");
        }
        catch(Exception e){
            Assert.assertTrue(true);
        }

        try{
            Sample s = new Sample(12, -12);
            Assert.fail("Should throw an Error");
        }
        catch(Exception e){
            Assert.assertTrue(true);

        }


    }
}
