package SolvingMethods.DecompositionMethods;

import Assay.MatrixException;
import Matrix.*;

abstract class Solver {

    Matrix matrixL;
    Matrix matrixU;

    Matrix matrix;
    LinearMatrix dopMatrix;
    LinearMatrix answers;

    Solver(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
        try {
            matrixL = new Matrix(answers.getSize(), answers.getSize());
            matrixU = new Matrix(answers.getSize(), answers.getSize());
        } catch (MatrixException ex) {
            ex.getMessage();
        }
    }

    abstract void decomposition() throws MatrixException;
    abstract LinearMatrix solve() throws MatrixException;
}
