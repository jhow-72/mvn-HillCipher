package Helpers.Filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class StringAnalyzer {
    static final  String OutputPath = "src/main/java/Outputs/OutputStringAnalyzer.txt";

    public void run(HashMap<String, String> stringsEChaves, String policarpoParseado) {
        BigInteger contadorIteracoes = BigInteger.ZERO;

        // compara as substrings do arquivo com o livro 100 chars por vez
        for (int i = 0; i < policarpoParseado.length() - 100; i++) {
            contadorIteracoes = contadorIteracoes.add(BigInteger.ONE);
            String substring = policarpoParseado.substring(i, i + 100);
            if (stringsEChaves.containsKey(substring)) {
                System.out.println("achou!");
                System.out.println("total de iteraçoes ateh a chave correta: " + contadorIteracoes);
                String result = stringsEChaves.get(substring)+" -> "+substring;
                gravaNoArquivo(result, StringAnalyzer.OutputPath);
                System.out.println(result);
                System.exit(0);
            }
        }
    }

    private void gravaNoArquivo(String result, String outPutFile) {
        try {
            File file = new File(outPutFile);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(result + "\n");
            writer.close();
        } catch (IOException e){
            System.out.println("Erro ao escrever no arquivo. " + e.getMessage());
        }
    }
}
