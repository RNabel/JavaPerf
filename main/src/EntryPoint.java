import matrixLogic.IntMatrix;

import java.util.Random;

public class EntryPoint {
    private static int NUMBER_RANGE = 1000;

    public static void main(String[] args) {
        IntMatrix intMat = IntMatrix.createRandomMatrix(10);
        System.out.println(intMat.toString());
    }
}
