package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.Hep800M;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHep800M {
    private Hep800M hep800M = new Hep800M();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hep800M.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hep800M.calculateResult(69.9);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hep800M.calculateResult(70);
        assertEquals(2026, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hep800M.calculateResult(70.1);
        assertEquals(2024, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hep800M.calculateResult(249.9);
        assertEquals(1, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hep800M.calculateResult(250);
        assertEquals(1, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hep800M.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hep800M.calculateResult(250.1);});
        assertTrue(output.contains("Value too high"));
    }
}