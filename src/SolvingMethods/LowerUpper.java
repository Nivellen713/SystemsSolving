package SolvingMethods;

import Matrix.*;

public class LowerUpper {

    private Matrix matrix;
    private LinearMatrix dopMatrix;
    private LinearMatrix answers;
    private Matrix lMatrix;
    private Matrix uMatrix;


    public LowerUpper(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        this.matrix = matrix;
        this.uMatrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
        try {
            lMatrix = new Matrix(answers.getSize(), answers.getSize());
        } catch (MatrixException ex){
            ex.getMessage();
        }
    }

    public LinearMatrix solving() throws MatrixException {

        for (int i = 0; i < matrix.getVerticalSize(); i++){
            for (int j = i; j < matrix.getVerticalSize(); j++){
                double a = uMatrix.getElement(j,i);
                double b = uMatrix.getElement(i,i);
                lMatrix.setElement(j, i, a/b);
            }
        }
        for (int k = 1; k < matrix.getVerticalSize(); k++) {
            for (int i = k - 1; i < matrix.getVerticalSize(); i++) {
                for (int j = i; j < matrix.getVerticalSize(); j++) {
                    double a = uMatrix.getElement(j,i);
                    double b = uMatrix.getElement(i,i);
                    lMatrix.setElement(j, i, a/b);
                }
            }
            for (int i = k; i < matrix.getVerticalSize(); i++) {
                for (int j = k - 1; j < matrix.getVerticalSize(); j++) {
                    double a = uMatrix.getElement(i, j);
                    double b = lMatrix.getElement(i, k - 1);
                    double c = uMatrix.getElement(k - 1, j);
                    double d = a - b*c;
                    lMatrix.setElement(j, i, d);
                }
            }
        }
        return answers;
    }


}
