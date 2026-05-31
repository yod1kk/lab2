import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Hashing {

    public static int hornerHash(String word) {
        int hash = 0;
        int P = 31;
        int M = 997;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            hash = (P * hash + letter) % M;
        }
        return hash;
    }

    public static String[] generateUniqueWords(int count) {
        Set<String> uniqueWords = new HashSet<>();
        while (uniqueWords.size() < count) {
            uniqueWords.add(generateUniqueWord());
        }
        return uniqueWords.toArray(new String[0]);
    }

    public static String generateUniqueWord() {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int length = 5 + random.nextInt(3);
        for (int i = 0; i < length; i++) {
            sb.append(abc.charAt(random.nextInt(abc.length())));
        }
        return sb.toString();
    }
}