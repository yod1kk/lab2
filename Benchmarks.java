import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class Benchmarks {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testFibonacci_Level1_Naive() {
        int n = 40;

        System.gc();
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Fibonacci.fibNaive(n);

        long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Витрати пам'яті (Рівень 1 - Рекурсія): " + Math.max(0, (memAfter - memBefore) / 1024) + " КБ");
    }

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testFibonacci_Level2_Memoization() {
        int n = 100000;

        System.gc();
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Fibonacci.fibMemo(n);

        long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Витрати пам'яті (Рівень 2 - Масив): " + Math.max(0, (memAfter - memBefore) / 1024) + " КБ");
    }

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testFibonacci_Level3_Iterative() {
        int n = 100000;

        System.gc();
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Fibonacci.fibOptimized(n);

        long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Витрати пам'яті (Рівень 3 - 2 змінні): " + Math.max(0, (memAfter - memBefore) / 1024) + " КБ");
    }

    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 3)
    @Test
    public void testHornerHash_Collisions() {
        int wordCount = 1000;
        int M = 997;

        String[] words = Hashing.generateUniqueWords(wordCount);
        int[] lockers = new int[M];

        for (String word : words) {
            int hashValue = Hashing.hornerHash(word);
            lockers[hashValue]++;
        }

        int collisions = 0;
        for (int count : lockers) {
            if (count > 1) {
                collisions += (count - 1);
            }
        }

        double collisionPercent = (collisions * 100.0) / wordCount;

        System.out.println("\n Результати Хешування ");
        System.out.println("Протестовано слів: " + wordCount);
        System.out.println("Кількість шафок (M): " + M);
        System.out.println("Знайдено колізій: " + collisions);
        System.out.println("Відсоток колізій: " + String.format("%.2f", collisionPercent) + "%");
    }
}