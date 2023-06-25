package Helpers.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextEncripted {
    public String getTextContent(String cipherTextPath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(cipherTextPath))){
            return reader.readLine();
        } catch (IOException e){
            System.out.println("ocorreu um erro ao tentar ler o texto: "+e.getMessage());
        }
        return "";
    }
}
