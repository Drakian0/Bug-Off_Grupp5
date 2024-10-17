package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.Hep100MHurdles;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHep100MHurdles {
    private Hep100MHurdles hurdles = new Hep100MHurdles();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(10.0);
        hurdles.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hurdles.calculateResult(9.9);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hurdles.calculateResult(10);
        assertEquals(1617, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hurdles.calculateResult(10.1);
        assertEquals(1600, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hurdles.calculateResult(29.9);
        assertEquals(0, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hurdles.calculateResult(30);
        assertEquals(0, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(10.0);
        hurdles.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hurdles.calculateResult(30.1);});
        assertTrue(output.contains("Value too high"));
    }
}
