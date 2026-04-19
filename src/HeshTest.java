import java.util.Random;
public class HeshTest {
    public static void main(String[] args) {
        System.out.println("Тестування Хеш функції");
        System.out.println();
        String[] words = new String[1000];

        for  (int i = 0; i < 1000; i++){
            words[i] = generateRamdomWord();
        }
        int M = 997;
        int[] lockers = new int[M];

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            int hashValue = hornerHash (currentWord);
            lockers[hashValue] = lockers[hashValue] + 1;
        }
        int totalCollisions = 0;
        for (int i = 0; i < lockers.length; i++) {
                if (lockers[i] > 1) {
                    totalCollisions = totalCollisions + (lockers[i] - 1);
                }
    }
        System.out.println("Згенеровано слів: 1000");
        System.out.println("Розмір таблиці (кількість шафок M): " + M);
        System.out.println("Загальна кількість колізій: " + totalCollisions);
}

public static int hornerHash (String word) {
    int hash = 0;
    int P = 31;
    int M = 997;
    for(int i = 0; i < word.length(); i++){
        char letter = word.charAt(i);
        hash = (P * hash + letter) % M;
    }
    return hash;
    }
    public static String generateRamdomWord(){
        String ABC = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 5; i++){
            int randomIndex = random.nextInt(ABC.length());
            result = result + ABC.charAt(randomIndex);

        }
        return result;
    }

}
