package SolvingMethods.DirectMethods;

import Assay.MatrixException;
import Matrix.*;

abstract class Solver {

    Matrix matrix;
    LinearMatrix dopMatrix;
    LinearMatrix answers;

    Solver(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
    }

    abstract LinearMatrix solve() throws MatrixException;
}
