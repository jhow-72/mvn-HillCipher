package Helpers.Matrices;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matrix {
    private boolean isInvertible;
    private int determinant;
    private RealMatrix matrix;
    private RealMatrix inverseMatrix;
    private int invMulplicativoModular;
    private boolean isValid;

    private RealMatrix decodeMatrix;

    public Matrix(RealMatrix matrix){
        this.matrix = matrix;
        this.isInvertible =  inversibleCheck();
        if(this.isInvertible) {
            this.determinant = calcDeterminat();
            this.inverseMatrix = calcInverseMatrix();
            this.invMulplicativoModular = calcInvMulplicativoModular();
        }
        isValid = (this.invMulplicativoModular != -1 && this.isInvertible);
        if(isValid)
            setDecodeMatrix();
    }

    private int calcDeterminat() {
        LUDecomposition luDecomposition = new LUDecomposition(matrix);
        return (int) Math.round(luDecomposition.getDeterminant());

    }

    private RealMatrix calcInverseMatrix() {
        return MatrixUtils.blockInverse(this.matrix, 0);
    }

    private boolean inversibleCheck() {
        LUDecomposition luDecomposition = new LUDecomposition(this.matrix);
        return luDecomposition.getSolver().isNonSingular();
    }

    private int calcInvMulplicativoModular() {
        // (detA * I)mod26 = 1 -> "I" eh o inverso multiplicativo
        int mod = 26;
        int inv = -1;
        for(int i=1; i<10000; i++){
            double result = (this.determinant*i)%mod;
            if(result==1){
                inv = i;
                break;
            }
        }
        return inv;
    }

    private void setDecodeMatrix() {
        // I*A- mod26
        this.decodeMatrix = new Array2DRowRealMatrix(2,2);
        for (int i = 0; i < this.inverseMatrix.getRowDimension(); i++) {
            for (int j = 0; j < this.inverseMatrix.getColumnDimension(); j++) {
                double value = this.invMulplicativoModular * this.inverseMatrix.getEntry(i, j);
                this.decodeMatrix.setEntry(i, j, value);
            }
        }
    }

    public boolean isInvertible() {
        return isInvertible;
    }

    public int getDeterminant() {
        return determinant;
    }

    public RealMatrix getMatrix() {
        return matrix;
    }

    public RealMatrix getInverseMatrix() {
        return inverseMatrix;
    }

    public int getInvMulplicativoModular() {
        return invMulplicativoModular;
    }

    public boolean isValid() {
        return isValid;
    }

    public RealMatrix getDecodeMatrix() {
        return decodeMatrix;
    }
    public String getStringDecodeMatrix() {
        int value1 = new BigDecimal(this.decodeMatrix.getEntry(0,0)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value2 = new BigDecimal(this.decodeMatrix.getEntry(0,1)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value3 = new BigDecimal(this.decodeMatrix.getEntry(1, 0)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value4 = new BigDecimal(this.decodeMatrix.getEntry(1, 1)).setScale(2, RoundingMode.HALF_UP).intValue();

        return String.format("[[%d, %d], [%d, %d]", value1, value2, value3, value4);
    }
}
