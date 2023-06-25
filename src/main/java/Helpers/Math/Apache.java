package Helpers.Math;

import org.apache.commons.math3.linear.*;

public class Apache {
    public static void main(String[] args) {
        double[][] matrixData = {
                {1,2},
                {3,4},
        };
        RealMatrix matrix = MatrixUtils.createRealMatrix(matrixData);
        double determinant = new LUDecomposition(matrix).getDeterminant();

        System.out.println("Determinant: "+determinant);
    }
}
