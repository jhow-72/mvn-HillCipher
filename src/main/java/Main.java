import Helpers.Matrices.Matrix;
import Helpers.Matrices.MatrixGenerator;
import Helpers.Strings.TextEncripted;
import Helpers.Strings.TextTrasnformer;
import Helpers.Writers.TransformedTextWriter;
import org.apache.commons.math3.linear.RealMatrix;

import java.math.BigDecimal;
import java.util.List;

public class Main {

//    Esse eh o main para testo com o texto00
//    public static void main(String[] args){
//        BigDecimal module26 = new BigDecimal(26);
//        String cipherText = new TextEncripted().getTextContent("/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt");
//        String openText = new TextEncripted().getTextContent("/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Aberto/Hill/Grupo00_texto_aberto.txt");
//
//        TextTrasnformer textTrasnformer = new TextTrasnformer();
//        RealMatrix matrixCipheredNums = textTrasnformer.transformCharsToNums(cipherText);
//
//        double[][] data = {
//                {2, 13},
//                {1, 7}
//        };
//        Matrix matrix = new Matrix(new Array2DRowRealMatrix(data));
//
//        String transformedText = new MatrixCipherProcessor(matrix, textTrasnformer, module26).processCipheredNums(matrixCipheredNums);
//
//        System.out.println(openText);
//        System.out.println(transformedText);
//    }

//    esse eh o main para o texto desconhecido
    public static void main(String[] args){
        BigDecimal module26 = new BigDecimal(26);
        String cipherText = new TextEncripted().getTextContent("/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt");

        TextTrasnformer textTrasnformer = new TextTrasnformer();
        RealMatrix matrixCipheredNums = textTrasnformer.transformCharsToNums(cipherText);

        List<Matrix> allPossibleDecoderMatrixList = new MatrixGenerator().getMatrixList();

        String outputFileName = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Output.txt";
        TransformedTextWriter transformedTextWriter = new TransformedTextWriter(outputFileName);
        transformedTextWriter.writeListTransformedText(allPossibleDecoderMatrixList, matrixCipheredNums, textTrasnformer, module26);

    }
}