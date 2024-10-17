package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.HeptHightJump;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHepHightJump {
    private HeptHightJump hepHightJump = new HeptHightJump();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hepHightJump.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepHightJump.calculateResult(-0.1);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hepHightJump.calculateResult(0);
        assertEquals(0, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hepHightJump.calculateResult(0.1);
        assertEquals(0, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hepHightJump.calculateResult(299.9);
        assertEquals(2732, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hepHightJump.calculateResult(300);
        assertEquals(2733, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hepHightJump.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepHightJump.calculateResult(300.1);});
        assertTrue(output.contains("Value too high"));
    }
}