public class Fibonacci {

    public static long fibNaive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    public static long fibMemo(int n, long[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        long result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo[n] = result;
        return result;
    }

    public static long fibOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        long prev2 = 0;
        long prev1 = 1;
        long current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
}


