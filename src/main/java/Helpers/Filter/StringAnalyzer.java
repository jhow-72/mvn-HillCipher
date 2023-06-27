package Helpers.Filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringAnalyzer {

    public void run(String plainText, String possibleText, String outputFile) {
        List<String> results = new ArrayList<>();

//         Compare substrings of both texts, considering 100 characters at a time
        for (int i = 0; i < plainText.length() - 100; i++) {
            String substring = plainText.substring(i, i + 100);
//            System.out.println(substring+" ------------ "+possibleText);
            if (substring.equals(possibleText)) {
                results.add(substring);
            }
        }

//        int chunkSize = 100;
//        int textLength = plainText.length();
//
//        // Compare substrings in chunks of 100 characters at a time
//        for (int i = 0; i < textLength - chunkSize + 1; i += chunkSize) {
//            String substring = plainText.substring(i, i + chunkSize);
//            System.out.println(substring+" ------------ "+possibleText);
//            if (substring.equals(possibleText)) {
//                results.add(substring);
//            }
//        }


        // Write the matching substrings to the output file
        try {
            File file = new File(outputFile);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            for (String result : results) {
                writer.write(result + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
