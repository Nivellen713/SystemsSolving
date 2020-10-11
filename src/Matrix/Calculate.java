package Matrix;

public class Calculate {

    public double determinant(Matrix matrix) throws MatrixException {
        int size = matrix.getHorizontalSize();
        if (size == 1) return matrix.getElement(0, 0);
        double det = 0;
        Matrix dopMatrix = new Matrix(size - 1, size - 1);
        int l = 1;
        for (int i = 0; i < size; ++i) {
            int x = 0, y = 0;
            for (int j = 1; j < size; ++j) {
                for (int k = 0; k < size; ++k) {
                    if (i == k) continue;
                    dopMatrix.setElement(x, y, matrix.getElement(j, k));
                    ++y;
                    if (y == size - 1) {
                        y = 0;
                        ++x;
                    }
                }
            }
            det += l * matrix.getElement(0, i) * determinant(dopMatrix);
            l *= (-1);
        }
        return det;
    }

    public void returnValues(Matrix to, Matrix from) {
        try {
            for (int i = 0; i < to.getHorizontalSize(); i++) {
                for (int j = 0; j < to.getVerticalSize(); j++) {
                    to.setElement(i, j, from.getElement(i, j));
                }
            }
        } catch (MatrixException ex){
            System.out.println(ex.getMessage());
        }
    }
}
