package Helpers.Matrices;

import org.apache.commons.math3.linear.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Matrix {
    private boolean isInvertible;
    private BigInteger determinant;
    private RealMatrix realMatrix;
    private RealMatrix inverseMatrix;
    private int invMulplicativoModular;
    private boolean isValid;
    private BigInteger modulus;
    private RealMatrix decodeMatrix;

    public Matrix(RealMatrix matrix, int modulus){
        this.realMatrix = matrix;
        this.modulus = BigInteger.valueOf(modulus);
        this.isInvertible =  inversibleCheck();
        if(this.isInvertible) {
            this.inverseMatrix = calcInverseMatrix();
            this.determinant = calcDeterminant();
            this.invMulplicativoModular = calcInvMulplicativoModular().intValue();
        }
        isValid = (this.isInvertible && this.invMulplicativoModular!=-1);
        if(isValid)
            setDecodeMatrix();
    }

    private BigInteger calcDeterminant() {
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        BigInteger determinant = new BigInteger(String.valueOf(Math.round(luDecomposition.getDeterminant())));
        return new BigInteger(String.valueOf(Math.round(luDecomposition.getDeterminant())));
    }

    private RealMatrix calcInverseMatrix() {
        RealMatrix inverse = MatrixUtils.createRealMatrix(2, 2);
        inverse.setEntry(0, 0, this.realMatrix.getEntry(1, 1));
        inverse.setEntry(0, 1, -this.realMatrix.getEntry(0, 1));
        inverse.setEntry(1, 0, -this.realMatrix.getEntry(1, 0));
        inverse.setEntry(1, 1, this.realMatrix.getEntry(0, 0));
        return inverse;
    }

    private boolean inversibleCheck() {
        LUDecomposition luDecomposition = new LUDecomposition(this.realMatrix);
        return luDecomposition.getSolver().isNonSingular();
    }

    private BigInteger calcInvMulplicativoModular() {
        try {
            return new BigInteger(String.valueOf(this.determinant)).modInverse(this.modulus);
        }catch (Exception e){
            return BigInteger.valueOf(-1);
        }
    }

    private void setDecodeMatrix() {
        // I*A- mod26
        this.decodeMatrix = new Array2DRowRealMatrix(2,2);
        for (int i = 0; i < this.inverseMatrix.getRowDimension(); i++) {
            for (int j = 0; j < this.inverseMatrix.getColumnDimension(); j++) {
                double entryDouble = new BigDecimal(String.valueOf(this.inverseMatrix.getEntry(i, j))).setScale(2, RoundingMode.HALF_UP).doubleValue();
                double value = this.invMulplicativoModular*entryDouble;

                value = value%26;
                if(value<0){
                    value += 26;
                }

                this.decodeMatrix.setEntry(i, j, value);
            }
        }
    }

    public boolean isInvertible() {
        return isInvertible;
    }

    public BigInteger getDeterminant() {
        return determinant;
    }

    public RealMatrix getRealMatrix() {
        return realMatrix;
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
        return getStringMatrix(this.decodeMatrix);
    }

    public String getStringMatrix() {
        return getStringMatrix(this.realMatrix);
    }

    public String getStringInverseMatrix() {
        return getStringMatrix(this.inverseMatrix);
    }

    private String getStringMatrix(RealMatrix matrix){
        int value1 = new BigDecimal(matrix.getEntry(0,0)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value2 = new BigDecimal(matrix.getEntry(0,1)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value3 = new BigDecimal(matrix.getEntry(1, 0)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value4 = new BigDecimal(matrix.getEntry(1, 1)).setScale(2, RoundingMode.HALF_UP).intValue();

        return String.format("[[%d, %d], [%d, %d]]", value1, value2, value3, value4);
    }
}
