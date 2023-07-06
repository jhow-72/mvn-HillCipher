package Helpers.Filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class StringAnalyzer {
    static final  String OutputPath = "src/main/java/Outputs/OutputStringAnalyzer.txt";

    public void run(HashMap<String, String> stringsEChaves, String policarpoParseado) {
        BigInteger contadorIteracoesPorChave = BigInteger.ZERO;

//         Compare substrings of both texts, considering 100 characters at a time
        for (int i = 0; i < policarpoParseado.length() - 100; i++) {
            contadorIteracoesPorChave =contadorIteracoesPorChave.add(BigInteger.ONE);
            String substring = policarpoParseado.substring(i, i + 100);
//            System.out.println(substring+" ------------ "+possibleText);
            if (stringsEChaves.containsKey(substring)) {
                System.out.println("achou!");
                System.out.println("total de iteraçoes pela chave correta: " + contadorIteracoesPorChave);
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
