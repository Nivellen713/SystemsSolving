package Matrix;

import Assay.MatrixException;

public class LinearMatrix {
    private double[] array;

    public LinearMatrix(int amount) throws MatrixException {
        // Проверяем на отрацительные значения матрицы
        if (amount < 1) {
            throw new MatrixException();
        }
        array = new double[amount];
    }

    public int getSize() {
        return array.length;
    }

    public double getElement(int i) throws MatrixException {
        if (checkRange(i)) {
            return array[i];
        }
        throw new MatrixException();
    }
    public void setElement(int i, double value) throws MatrixException {
        if (checkRange(i)) array[i] = value;
        else throw new MatrixException();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nLinear Matrix : ");
            for (double value : array) {
                s.append(String.format("%.3g", value)).append(" ");
        }
        return s.toString();
    }

    private boolean checkRange(int i){
        return i >= 0 && i < array.length;
    }

}
