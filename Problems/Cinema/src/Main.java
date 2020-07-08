import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] matrixSize = scanner.nextLine().split(" ");
        int row = Integer.parseInt(matrixSize[0]);
        int column = Integer.parseInt(matrixSize[1]);
        String[][] matrix = new String[row][column];
        for (int i = 0; i < row; i++) {
            String[] input = scanner.nextLine().split(" ");
            for (int j = 0; j < column; j++) {
                System.arraycopy(input, 0, matrix[i], 0, column);
            }
        }

        int seat = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < row; i++) {
            int count = 0;
            for (int j = 0; j < column; j++) {
                if ("0".equals(matrix[i][j])) {
                    count += 1;
                    if (count >= seat) {
                        System.out.print(i + 1);
                        return;
                    }
                } else {
                    count = 0;
                }
            }
        }

        System.out.print(0);
    }
}