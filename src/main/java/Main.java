import Helpers.Dictionaries.Dictionarie;
import Helpers.Matrices.Matrix;
import Helpers.Strings.TextEncripted;
import Helpers.Strings.TextTrasnformer;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

    public static void main(String[] args){
        BigDecimal module26 = new BigDecimal(26);
        String cipherText = new TextEncripted().getTextContent("/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt");
        String openText = new TextEncripted().getTextContent("/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Aberto/Hill/Grupo00_texto_aberto.txt");

        TextTrasnformer textTrasnformer = new TextTrasnformer();
        RealMatrix matrixCipheredNums = textTrasnformer.transformCharsToNums(cipherText);
//        System.out.println(matrixCipheredNums);

        double[][] data = {
            {2, 13},
            {1, 7}
        };
        Matrix matrix = new Matrix(new Array2DRowRealMatrix(data));

        String transformedText ="";
        for (int i = 0; i < matrixCipheredNums.getRowDimension(); i++) {
            for (int j = 0; j < matrixCipheredNums.getColumnDimension(); j+=2) {
                BigDecimal cipherNum1 = new BigDecimal(matrixCipheredNums.getEntry(i, j));
                BigDecimal cipherNum2 = new BigDecimal(matrixCipheredNums.getEntry(i, j+1));
                double[] cipherEntryPair = new double[]{cipherNum1.doubleValue(),cipherNum2.doubleValue()};

                RealMatrix temp = new Array2DRowRealMatrix(cipherEntryPair);
                temp = matrix.getDecodeMatrix().multiply(temp);

                BigDecimal value1 = new BigDecimal(temp.getEntry(0,0));
                BigDecimal value2 = new BigDecimal(temp.getEntry(1, 0));
//                System.out.println(value1);
//                System.out.println(value2);

                double[] cipherEntryPairModule26 = new double[]{value1.setScale(2, RoundingMode.HALF_UP).remainder(module26).doubleValue(),value2.setScale(2, RoundingMode.HALF_UP).remainder(module26).doubleValue()};
                temp = new Array2DRowRealMatrix(cipherEntryPairModule26);
                transformedText += textTrasnformer.transformNumToChars(temp);
            }
        }

        System.out.println("texto cifrad: "+cipherText);
        System.out.println("texto transf: "+transformedText);
        System.out.println("texto aberto: "+openText);
    }
}