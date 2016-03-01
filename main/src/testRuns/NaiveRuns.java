package testRuns;

import matrixLogic.Multiplier;
import matrixLogic.NaiveMultiplier;
import tester.TestSuite;

public class NaiveRuns {
    static Multiplier naiveMultiplier = new NaiveMultiplier();

    public static TestSuite createSmallTestRun() {
        return TestSuite.createTestSuite(800, 3000, 100, 3, naiveMultiplier);
    }

    public static TestSuite createLargeTestRun() {
        return TestSuite.createTestSuite(30, 32, 1, 10, naiveMultiplier);
    }

    public static TestSuite createHugeTestRun() {
        return TestSuite.createTestSuite(100, 101, 2, 3, naiveMultiplier);
    }
}

