import controllers.parsing.Parser;
import exceptions.UnkownFileExtensionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {
    private Parser parser;
    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parserTest1(){
        try {
            parser.parseFile("/home/abdullah/Dropbox/OSS/Computer Graphics/Homework3/scene3");
            Assert.fail("Should throw an Exception!");
        } catch(UnkownFileExtensionException e) {
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("should throw an UnknownFileExtension Exception instead");
        }

        try{
            parser.parseFile("/home/abdullah/Dropbox/OSS/Computer Graphics/sece.cbg");
            Assert.fail("should Throw an IOException");
        } catch (IOException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("should throw an IOException Exception instead");
        }
    }
    @Test
    public void parserTest2() {
        try {
            parser.parseFile("/home/abdullah/Downloads/scene3.cbg");
        } catch(Exception e) {
            e.printStackTrace();
            Assert.fail("should not throw an Exception");
        }
    }
}
