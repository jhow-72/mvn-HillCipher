package Helpers.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextEncripted {
    public String getEncriptedText(){
        String cipherTextAbsolutePath = "/Users/jhonatansantos/Documents/USP/S-7/Seguranca/EP/mvn_hill_cipher/src/main/java/Infos/Texto_conhecido/Cifrado/Hill/Grupo00_texto_cifrado.txt";
        return getCipherText(cipherTextAbsolutePath);
    }

    private String getCipherText(String cipherTextPath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(cipherTextPath))){
            return reader.readLine();
        } catch (IOException e){
            System.out.println("ocorreu um erro ao tentar ler o texto: "+e.getMessage());
        }
        return "";
    }
}
