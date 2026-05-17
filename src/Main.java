public class Main {
    public static void main(String[] args) {
        System.out.println("ТЕСТУВАННЯ АЛГОРИТМІВ ФІБОНАЧЧІ");
        System.out.println();

        testNaive();
        testOptimizedVsArray();
    }

    public static void testNaive() {
        System.out.println("Перший спосіб (Звичайна рекурсія)");
        System.out.println();

        int[] smallSizes = {10, 20, 30, 35, 40};

        for (int i = 0; i < smallSizes.length; i++) {
            int n = smallSizes[i];

            long start = System.nanoTime();
            Fibonacci.fibNaive(n);
            long end = System.nanoTime();

            long time = end - start;

            System.out.println("N = " + n + " | Час: " + time + " наносекунд");
        }
    }

    public static void testOptimizedVsArray() {
        System.out.println();
        System.out.println("Другий і третій спосіб (Масив проти 2 змінних)");

        int[] sizes = {2000, 4000, 6000};

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            System.out.println();
            System.out.println("Тестуємо число N = " + n);

            System.gc();
            long memoryBefore1 = runtime.totalMemory() - runtime.freeMemory();

            long start1 = System.nanoTime();
            long[] memo = new long[n + 1];
            Fibonacci.fibMemo(n, memo);
            long end1 = System.nanoTime();

            long memoryAfter1 = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed1 = (memoryAfter1 - memoryBefore1) / 1024;
            long time1 = (end1 - start1) / 1000;

            System.out.println("Спосіб з масивом:       Час = " + time1 + " мкс, Пам'ять = " + memoryUsed1 + " КБ");

            System.gc();
            long memoryBefore2 = runtime.totalMemory() - runtime.freeMemory();

            long start2 = System.nanoTime();
            Fibonacci.fibOptimized(n);
            long end2 = System.nanoTime();

            long memoryAfter2 = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed2 = (memoryAfter2 - memoryBefore2) / 1024;
            if (memoryUsed2 < 0) {
                memoryUsed2 = 0;
            }
            long time2 = (end2 - start2) / 1000;

            System.out.println("Спосіб з двома змінними: Час = " + time2 + " мкс, Пам'ять = " + memoryUsed2 + " КБ");
        }
    }
}