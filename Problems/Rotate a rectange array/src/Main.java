import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int row = Integer.parseInt(input[0]);
        int column = Integer.parseInt(input[1]);
        String[][] newArray = new String[column][row];
        for (int i = 0; i < row; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            for (int j = 0; j < numbers.length; j++) {
                newArray[j][row - i - 1] = numbers[j];
            }
        }

        for (String[] r : newArray) {
            for (String item : r) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
