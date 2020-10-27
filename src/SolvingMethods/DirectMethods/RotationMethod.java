package SolvingMethods.DirectMethods;

import Assay.MatrixException;
import Matrix.LinearMatrix;
import Matrix.Matrix;

public class RotationMethod extends Solver {
    public RotationMethod(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        super(matrix, dopMatrix, answers);
    }

    private double coeffC;
    private double coeffS;

    @Override
    public LinearMatrix solve() throws MatrixException {
        return null;
    }
}
