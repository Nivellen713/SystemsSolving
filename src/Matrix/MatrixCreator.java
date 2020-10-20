package Matrix;

import Assay.MatrixException;

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

    public void fillRandomized (LinearMatrix t, int start, int end) {
        for (int i = 0; i < t.getSize(); i++){
            try {
                double value = (int)(Math.random() * (end - start) + start);
                t.setElement(i, value);
            } catch (MatrixException e){
                e.getMessage();
            }
        }
    }

    public void personalVariables (Matrix matrix, LinearMatrix dopMatrix) throws MatrixException {
        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < matrix.getVerticalSize(); j++) {
            System.out.println("Enter " + (j + 1) + " line: ");
            String str = scanner.nextLine();
            String[] values = str.split("%s");
            for (int i = 0; i < matrix.getHorizontalSize(); i++){
                double value = Double.parseDouble(values[i]);
                matrix.setElement(i, j, value);
            }
        }
        System.out.println("Enter free members: ");
        for (int i = 0; i < dopMatrix.getSize(); i++){
            double value = scanner.nextDouble();
            dopMatrix.setElement(i, value);
        }
    }
    
        public void personalVariables (Matrix matrix)throws MatrixException {
        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < matrix.getVerticalSize(); j++) {
            System.out.println("Enter " + (j + 1) + " line: ");
            String str = scanner.nextLine();
            String[] values = str.split(" ");
            for (int i = 0; i < matrix.getHorizontalSize(); i++){
                double value = Double.parseDouble(values[i]);
                matrix.setElement(i, j, value);
            }
        }
    }
    
}
