package testRuns;

import com.sun.org.apache.xpath.internal.operations.Mult;
import matrixLogic.Multiplier;
import matrixLogic.NaiveMultiplier;
import matrixLogic.StrassenMultiplier;
import tester.Test;
import tester.TestSuite;

public class StrassenRuns {
    private static Multiplier naiveMultiplier = new NaiveMultiplier();

    private static int smallThreshold = 10;

    public static TestSuite createSmallTestWithSmallThreshold() {
        Multiplier multiplier = new StrassenMultiplier(naiveMultiplier, smallThreshold);
        return TestSuite.createTestSuite(1, 20, 1, 10, multiplier);
    }

    public static TestSuite createLargeTestWithSmallThreshold() {
        Multiplier multiplier = new StrassenMultiplier(naiveMultiplier, smallThreshold);
        return TestSuite.createTestSuite(40, 41, 1, 2, multiplier);
    }

    public static TestSuite createHugeTestWithSmallThreshold() {
        Multiplier multiplier = new StrassenMultiplier(naiveMultiplier, smallThreshold);
        return TestSuite.createTestSuite(100, 101, 2, 2, multiplier);
    }

    public static TestSuite createLargeTestSuiteWithVariableThreshold() {
        TestSuite testSuite = new TestSuite();
        Multiplier strassenMultiplier;

        for (int i = 50; i < 800; i += 10) { // Max dimension.
            int j = (i > 50) ? 50 : i;
            for (; j <= i; j += 10) { // Threshold.
                strassenMultiplier = new StrassenMultiplier(naiveMultiplier, j);
                Test newTest = new Test(i, 10, strassenMultiplier);
                testSuite.registerNewTest(newTest);
            }
        }

        return testSuite;
    }

    public static TestSuite createSmallTestSuiteWithVariableThreshold() {
        TestSuite testSuite = new TestSuite();
        Multiplier strassenMultiplier;

        for (int i = 1500; i <= 1500; i += 100) {
//            testSuite.registerNewTest(new Test(i, 3, naiveMultiplier));

            for (int j = 600; j <= 600; j += 10) {
                strassenMultiplier = new StrassenMultiplier(naiveMultiplier, j);
                testSuite.registerNewTest(new Test(i, 3, strassenMultiplier));
            }
        }

        return testSuite;
    }
}
