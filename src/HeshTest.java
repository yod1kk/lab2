import java.util.Random;

public class HeshTest {
    public static void main(String[] args) {
        System.out.println("Тестування Хеш функції");
        System.out.println();

        int wordCount = 10000;
        String[] words = new String[wordCount];

        for (int i = 0; i < wordCount; i++) {
            words[i] = generateRandomWord();
        }

        int M = 997;
        int[] lockers = new int[M];

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            int hashValue = hornerHash(currentWord);
            lockers[hashValue] = lockers[hashValue] + 1;
        }

        int totalCollisions = 0;
        for (int i = 0; i < lockers.length; i++) {
            if (lockers[i] > 1) {
                totalCollisions = totalCollisions + (lockers[i] - 1);
            }
        }

        double collisionPercent = (totalCollisions * 100.0) / wordCount;

        System.out.println("Згенеровано слів: " + wordCount);
        System.out.println("Розмір таблиці (кількість шафок M): " + M);
        System.out.println("Загальна кількість колізій: " + totalCollisions);
        System.out.println("Відсоток колізій: " + String.format("%.2f", collisionPercent) + "%");
    }

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

    public static String generateRandomWord() {
        String ABC = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(ABC.charAt(random.nextInt(ABC.length())));
        }
        return sb.toString();
    }
}