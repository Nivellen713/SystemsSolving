package SolvingMethods;

import Assay.MatrixException;
import Matrix.*;

abstract public class Solver {

    Matrix matrix;
    LinearMatrix dopMatrix;
    LinearMatrix answers;

    Solver(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
    }

    public abstract LinearMatrix solve() throws MatrixException;
}
