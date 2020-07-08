import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        /* write your code here */
        String regex = "[A, B, E, K, M, H, O, P, C, T, Y, X]\\d{3}[A, B, E, K, M, H, O, P, C, T, Y, X]{2}";
        System.out.print(regNum.matches(regex));
    }
}