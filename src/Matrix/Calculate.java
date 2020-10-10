package Matrix;

public class Calculate {

    double determinant(Matrix matrix) throws MatrixException {
        int size = matrix.getHorizontalSize();
        if(size == 1) return matrix.getElement(0,0);
        double det = 0;
        Matrix dopMatrix = new Matrix(size-1, size-1);
        int l = 1;
        for(int i = 0; i < size; ++i){
            int x = 0, y = 0;
            for(int j = 1; j < size; ++j) {
                for(int k = 0; k < size; ++k) {
                    if(i == k) continue;
                    dopMatrix.setElement(x, y, matrix.getElement(j,k));
                    ++y;
                    if(y == size - 1) {
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
}
