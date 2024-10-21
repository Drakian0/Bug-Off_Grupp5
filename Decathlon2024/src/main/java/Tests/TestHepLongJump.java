package Tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import org.junit.jupiter.api.Test;
import heptathlon.HeptLongJump;
import common.InputResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class TestHepLongJump {
    private HeptLongJump hepLongJump = new HeptLongJump();

    @Test
    public void testBelowLowerBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hepLongJump.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepLongJump.calculateResult(-0.1);});
        assertTrue(output.contains("Value too low"));
    }

    @Test
    public void testOnLowerBoundary() {
        int score = hepLongJump.calculateResult(0);
        assertEquals(0, score);
    }

    @Test
    public void testAboveLowerBoundary() {
        int score = hepLongJump.calculateResult(0.1);
        assertEquals(0, score);
    }

    @Test
    public void testBelowUpperBoundary() {
        int score = hepLongJump.calculateResult(999.9);
        assertEquals(2299, score);
    }

    @Test
    public void testOnUpperBoundary() {
        int score = hepLongJump.calculateResult(1000);
        assertEquals(2299, score);
    }

    @Test
    public void testAboveUpperBoundary() throws Exception{
        InputResult mockInputResult = Mockito.mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(100.0);
        hepLongJump.inputResult = mockInputResult;
        String output = tapSystemOut(() -> {hepLongJump.calculateResult(1000.1);});
        assertTrue(output.contains("Value too high"));
    }
}