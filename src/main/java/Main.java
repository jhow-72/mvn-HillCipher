import Helpers.Filter.StringAnalyzer;
import Helpers.Readers.TransformedTextReader;
import Helpers.Strings.TextParser;

import java.math.BigDecimal;
import java.util.List;

public class Main {

//    Esse eh o main para teste com o texto00
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

    public static void main(String[] args){
        BigDecimal module26 = new BigDecimal(26);

//        String cipherText00Path = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt";
//        String cipherText08Path = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo08_texto_cifrado.txt";
//
//        String cipherText = new TextEncripted().getTextContent(cipherText08Path);
//
//        TextTrasnformer textTransformer = new TextTrasnformer();
//        RealMatrix matrixCipheredNums = textTransformer.transformCharsToNums(cipherText);
//
//        List<Matrix> allPossibleDecoderMatrixList = new MatrixGenerator().getMatrixList();

        String output00FilePath = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Outputs/Output00.txt";
        String output08FilePath = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Outputs/Output08.txt";

//        TransformedTextWriter transformedTextWriter = new TransformedTextWriter(output08FilePath);
//        transformedTextWriter.writeListTransformedText(allPossibleDecoderMatrixList, matrixCipheredNums, textTransformer, module26);


        List<String> possibleTexts = new TransformedTextReader().getAllLineStrings(output00FilePath);

        String caminhoPolicarpoQuaresma = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/policarpo_quaresma.txt";
        String policarpoParseado = new TextParser().parse(caminhoPolicarpoQuaresma);

        String caminhoOutputStringAnalyzer = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Outputs/OutputStringAnalyzer.txt";

        for(String text : possibleTexts){
            new StringAnalyzer().run(policarpoParseado, text, caminhoOutputStringAnalyzer);
        }
    }
}