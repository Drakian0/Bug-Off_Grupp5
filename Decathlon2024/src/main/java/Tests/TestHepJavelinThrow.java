package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.HeptJavelinThrow;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHepJavelinThrow {
    private HeptJavelinThrow hepJavelinThrow = new HeptJavelinThrow();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hepJavelinThrow.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepJavelinThrow.calculateResult(-0.1);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hepJavelinThrow.calculateResult(0);
        assertEquals(0, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hepJavelinThrow.calculateResult(0.1);
        assertEquals(0, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hepJavelinThrow.calculateResult(109.9);
        assertEquals(2043, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hepJavelinThrow.calculateResult(110);
        assertEquals(2045, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hepJavelinThrow.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepJavelinThrow.calculateResult(110.1);});
        assertTrue(output.contains("Value too high"));
    }
}