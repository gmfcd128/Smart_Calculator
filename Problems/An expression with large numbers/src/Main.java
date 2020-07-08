import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split(" ");
        BigInteger a = new BigInteger(values[0]);
        BigInteger b = new BigInteger(values[1]);
        BigInteger c = new BigInteger(values[2]);
        BigInteger d = new BigInteger(values[3]);
        BigInteger result = a.negate().multiply(b).add(c).subtract(d);
        System.out.println(result);
    }
}