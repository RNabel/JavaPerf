package matrixLogic;

public class StrassenMultiplier implements Multiplier {

    private Multiplier switchTo;
    private int threshold;

    /**
     * Generate a matrix multiplier which uses the Strassen
     * algorithm for matrices with dimension greater than
     * the supplied threshold and switches to the supplied
     * matrix multiplication algorithm after that.
     */
    public StrassenMultiplier(Multiplier switchTo, int threshold) {
        this.switchTo = switchTo;
        this.threshold = threshold;
    }

    public String toString() {
        return "matrixLogic.StrassenMultiplier, " + threshold + ", " + switchTo;
    }

    public IntMatrix multiply(IntMatrix a, IntMatrix b) {
        int dim = a.getDim();
        if (dim == 1) {
            int [][] result = new int[1][1];
            result[0][0] = a.get(0,0) * b.get(0,0);
            return new IntMatrix(result);
        }
        else if (dim <= threshold) {
            return switchTo.multiply(a,b);
        }
        else {
            IntMatrix a11 = a.getQuarter(Quarter.TOPLEFT);
            IntMatrix a12 = a.getQuarter(Quarter.TOPRIGHT);
            IntMatrix a21 = a.getQuarter(Quarter.BOTTOMLEFT);
            IntMatrix a22 = a.getQuarter(Quarter.BOTTOMRIGHT);

            IntMatrix b11 = b.getQuarter(Quarter.TOPLEFT);
            IntMatrix b12 = b.getQuarter(Quarter.TOPRIGHT);
            IntMatrix b21 = b.getQuarter(Quarter.BOTTOMLEFT);
            IntMatrix b22 = b.getQuarter(Quarter.BOTTOMRIGHT);

            IntMatrix p1 = multiply(IntMatrix.add(a11, a22), IntMatrix.add(b11, b22));
            IntMatrix p2 = multiply(IntMatrix.add(a21, a22), b11);
            IntMatrix p3 = multiply(a11, IntMatrix.subtract(b12, b22));
            IntMatrix p4 = multiply(a22, IntMatrix.subtract(b21, b11));
            IntMatrix p5 = multiply(IntMatrix.add(a11, a12), b22);
            IntMatrix p6 = multiply(IntMatrix.subtract(a21, a11), IntMatrix.add(b11, b12));
            IntMatrix p7 = multiply(IntMatrix.subtract(a12, a22), IntMatrix.add(b21, b22));

            IntMatrix c11 = IntMatrix.add(IntMatrix.subtract(IntMatrix.add(p1, p4), p5), p7);
            IntMatrix c12 = IntMatrix.add(p3, p5);
            IntMatrix c21 = IntMatrix.add(p2, p4);
            IntMatrix c22 = IntMatrix.add(IntMatrix.subtract(IntMatrix.add(p1, p3), p2), p6);

            IntMatrix result = new IntMatrix(dim);
            result.setQuarter(Quarter.TOPLEFT, c11);
            result.setQuarter(Quarter.TOPRIGHT, c12);
            result.setQuarter(Quarter.BOTTOMLEFT, c21);
            result.setQuarter(Quarter.BOTTOMRIGHT, c22);
            return result;
        }
    }

}
