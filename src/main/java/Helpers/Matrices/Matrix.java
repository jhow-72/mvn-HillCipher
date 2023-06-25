package Helpers.Matrices;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Matrix {
    private boolean isInvertible;
    private int determinant;
    private RealMatrix matrix;
    private RealMatrix inverseMatrix;
    private int invMulplicativoModular;
    private boolean isValid;

    public Matrix(RealMatrix matrix){
        this.matrix = matrix;
        this.isInvertible =  inversibleCheck();
        if(this.isInvertible) {
            this.determinant = calcDeterminat();
            this.inverseMatrix = calcInverseMatrix();
            this.invMulplicativoModular = calcInvMulplicativoModular();
        }
        isValid = (this.invMulplicativoModular != -1 && this.isInvertible);
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
        int mod = 26;
        int inv = -1;
        for(int i=1; i<10000; i++){
            double result = (this.determinant*i)%mod;
            if(result==1){
                inv = i;
                System.out.println("fez o break!!");
                break;
            }
        }
        return inv;
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
}
