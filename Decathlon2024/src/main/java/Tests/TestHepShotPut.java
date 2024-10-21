package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.HeptShotPut;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHepShotPut {
    private HeptShotPut hepShotPut = new HeptShotPut();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(20.0);
        hepShotPut.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepShotPut.calculateResult(-0.1);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hepShotPut.calculateResult(0);
        assertEquals(0, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hepShotPut.calculateResult(0.1);
        assertEquals(0, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hepShotPut.calculateResult(29.9);
        assertEquals(1880, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hepShotPut.calculateResult(30);
        assertEquals(1887, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(20.);
        hepShotPut.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepShotPut.calculateResult(30.1);});
        assertTrue(output.contains("Value too high"));
    }
}