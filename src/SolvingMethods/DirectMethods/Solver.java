package SolvingMethods.DirectMethods;

import Assay.MatrixException;
import Matrix.*;

abstract public class Solver {

    public Matrix matrix;
    public LinearMatrix dopMatrix;
    public LinearMatrix answers;

    public Solver(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
    }

    public abstract LinearMatrix solve() throws MatrixException;
}
