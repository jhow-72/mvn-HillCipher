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
            this.determinant = calcDeterminat();
            this.inverseMatrix = calcInverseMatrix();
            this.invMulplicativoModular = calcInvMulplicativoModular().intValue();
        }
        isValid = (this.isInvertible && this.invMulplicativoModular!=-1);
        if(isValid)
            setDecodeMatrix();
    }

    private BigInteger calcDeterminat() {
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        return new BigInteger(String.valueOf(Math.round(luDecomposition.getDeterminant())));
    }

    private RealMatrix calcInverseMatrix() {
        LUDecomposition decomposition = new LUDecomposition(this.realMatrix);
        return decomposition.getSolver().getInverse();
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
                double entryDouble = new BigDecimal(String.valueOf(this.inverseMatrix.getEntry(i, j))).doubleValue();
                double value = this.invMulplicativoModular*entryDouble;
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

    public String getStringMatrix() {
        int value1 = new BigDecimal(this.realMatrix.getEntry(0,0)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value2 = new BigDecimal(this.realMatrix.getEntry(0,1)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value3 = new BigDecimal(this.realMatrix.getEntry(1, 0)).setScale(2, RoundingMode.HALF_UP).intValue();
        int value4 = new BigDecimal(this.realMatrix.getEntry(1, 1)).setScale(2, RoundingMode.HALF_UP).intValue();

        return String.format("[[%d, %d], [%d, %d]", value1, value2, value3, value4);
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
