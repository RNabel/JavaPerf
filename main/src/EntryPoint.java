import com.sun.org.apache.xpath.internal.operations.Mult;
import matrixLogic.Multiplier;
import matrixLogic.NaiveMultiplier;
import matrixLogic.StrassenMultiplier;
import testRuns.NaiveRuns;
import testRuns.StrassenRuns;
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
//        String output = "--- small naive test run ---\n";
//        TestSuite testSuite = StrassenRuns.createSmallTestWithSmallThreshold();
//        output += testSuite.runAllTests();
//        System.out.println("Finished small set.");
//
//        writeResultsToFile(output);
//
//        output += "--- large naive test run --- \n";
//        output += StrassenRuns.createLargeTestWithSmallThreshold().runAllTests();
//        System.out.println("Finished large set.");
//
//        writeResultsToFile(output);

        output += "--- mini variable threshold test --- \n";
        output+= StrassenRuns.createSmallTestSuiteWithVariableThreshold().runAllTests();
        System.out.println("Finished threshold set.");

        writeResultsToFile(output);
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
