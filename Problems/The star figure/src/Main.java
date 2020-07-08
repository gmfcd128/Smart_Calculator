import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int matrixSize = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[matrixSize][matrixSize];
        for (char[] row : matrix) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < matrixSize / 2; i++) {
            matrix[i][i] = '*';
            matrix[i][matrixSize / 2] = '*';
            matrix[i][matrixSize - i - 1] = '*';
        }
        for (int j = 0; j < matrixSize; j++) {
            matrix[matrixSize / 2][j] = '*';
        }
        for (int k = matrixSize - 1; k > matrixSize / 2; k--) {
            matrix[k][k] = '*';
            matrix[k][matrixSize / 2] = '*';
            matrix[k][matrixSize - k - 1] = '*';
        }

        for (char[] row : matrix) {
            for (char item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}