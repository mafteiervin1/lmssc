package Lab1.crawl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import static Lab1.crawl.Constants.*;

public class TextProcessor {

    private static String allPunctuationChars = "[“;”!._:{}=+<>,()/'\"@?\\s]";
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String text) throws IOException {

        int i;
        for (String line : text.split("\\n")) {
            String formattedLine = line.replaceAll("\\s+", " ").replaceAll("\\n+", "\\n");
            if (!line.matches("[\\n\\r\\s]+")) {
                String [] wordsArray = formattedLine.split("\\s");
                for (i = 0; i< wordsArray.length; i++) {
                    String word1 = wordsArray[i].trim();
                    String word2 = i+1 <= wordsArray.length - 1
                        ? wordsArray[i+1].trim()
                        : "";
                    String word3 = i+2 <= wordsArray.length - 1
                        ? wordsArray[i+2].trim()
                        : "";
                    i = printWords(i, word1, word2, word3);
                }
            }
        }
        writer.close();
    }

    private static int printWords(int i, String word1, String word2, String word3) throws IOException {
        if (word1.isEmpty()) {
            return i;
        }
        if (isNameWithNoun(word1, word2) || areNames(word1, word2) || isPhonenumber(word1 + word2) || isPhonenumber(word2 + word3) || isPhonenumber(word1 + word3)) {
            writer.write(word1 + " " + word2);
            writer.newLine();
            i++;
            return i;
        }
        if (areNames(word1, word2, word3) || isPhonenumber(word1 + word2 + word3)) {
            writer.write(word1 + " " + word2 + " " + word3);
            writer.newLine();
            i = i + 2;
            return i;
        }
        if (word1.substring(word1.length() - 1).matches(allPunctuationChars)) {
            writer.write(word1.substring(0, word1.length() - 1));
            writer.newLine();
            writer.write(word1.substring(word1.length() - 1));
            writer.newLine();
        } else if (word1.substring(0, 1).matches(allPunctuationChars)) {
            writer.write(word1.substring(0, 1));
            writer.newLine();
            writer.write(word1.substring(1));
            writer.newLine();
        } else {
            writer.write(word1);
            writer.newLine();
        }
        return i;
    }

    private static boolean isNameWithNoun(String word1, String word2) {
        return ACADEMIC_TITLE_REGEX.matcher(word1).matches() && ITS_NAME_REGEX.matcher(word2).matches();
    }

    private static boolean areNames(String... words) {
        for (String word:words) {
            if(!ITS_NAME_REGEX.matcher(word).matches())
                return false;
        }
        return true;
    }

    private static boolean isPhonenumber(String word) {
        return PHONE_NUMBER_REGEX.matcher(word).matches();
    }

}
