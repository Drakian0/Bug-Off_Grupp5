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

public class TestDeca400M {

    @TestFactory
    Collection<DynamicTest> dynamicTestsForCalculateResult() {
        List<DynamicTest> dynamicTests = new ArrayList<>();

        Deca400M deca400M = new Deca400M();
        double minTime = 20.0; // Hardcoded for now, perhaps better to use a getter to get it from the class file incase changes are made
        double maxTime = 100.0; // Hardcoded for now, perhaps better to use a getter to get it from the class file incase changes are made


        BigDecimal start = new BigDecimal(minTime); // "10.00"
        BigDecimal end = new BigDecimal(maxTime); // "30.00"
        BigDecimal increment = new BigDecimal("0.10");

        for (BigDecimal runningTime = start; runningTime.compareTo(end) <= 0; runningTime = runningTime.add(increment)) {
            double finalRunningTime = runningTime.setScale(2, RoundingMode.HALF_UP).doubleValue();
            Executable exec = () -> {
                // Mock the InputResult class
                InputResult mockInputResult = Mockito.mock(InputResult.class);
                when(mockInputResult.enterResult()).thenReturn(finalRunningTime);

                // Inject the mock into Deca400M
                deca400M.inputResult = mockInputResult;

                // Set active to true to ensure the loop runs as expected
                deca400M.setActive(true);

                double A = deca400M.getA();
                double B = deca400M.getB();
                double C = deca400M.getC();

                int expectedScore = calculateExpectedScore(finalRunningTime, A, B, C);
                int actualScore = deca400M.calculateResult(finalRunningTime);
                Assertions.assertEquals(expectedScore, actualScore, "The score should match the expected value for running time: " + finalRunningTime);
            };
            String testName = "Test Testsson " + finalRunningTime;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(testName, exec);
            dynamicTests.add(dynamicTest);
        }

        // Add tests for values outside the acceptable range
        dynamicTests.add(createOutOfRangeTest("Test Testsson1 " + (minTime - 0.01), minTime - 0.01, "Value too low"));
        dynamicTests.add(createOutOfRangeTest("Test Testsson2 " + (maxTime + 0.01), maxTime + 0.01, "Value too high"));

        return dynamicTests;
    }

    private DynamicTest createOutOfRangeTest(String testName, double runningTime, String expectedMessage) {
        return DynamicTest.dynamicTest(testName, () -> {
            // Mock the InputResult class
            InputResult mockInputResult = Mockito.mock(InputResult.class);
            when(mockInputResult.enterResult()).thenReturn(50.0); // Provide a valid value after invalid input

            // Capture the console output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));

            // Inject the mock into Deca400M
            Deca400M deca400M = new Deca400M();
            deca400M.inputResult = mockInputResult;

            // Set active to true to ensure the loop runs as expected
            deca400M.setActive(true);

            // Call the method with the out-of-range value
            deca400M.calculateResult(runningTime);

            // Restore the original System.out
            System.setOut(originalOut);

            // Check the console output for the expected message
            String output = outContent.toString();
            Assertions.assertTrue(output.contains(expectedMessage), "The console output should contain the expected message: " + expectedMessage);
            System.out.println(outContent);
        });
    }

    // Value for calculating field events
    private int calculateExpectedScore(double runningTime, double A, double B, double C) {
        return (int) (A * Math.pow((B - runningTime), C));
    }
}
