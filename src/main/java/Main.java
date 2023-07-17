import Helpers.Filter.StringAnalyzer;
import Helpers.Matrices.Matrix;
import Helpers.Matrices.MatrixGenerator;
import Helpers.Strings.TextEncripted;
import Helpers.Strings.TextTrasnformer;
import Helpers.Writers.TransformedTextWriter;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Main {
    static final  String PolicarpoPath = "src/main/java/Infos/Texto_conhecido/policarpo_quaresma.txt";
    static final String PolicarpoParseadoPath = "src/main/java/Outputs/PolicarpoParseado.txt";
    static final  String cipherText00Path = "src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt";
    static final  String cipherText08Path = "src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo08_texto_cifrado.txt";
    static final  String outputFile00Path = "src/main/java/Outputs/Output00.txt";
    static final  String outputFile08Path = "src/main/java/Outputs/Output08.txt";

    // rode apenas os referentes ao grupo 00 ou ao grupo 08, se rodar ambos juntos, n ira funcionar
    // o motivo disso eh porque o programa para ao encontrar a chave e o texto aberto
    public static void main(String[] args){
//        fazDecodificacao(cipherText00Path, outputFile00Path);
//        procuraTextoAberto(outputFile00Path);

        fazDecodificacao(cipherText08Path, outputFile08Path);
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
        BigInteger contadorChavesAdicionadas = BigInteger.ZERO;

//        caso nao tenha o arquivo do policarpoParseado, comente a chamada do lerPolicarpo e rode a linha abaixo q faz o parse
//        String policarpoParseado = new TextParser().parse(Main.PolicarpoPath);
        String policarpoParseado = lerPolicarpo();

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceStringsFilePath))) {
            String fullLine, key, alphaNumeric;
            HashMap<String, String> stringsEChaves = new HashMap<>();

            while ((fullLine = reader.readLine()) != null){
                String[] parts = fullLine.split(" -> ");
                key = parts[0];
                alphaNumeric = parts[1];
                stringsEChaves.put(alphaNumeric, key);

                contadorChavesAdicionadas = contadorChavesAdicionadas.add(BigInteger.ONE);
            }
            new StringAnalyzer().run(stringsEChaves, policarpoParseado);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("chaves tentadas: " + contadorChavesAdicionadas);
    }

    private static String lerPolicarpo(){
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(Main.PolicarpoParseadoPath))) {
            content.append(reader.readLine());
        } catch (Exception e){
            System.out.println("erro ao ler o policarpo parseado");
            e.printStackTrace();
        }
        return content.toString();
    }
}