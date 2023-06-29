import Helpers.Filter.StringAnalyzer;
import Helpers.Matrices.Matrix;
import Helpers.Matrices.MatrixGenerator;
import Helpers.Strings.TextEncripted;
import Helpers.Strings.TextParser;
import Helpers.Strings.TextTrasnformer;
import Helpers.Writers.TransformedTextWriter;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Main {
    static final  String PolicarpoPath = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Infos\\Texto_conhecido\\policarpo_quaresma.txt";
    static final String PolicarpoParseadoPath = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Outputs\\PolicarpoParseadoLeoPath.txt";
    static final  String cipherText00Path = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Infos\\Texto_conhecido\\Cifrado\\Hill\\Grupo00_texto_cifrado.txt";
    static final  String cipherText08Path = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Infos\\Texto_conhecido\\Cifrado\\Hill\\Grupo08_texto_cifrado.txt";
    static final  String outputFile00Path = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Outputs\\Output00.txt";
    static final  String outputFile08Path = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Outputs\\Output08.txt";

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

        List<Matrix> allPossibleDecoderMatrixList = new MatrixGenerator().getMatrixList(module26);

        TransformedTextWriter transformedTextWriter = new TransformedTextWriter(outputFilePath);
        transformedTextWriter.writeListTransformedText(allPossibleDecoderMatrixList, matrixCipheredNums, textTransformer, module26);
    }

    private static void procuraTextoAberto(String sourceStringsFilePath) {
        BigInteger contadorIteracoesTotal = BigInteger.ZERO;
//        caso n tenha o arquivo PolicarpoParseado, descomente a linha a baixo e comente do StringBuilder até o final do try catch
//        String policarpoParseado = new TextParser().parse(Main.PolicarpoPath);
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(Main.PolicarpoParseadoPath))) {
            content.append(reader.readLine());
        } catch (Exception e){
            System.out.println("erro ao ler o policarpo parseado");
            e.printStackTrace();
        }
        String policarpoParseado = content.toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceStringsFilePath))) {
            String fullLine, key, alphaNumeric;

            while ((fullLine = reader.readLine()) != null){
                String[] parts = fullLine.split(" -> ");
                key = parts[0];
                alphaNumeric = parts[1];

                contadorIteracoesTotal =contadorIteracoesTotal.add(BigInteger.ONE);
                new StringAnalyzer().run(alphaNumeric, key, policarpoParseado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("F");
        System.out.println("chaves tentadas: " + contadorIteracoesTotal);
        System.out.println("total de iterações: " + contadorIteracoesTotal);
    }

    private static void comparaPolicarpos(String policarpoParseado) {
        String PolicarpoParseadoLeoPath = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Outputs\\PolicarpoParseadoLeoPath.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(PolicarpoParseadoLeoPath))){
            String policarpoParseadoLeo = reader.readLine();
            System.out.println(policarpoParseado.equals(policarpoParseadoLeo));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}