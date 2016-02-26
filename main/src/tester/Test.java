package tester;

import matrixLogic.IntMatrix;
import matrixLogic.Multiplier;

public class Test {
    private int size;
    private int iterations;
    private Multiplier multiplier;

    public Test(int size, int iterations, Multiplier multiplier) {
        this.size = size;
        this.iterations = iterations;
        this.multiplier = multiplier;
    }

    public int runTest() {
        // Create intMatrices.
        IntMatrix intMatrixA = IntMatrix.createRandomMatrix(size);
        IntMatrix intMatrixB = IntMatrix.createRandomMatrix(size);

        // TODO start timer.
        int totalTime = 0;

        // Multiply matrices.
        for (int i = 0; i < iterations; i++) {
            multiplier.multiply(intMatrixA, intMatrixB);
            multiplier.multiply(intMatrixB, intMatrixA);
        }

        // TODO stop timer.

        return totalTime;
    }
}
