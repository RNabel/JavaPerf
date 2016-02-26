package matrixLogic;

import java.util.Random;

public class IntMatrix {
    private static int NUMBER_RANGE = 1000;
    /**
     * Creates matrix with random entries of order dim.
     * @param dim {int} The dimension of the matrix.
     * @return {matrixLogic.IntMatrix} The matrix with random entries.
     */
    public static IntMatrix createRandomMatrix(int dim) {
        return createRandomMatrix(dim, NUMBER_RANGE);
    }

    public static IntMatrix createRandomMatrix(int dim, int numberRange) {
        Random random = new Random();
        IntMatrix currentMatrix = new IntMatrix(dim);

        // Create random values.
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int randomEntry = random.nextInt(NUMBER_RANGE);
                currentMatrix.set(i, j, randomEntry);
            }
        }

        return currentMatrix;
    }


        private int [][] values;

    /**
     * Creates a square matrix with the given dimension; the matrix is
     * filled by 0's. Also pad up to the next power of 2. <-- Why?!
     */
    public IntMatrix(int dim) {
        this.values = new int[dim][dim];
        padDimension();
    }

    /**
     * Creates a matrix containing the given values. Also pad up to a
     * square matrix which is of dimension the next power of 2.
     */
    public IntMatrix(int[][] values) {
        this.values = values;
        padDimension();
    }

    /**
     * Returns the dimension of the matrix.
     */
    public int getDim() {
        return values.length;
    }

    /**
     * Returns the values stored in the matrix.
     */
    public int[][] getValues() {
        return values;
    }

    /**
     * Update the value stored at the specified location.
     */
    public void set(int i, int j, int val) {
        values[i][j] = val;
    }

    /**
     * Get the value stored at the specified location.
     */
    public int get(int i, int j) {
        return values[i][j];
    }

    /**
     * Pads the matrix with 0 values (at the right and the
     * bottom) so that the matrix is a square matrix and the
     * dimension is a power of 2.
     */
    public void padDimension() {
        int dim = 1;
        while (dim < values.length)
            dim *= 2;
        padDimensionTo(dim);
    }

    /**
     * Pads the matrix with 0 values (at the right and the
     * bottom) so that the matrix is a square matrix with
     * the given dimension.
     */
    public void padDimensionTo(int dim) {
        int oldDim = this.values.length;
        if (oldDim < dim) {
            int[][] values = new int[dim][dim];
            for (int i = 0; i < oldDim; i++) {
                for (int j = 0; j < oldDim; j++) {
                    values[i][j] = this.values[i][j];
                }
            }
            this.values = values;
        }
    }

    /**
     * Return a (square) slice of the matrix, starting from
     * the given coordinates with the given dimension.
     */
    public IntMatrix getSlice(int x, int y, int dim) {
        int[][] values = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                values[i][j] = this.values[x+i][y+j];
            }
        }
        return new IntMatrix(values);
    }

    /**
     * Update the values of a slice of the matrix, starting
     * from the given coordinates.
     */
    public void setSlice(int x, int y, IntMatrix m) {
        int dim = m.getDim();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.set(x+i, y+j, m.get(i,j));
            }
        }
    }

    /**
     * Return a quarter of the given matrix; a matrix has 4
     * quarters: TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT.
     */
    public IntMatrix getQuarter(Quarter q) {
        int half = this.getDim() / 2;
        switch (q) {
            case TOPLEFT     : return getSlice( 0   , 0   , half );
            case TOPRIGHT    : return getSlice( 0   , half, half );
            case BOTTOMLEFT  : return getSlice( half, 0   , half );
            case BOTTOMRIGHT : return getSlice( half, half, half );
            default          : return null;
        }
    }

    /**
     * Update the values of a quarter of the given matrix; a
     * matrix has 4 quarters: TOPLEFT, TOPRIGHT, BOTTOMLEFT,
     * BOTTOMRIGHT.
     */
    public void setQuarter(Quarter q, IntMatrix m) {
        int half = this.getDim() / 2;
        switch (q) {
            case TOPLEFT     : setSlice( 0   , 0   , m );
            case TOPRIGHT    : setSlice( 0   , half, m );
            case BOTTOMLEFT  : setSlice( half, 0   , m );
            case BOTTOMRIGHT : setSlice( half, half, m );
        }
    }

    /**
     * Matrix addition.
     */
    public static IntMatrix add(IntMatrix a, IntMatrix b) {
        int dim = a.getDim();

        IntMatrix c = new IntMatrix(dim);

        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                c.set(i, j, a.get(i,j) + b.get(i,j));
            }
        }

        return c;
    }

    /**
     * Matrix subtraction.
     */
    public static IntMatrix subtract(IntMatrix a, IntMatrix b) {
        int dim = a.getDim();

        IntMatrix c = new IntMatrix(dim);

        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                c.set(i, j, a.get(i,j) - b.get(i,j));
            }
        }

        return c;
    }

    /**
     * A string representation of the matrix.
     */
    public String toString() {
        int dim = values.length;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                b.append(values[i][j]);
                b.append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }

}
