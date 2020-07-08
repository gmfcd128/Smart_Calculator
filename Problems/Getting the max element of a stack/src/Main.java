import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> trackStack = new ArrayDeque<>();
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].equals("push")) {
                int number = Integer.parseInt(command[1]);
                stack.push(number);
                if (trackStack.size() == 0 || number > trackStack.peek()) {
                    trackStack.push(number);
                } else if (number <= trackStack.peek()) {
                    trackStack.push(trackStack.peek());
                }
            } else if (command[0].equals("pop") && stack.size() > 0) {
                stack.pop();
                trackStack.pop();
            } else if (command[0].equals("max")) {
                System.out.println(trackStack.peek());
            }
        }
    }
}