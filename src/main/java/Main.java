import Helpers.Filter.StringAnalyzer;
import Helpers.Matrices.Matrix;
import Helpers.Matrices.MatrixGenerator;
import Helpers.Strings.TextEncripted;
import Helpers.Strings.TextParser;
import Helpers.Strings.TextTrasnformer;
import Helpers.Writers.TransformedTextWriter;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    static final  String cipherText00Path = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt";;
    static final  String cipherText08Path = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo08_texto_cifrado.txt";;
    static final  String outputFile00Path = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Outputs/Output00.txt";;
    static final  String outputFile08Path = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Outputs/Output08.txt";;

    public static void main(String[] args){
//        fazDecodificacao(cipherText00Path, outputFile00Path);
//        procuraTextoAberto(outputFile00Path);

//        fazDecodificacao(cipherText08Path, outputFile08Path);
        procuraTextoAberto(outputFile08Path);
    }

    private static void fazDecodificacao(String cipherTextPath, String outputFilePath) {
        BigDecimal module26 = new BigDecimal(26);

        String cipherText = new TextEncripted().getTextContent(cipherTextPath);

        TextTrasnformer textTransformer = new TextTrasnformer();
        RealMatrix matrixCipheredNums = textTransformer.transformCharsToNums(cipherText);

        List<Matrix> allPossibleDecoderMatrixList = new MatrixGenerator().getMatrixList();

        TransformedTextWriter transformedTextWriter = new TransformedTextWriter(outputFilePath);
        transformedTextWriter.writeListTransformedText(allPossibleDecoderMatrixList, matrixCipheredNums, textTransformer, module26);
    }

    private static void procuraTextoAberto(String sourceStringsFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceStringsFilePath))) {
            String fullLine, key, alphaNumeric;

            while ((fullLine = reader.readLine()) != null){
                String[] parts = fullLine.split(" -> ");
                key = parts[0];
                alphaNumeric = parts[1];

                new StringAnalyzer().run(alphaNumeric, key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("F");
    }
}