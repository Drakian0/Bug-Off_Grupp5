package common.testing;

import common.InputResult;
import decathlon.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestDecaShotPut {

    @TestFactory
    Collection<DynamicTest> dynamicTestsForCalculateResult() {
        List<DynamicTest> dynamicTests = new ArrayList<>();

        DecaShotPut decaShotPut = new DecaShotPut();
        double minDistance = 0.0; // Hardcoded for now, perhaps better to use a getter to get it from the class file incase changes are made
        double maxDistance = 30.0; // Hardcoded for now, perhaps better to use a getter to get it from the class file incase changes are made

        BigDecimal start = new BigDecimal(minDistance); // "0.00"
        BigDecimal end = new BigDecimal(maxDistance); // "30.00"
        BigDecimal increment = new BigDecimal("0.10");

        for (BigDecimal distance = start; distance.compareTo(end) <= 0; distance = distance.add(increment)) {
            double finalDistance = distance.setScale(2, RoundingMode.HALF_UP).doubleValue();
            Executable exec = () -> {
                // Mock the InputResult class
                InputResult mockInputResult = Mockito.mock(InputResult.class);
                when(mockInputResult.enterResult()).thenReturn(finalDistance);

                // Inject the mock into DecaShotPut
                decaShotPut.inputResult = mockInputResult;

                // Set active to true to ensure the loop runs as expected
                decaShotPut.setActive(true);

                double A = decaShotPut.getA();
                double B = decaShotPut.getB();
                double C = decaShotPut.getC();

                int expectedScore = calculateExpectedScore(finalDistance, A, B, C);
                int actualScore = decaShotPut.calculateResult(finalDistance);
                Assertions.assertEquals(expectedScore, actualScore, "The score should match the expected value for distance: " + finalDistance);
            };
            String testName = "Test Testsson " + finalDistance;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(testName, exec);
            dynamicTests.add(dynamicTest);
        }

        // Add tests for values outside the acceptable range
        dynamicTests.add(createOutOfRangeTest("Test Testsson1 " + (minDistance - 0.01), minDistance - 0.01, "Value too low"));
        dynamicTests.add(createOutOfRangeTest("Test Testsson2 " + (maxDistance + 0.01), maxDistance + 0.01, "Value too high"));

        return dynamicTests;
    }

    private DynamicTest createOutOfRangeTest(String testName, double distance, String expectedMessage) {
        return DynamicTest.dynamicTest(testName, () -> {
            // Mock the InputResult class
            InputResult mockInputResult = Mockito.mock(InputResult.class);
            when(mockInputResult.enterResult()).thenReturn(15.0); // Provide a valid value after invalid input in order to break the loop and continue the code

            // Capture the console output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));

            // Inject the mock into DecaShotPut
            DecaShotPut decaShotPut = new DecaShotPut();
            decaShotPut.inputResult = mockInputResult;

            // Set active to true to ensure the loop runs as expected
            decaShotPut.setActive(true);

            // Call the method with the out-of-range value
            decaShotPut.calculateResult(distance);

            // Restore the original System.out
            System.setOut(originalOut);

            // Check the console output for the expected message
            String output = outContent.toString();
            Assertions.assertTrue(output.contains(expectedMessage), "The console output should contain the expected message: " + expectedMessage);
            System.out.println(outContent);
        });
    }

    // Value for calculating field events
    private int calculateExpectedScore(double distance, double A, double B, double C) {
        return (int) (A * Math.pow((distance - B), C));
    }
}
