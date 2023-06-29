package Helpers.Filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class StringAnalyzer {
    static final  String OutputPath = "J:\\jhow_\\Documents\\USP\\Semestres\\Semestre_7\\Seguranca\\EP\\mvn-HillCipher\\src\\main\\java\\Outputs\\OutputStringAnalyzer.txt";

    public void run(String possibleText, String key, String policarpoParseado) {
        BigInteger contadorIteraçoesPorChave = BigInteger.ZERO;

//         Compare substrings of both texts, considering 100 characters at a time
        for (int i = 0; i < policarpoParseado.length() - 100; i++) {
            contadorIteraçoesPorChave =contadorIteraçoesPorChave.add(BigInteger.ONE);
            String substring = policarpoParseado.substring(i, i + 100);
//            System.out.println(substring+" ------------ "+possibleText);
            if (substring.equals(possibleText)) {
                System.out.println("achou!");
                System.out.println("total de iteraçoes pela chave correta: " + contadorIteraçoesPorChave);
                String result = key+" -> "+possibleText;
                gravaNoArquivo(result, StringAnalyzer.OutputPath);

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
