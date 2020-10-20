import Assay.MatrixException;
import Matrix.*;
import SolvingMethods.*;

public class Test {
    public static void main(String[] args) {
        int n = 4;
        try {
            Matrix matrix = new Matrix(n, n);
            LinearMatrix dopMatrix = new LinearMatrix(n);
            LinearMatrix answers = new LinearMatrix(n);

            matrix.setElement(0, 0, 1);
            matrix.setElement(0, 1, -1);
            matrix.setElement(0, 2, 3);
            matrix.setElement(0, 3, 1);

            matrix.setElement(1, 0, 4);
            matrix.setElement(1, 1, -1);
            matrix.setElement(1, 2, 5);
            matrix.setElement(1, 3, 4);

            matrix.setElement(2, 0, 2);
            matrix.setElement(2, 1, -2);
            matrix.setElement(2, 2, 4);
            matrix.setElement(2, 3, 1);

            matrix.setElement(3, 0, 1);
            matrix.setElement(3, 1, -4);
            matrix.setElement(3, 2, 5);
            matrix.setElement(3, 3, -1);

            dopMatrix.setElement(0, 5);
            dopMatrix.setElement(1, 4);
            dopMatrix.setElement(2, 6);
            dopMatrix.setElement(3, 3);

            System.out.print("Your matrix: " + matrix);
            System.out.println("Dop matrix: " + dopMatrix);

            answers = new LowerUpper(matrix, dopMatrix, answers).solve();
            System.out.println("\nTest solution: " + answers);

            System.out.println("\nCorrect solution: " + "9,00 18,0 10,0 -16,0");

        } catch (MatrixException ex) {
            System.err.println("Error of creating matrix " + ex);
        }
    }
}
