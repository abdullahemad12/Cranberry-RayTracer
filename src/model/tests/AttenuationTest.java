import model.graphics.light.Attenuation;
import org.junit.Assert;
import org.junit.Test;

public class AttenuationTest {

    @Test
    public void attenuationTest1() {
        Attenuation attn = new Attenuation(1, 0, 0);

        for(int i = 1; i < 500; i++) {
            Assert.assertEquals(1.0, attn.computeAttenuation(i), 0);
        }
    }

    @Test
    public void attenuationTest2() {
        Attenuation attn = new Attenuation(1.5, 0.5, 0.001);

        Assert.assertEquals(4000.0/17121.0, attn.computeAttenuation(5.5), 0.00000000001);

        Assert.assertEquals(4000.0 / 7001.0, attn.computeAttenuation(0.5), 0.00000000001);

        Assert.assertEquals(2.0 / 3003.0, attn.computeAttenuation(1000), 0.0000000001);

    }
    @Test
    public void attenuationTest3() {
        Attenuation attn = new Attenuation(5, 20, 10);

        Assert.assertEquals(2.0/835.0, attn.computeAttenuation(5.5), 0.00000000001);

        Assert.assertEquals(2.0/35.0, attn.computeAttenuation(0.5), 0.00000000001);

        Assert.assertEquals(1.0/10020005.0, attn.computeAttenuation(1000), 0.0000000001);
    }
}
