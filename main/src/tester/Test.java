package tester;

import matrixLogic.IntMatrix;
import matrixLogic.Multiplier;

public class Test {
    private int size;
    private int iterations;
    private Multiplier multiplier;
    private long elapsedTime = -1;

    public Test(int size, int iterations, Multiplier multiplier) {
        this.size = size;
        this.iterations = iterations;
        this.multiplier = multiplier;
    }

    /**
     * Runs the test.
     * @return The elapsed time in nanoseconds.
     */
    public long runTest() {
        // Create intMatrices.
        IntMatrix intMatrixA = IntMatrix.createRandomMatrix(size);
        IntMatrix intMatrixB = IntMatrix.createRandomMatrix(size);

        // Start timer.
        long startTime = System.nanoTime();

        // Multiply matrices.
        for (int i = 0; i < iterations; i++) {
            multiplier.multiply(intMatrixA, intMatrixB);
            multiplier.multiply(intMatrixB, intMatrixA);
        }

        // Stop the timer.
        elapsedTime = System.nanoTime() - startTime;
        return elapsedTime;
    }

    @Override
    public String toString() {
        if (elapsedTime == -1) { // If elapsed time not run yet.
            runTest();
        }

        // CSV Format: matrix size {int}, iterations {int}, elapsed time {long}, average time {double}
        return size + ", " + iterations + ", " + elapsedTime + ", " + elapsedTime / (double)iterations + "\n";
    }

    public Multiplier getMultiplier() {
        return multiplier;
    }
}
