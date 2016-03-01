package tester;

import matrixLogic.IntMatrix;
import matrixLogic.Multiplier;

public class Test {
    private int dimension;
    private int iterations;
    private Multiplier multiplier;
    private long elapsedTime = -1;

    public Test(int dimension, int iterations, Multiplier multiplier) {
        this.dimension = dimension;
        this.iterations = iterations;
        this.multiplier = multiplier;
    }

    /**
     * Runs the test.
     * @return The elapsed time in nanoseconds.
     */
    public long runTest() {
        // Create intMatrices.
        IntMatrix intMatrixA = IntMatrix.createRandomMatrix(dimension);
        IntMatrix intMatrixB = IntMatrix.createRandomMatrix(dimension);

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

        // Get multiplier-specific results.
        String multiplierSpecification = this.multiplier.toString();

        // CSV Format: matrix dimension {int}, iterations {int}, elapsed time {long}, average time {double}
        return dimension + ", " + iterations + ", " + elapsedTime + ", " + elapsedTime / (double)iterations + ", " + multiplierSpecification + "\n";
    }

    public Multiplier getMultiplier() {
        return multiplier;
    }
}
