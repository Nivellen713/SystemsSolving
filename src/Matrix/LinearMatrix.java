package Matrix;

public class LinearMatrix {
    private double[] array;

    public LinearMatrix(int amount) throws MatrixException {
        // Проверяем на отрацительные значения матрицы
        if (amount < 1) {
            throw new MatrixException();
        }
        array = new double[amount];
    }

    public double getElement(int i) throws MatrixException {
        if (checkRange(i)) {
            return array[i];
        }
        throw new MatrixException();
    }
    public void setElement(int i, double value) throws MatrixException {
        if (checkRange(i)) {
            array[i] = value;
        }
        throw new MatrixException();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nFreeMembers : " + array.length);
            for (double value : array) {
                s.append(value + " ");
        }
        return s.toString();
    }

    private boolean checkRange(int i){
        if (i >= 0 && i < array.length){
            return true;
        } else {
            return false;
        }
    }

}
