package Helpers.Matrices;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MatrixGenerator {

    private List<Matrix> matrixList = new ArrayList<>();

    public List<Matrix> generateAllPossibleValidMatrices(int modulus){
        for(int a=0; a < modulus; a++){
            for(int b=0; b < modulus; b++){
                for(int c=0; c < modulus; c++){
                    for(int d=0; d < modulus; d++){
                        double[][] data = {
                                {a, b},
                                {c, d}
                        };

                        Matrix matrix = new Matrix(new Array2DRowRealMatrix(data));
                        if (matrix.isValid()){
                            this.matrixList.add(matrix);
                        }
                    }
                }
            }
        }
        return this.matrixList;
    }

    public List<Matrix> getMatrixList() {
        if(this.matrixList.isEmpty())
            generateAllPossibleValidMatrices(BigDecimal.valueOf(26).intValue());

        return matrixList;
    }
}
