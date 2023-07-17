package Helpers.Matrices;

import Helpers.Strings.TextTrasnformer;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MatrixCipherProcessor {
    private Matrix matrix;
    private TextTrasnformer textTransformer;
    private BigDecimal module26;
    private String initialString;
    StringBuilder transformedText;

    public MatrixCipherProcessor(Matrix matrix, TextTrasnformer textTransformer, BigDecimal module26, String initialString) {
        this.matrix = matrix;
        this.textTransformer = textTransformer;
        this.module26 = module26;
        this.initialString = initialString;
        if( !this.initialString.isEmpty() ){
            transformedText = new StringBuilder();
            this.transformedText.append(this.initialString);
        }

    }

    /**
     * Processa os números cifrados contidos na matriz especificada.
     * Retorna o texto transformado.
     *
     * @param matrixCipheredNums A matriz contendo os números cifrados
     * @return O texto transformado
     */
    public String decodeCipheredNums(RealMatrix matrixCipheredNums) {
        int numRows = matrixCipheredNums.getRowDimension();
        int numCols = matrixCipheredNums.getColumnDimension();

        // apesar de ser um for aninhado, eh esperado que a qtd de linhas seja sempre 1, portanto é O(n)
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j+=2) {
                // busca os valores do par que sera multiplicado pela chave decodificadora
                BigDecimal cipherNum1 = new BigDecimal(matrixCipheredNums.getEntry(i, j));
                BigDecimal cipherNum2 = new BigDecimal(matrixCipheredNums.getEntry(i, j+1));
                double[] cipherEntryPair = new double[]{ // esse array de double eh necessario para criar a nova matriz
                        cipherNum1.doubleValue(),
                        cipherNum2.doubleValue()
                };

                // cria uma RealMatrix temporária que tem os valores do par que sera multiplicado pela matriz decodificadora
                // isso foi feito pois o metodo multiply funciona apenas com o tipo RealMatrix e esse metodo eh o mais confiavel para essa operacao
                // essa multiplicacao decodifica os numeros codificados, restando apenas tirar os modulos deles mais a frente
                RealMatrix temp = new Array2DRowRealMatrix(cipherEntryPair);
                temp = matrix.getDecodeMatrix().multiply(temp);

                // separa os valores, agora multiplicados pela matriz decodificadora, double novamente,
                // transforma eles em BigDecimal para realizar operacoes mais precisas
                // lembrando que temp possui apenas 2 valores, pois jah eh o par que esta sendo decodificado nessa iteracao
                BigDecimal value1 = new BigDecimal(temp.getEntry(0,0));
                BigDecimal value2 = new BigDecimal(temp.getEntry(1, 0));

                // prepara os dados dentro de um novo array the doubles que é necessário para criar a ultima RealMatrix
                // aplica o modulo26 para ficar em de acordo com o alfabeto dec2alf;
                double[] cipherEntryPairModule26 = new double[]{
                        value1.setScale(2, RoundingMode.HALF_UP).remainder(module26).doubleValue(),
                        value2.setScale(2, RoundingMode.HALF_UP).remainder(module26).doubleValue()
                };
                temp = new Array2DRowRealMatrix(cipherEntryPairModule26);
                transformedText.append(textTransformer.transformNumToChars(temp));
            }
        }

        return transformedText.toString();
    }
}
