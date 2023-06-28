package Helpers.Filter;

import Helpers.Strings.TextParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringAnalyzer {
    static final  String PolicarpoPath = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/policarpo_quaresma.txt";
    static final  String OutputPath = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Outputs/output_string_analyzer.txt";

    public void run(String possibleText, String key) {
        String policarpoParseado = new TextParser().parse(StringAnalyzer.PolicarpoPath);

//         Compare substrings of both texts, considering 100 characters at a time
        for (int i = 0; i < policarpoParseado.length() - 100; i++) {
            String substring = policarpoParseado.substring(i, i + 100);
//            System.out.println(substring+" ------------ "+possibleText);
            if (substring.equals(possibleText)) {
                System.out.println("achou!");

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
