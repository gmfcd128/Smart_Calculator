import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            if (number % 2 == 0) {
                deque.addFirst(number);
            } else {
                deque.addLast(number);
            }
        }
        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
        }

    }
}