package Matrix;

import java.util.Scanner;

public class MatrixCreator {
    public void fillRandomized (Matrix t, int start, int end) {
        int v = t.getVerticalSize();
        int h = t.getHorizontalSize();
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < h; j++) {
                try {
                    int value = (int)(Math.random() * (end - start) + start);
                    t.setElement(i, j, value);
                } catch (MatrixException e){
                    e.getMessage();
                }
            }
        }
    }

    public void personalVariables (Matrix matrix, LinearMatrix dopMatrix) throws MatrixException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество неизвестных: ");
        int amountOfUnknowns = scanner.nextInt();
        for (int j = 0; j < matrix.getVerticalSize(); j++) {
            System.out.println("Введите " + (j + 1) + " строку: ");
            for (int i = 0; i < matrix.getHorizontalSize(); i++){
                double value = scanner.nextDouble();
                matrix.setElement(i, j, value);
            }
        }
        System.out.println("Введите свободные члены: ");
        for (int i = 0; i < dopMatrix.getSize(); i++){
            double value = scanner.nextDouble();
            dopMatrix.setElement(i, value);
        }
    }
}
