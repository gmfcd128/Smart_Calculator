import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        if (n == 0) {
            return BigInteger.ONE;
        } else {
            BigInteger start = BigInteger.valueOf(n);
            for (int i = n - 2; i > 0; i -= 2) {
                start = start.multiply(BigInteger.valueOf(i));
            }
            return start;
        }
    }
}