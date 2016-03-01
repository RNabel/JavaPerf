package tester;

import matrixLogic.Multiplier;

import java.text.MessageFormat;
import java.util.ArrayList;

public class TestSuite {
    ArrayList<Test> tests = new ArrayList<Test>();

    public void registerNewTest(Test test) {
        this.tests.add(test);
    }

    public String runAllTests() {
        String output = "";
        for (int i = 0; i < tests.size(); i++) {
            System.out.println(MessageFormat.format("Starting test {0} of {1}", i+1, tests.size()));
            Test test = tests.get(i);
            output += test.toString();
        }

        return output;
    }

    public static TestSuite createTestSuite(int startSize, int maxSize, int step, int iterations, Multiplier multiplier) {
        TestSuite outputSuite = new TestSuite();

        // Create all test test cases.
        for (int i=startSize; i < maxSize; i += step) {
            Test newTest = new Test(i, iterations, multiplier);
            outputSuite.registerNewTest(newTest);
        }

        return outputSuite;
    }

    public Test getTest(int index) {
        return tests.get(index);
    }
}
