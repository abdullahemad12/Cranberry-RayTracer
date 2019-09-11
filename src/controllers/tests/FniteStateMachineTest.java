import controllers.parsing.FiniteStateMachine;
import controllers.parsing.Transitions;
import exceptions.InvalidStateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FniteStateMachineTest {
    private FiniteStateMachine fsm;
    @Before
    public void setUp() {
        fsm = new FiniteStateMachine();
    }

    @Test
    public void FiniteStateMachineTest1(){
        try{
            fsm.takeAction("unkown");
            Assert.fail("Should throw an Exception!");
        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }
    @Test
    public void FiniteStateMachineTest2() {
        try {
            fsm.takeAction("output");
            Assert.fail("Should throw an Exception!");
        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }

    @Test
    public void FiniteStateMachineTest3() {
        try {
            for(int i = 0; i < 50; i++) {
                Assert.assertEquals(Transitions.BLANK_LINE, fsm.takeAction(""));
                Assert.assertEquals(Transitions.COMMENT, fsm.takeAction("#"));
            }
            Assert.assertEquals(Transitions.SIZE, fsm.takeAction("size"));

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            fsm.takeAction("size");
            Assert.fail("Should throw an Exception!");
        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }

    @Test
    public void FiniteStateMachineTest4() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            Assert.assertEquals(Transitions.OUTPUT, fsm.takeAction("output"));
            Assert.assertEquals(Transitions.MAX_DEPTH, fsm.takeAction("maxdepth"));
            Assert.assertEquals(Transitions.CAMERA, fsm.takeAction("camera"));


        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }
    @Test
    public void FiniteStateMachineTest5() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            fsm.takeAction("maxdepth");
            fsm.takeAction("camera");


        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }
    @Test
    public void FiniteStateMachineTest6() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            fsm.takeAction("output");
            fsm.takeAction("camera");


        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }

    @Test
    public void FiniteStateMachineTest7() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {

            fsm.takeAction("camera");


        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }



    @Test
    public void FiniteStateMachineTest9() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            fsm.takeAction("maxdepth");
            fsm.takeAction("camera");
            fsm.takeAction("maxdepth");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }
    @Test
    public void FiniteStateMachineTest10() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            fsm.takeAction("maxdepth");
            fsm.takeAction("output");
            fsm.takeAction("camera");
            fsm.takeAction("maxdepth");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }

    @Test
    public void FiniteStateMachineTest11() {
        try {
            fsm.takeAction("size");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }

        try {
            fsm.takeAction("maxdepth");
            fsm.takeAction("output");
            fsm.takeAction("camera");
            fsm.takeAction("output");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }

    @Test
    public void FiniteStateMachineTest12() {
        try {
            fsm.takeAction("size");
            fsm.takeAction("camera");
        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
        try {
            fsm.takeAction("maxdepth");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
        try {
            fsm.takeAction("size");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
        try {
            fsm.takeAction("output");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
        try {
            fsm.takeAction("camera");
            Assert.fail("should throw an Exception");

        } catch (InvalidStateException e){
            Assert.assertTrue(true);
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
    }    @Test
    public void FiniteStateMachineTest13() {
        try {
            fsm.takeAction("size");
            fsm.takeAction("camera");

        } catch (InvalidStateException e){
            Assert.fail("should not throw an Exception");
        } catch (Exception e){
            Assert.fail("Another type of Excpetion is thrown " + e.getMessage());
        }
        Transitions[] transitions = Transitions.values();
        for (Transitions transition : transitions) {
            if (transition == Transitions.CAMERA || transition == Transitions.SIZE ||
                    transition == Transitions.OUTPUT || transition == Transitions.MAX_DEPTH) {
                continue;
            }
            try {
                Assert.assertEquals(transition, fsm.takeAction(transition.getValue()));

            } catch (InvalidStateException e) {
                Assert.fail("should not throw an Exception");
            } catch (Exception e) {
                Assert.fail("Another type of Exception is thrown " + e.getMessage());
            }
        }
    }

}
