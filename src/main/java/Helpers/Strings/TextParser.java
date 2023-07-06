package Helpers.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class TextParser {
    public String parse(String fileName) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String parsedContent = content.toString().toLowerCase();
        parsedContent = removeAccents(parsedContent);
        parsedContent = removeNonLetters(parsedContent);

        return parsedContent;
    }

    public String removeAccents(String text) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedText).replaceAll("");
    }

    public String removeNonLetters(String text) {
        return text.replaceAll("[^a-zA-Z]", "");
    }
}

