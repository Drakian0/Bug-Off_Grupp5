package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.Hep200M;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHep200M {
    private Hep200M hep200M = new Hep200M();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(50.0);
        hep200M.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hep200M.calculateResult(19.9);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hep200M.calculateResult(20);
        assertEquals(1398, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hep200M.calculateResult(20.1);
        assertEquals(1387, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hep200M.calculateResult(99.9);
        assertEquals(0, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hep200M.calculateResult(100);
        assertEquals(0, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(50.0);
        hep200M.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hep200M.calculateResult(100.1);});
        assertTrue(output.contains("Value too high"));
    }
}
