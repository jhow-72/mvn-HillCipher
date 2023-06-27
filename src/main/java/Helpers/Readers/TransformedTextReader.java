package Helpers.Readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransformedTextReader {
    private static List<String> ReadText(String filePath, byte mode) {
        List<String> lineList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(mode==0) {
                    lineList.add(line);
                }
                else{
                    String arrowSpace = "-> ";
                    int indexArrowSpace = line.indexOf("-> ");
                    String onlyTheTextPart = line.substring(indexArrowSpace + arrowSpace.length()).trim();
                    lineList.add(onlyTheTextPart);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }

    public List<String> getAllFullLines(String filePath){
        return ReadText(filePath, (byte)0);
    }

    public List<String> getAllLineStrings(String filePath){
        return ReadText(filePath, (byte)1);
    }
}
