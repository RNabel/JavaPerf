import com.sun.org.apache.xpath.internal.operations.Mult;
import matrixLogic.Multiplier;
import matrixLogic.NaiveMultiplier;
import matrixLogic.StrassenMultiplier;
import tester.Test;
import tester.TestSuite;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntryPoint {
    private static int NUMBER_RANGE = 1000;
    private static String SAVE_FOLDER_PATH = "/home/robin/Documents/Repositories/JavaPerf/output/";

    public static void main(String[] args) {
        String output = "";
        TestSuite[] testSuites = getEquivalentTestSuites(1, 20, 22, 1);

        for (int i = 0; i < testSuites.length; i++) {
            TestSuite currentSuite = testSuites[i];
            String currentMultiplier = currentSuite.getTest(0).getMultiplier().getClass().toString();
            output += "\n-------------" + currentMultiplier +"-------------------\n";
            output += currentSuite.runAllTests();
        }

        writeResultsToFile(output);
    }

    private static TestSuite getNaiveTestSuite() {
        int iterations = 100;
        Multiplier multiplier = new NaiveMultiplier();

        return TestSuite.createTestSuite(0, 30, 1, iterations, multiplier);
    }

    private static TestSuite getStrassenTestSuite() {
        int iterations = 100;
        Multiplier multiplier = new StrassenMultiplier(null, 0);

        return TestSuite.createTestSuite(0, 30, 1, iterations, multiplier);
    }

    /**
     * Creates Naive and Strassen test suites with the same parameters.
     * @return
     */
    private static TestSuite[] getEquivalentTestSuites(int iterations, int minSize, int maxSize, int step) {
        Multiplier naiveMultiplier = new NaiveMultiplier();
        Multiplier strassenMultiplier = new StrassenMultiplier(null, 0);

        TestSuite naiveTestSuite = TestSuite.createTestSuite(minSize, maxSize, step, iterations, naiveMultiplier);
        TestSuite strassenTestSuite = TestSuite.createTestSuite(minSize, maxSize, step, iterations, strassenMultiplier);

        return new TestSuite[]{naiveTestSuite, strassenTestSuite};
    }

    private static String getCurrentTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private static void writeResultsToFile(String input) {

        String fileName = getCurrentTimeStamp() + ".csv";
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FOLDER_PATH + fileName);
            fileWriter.write(input);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Could not open file!");
        }
    }
}
