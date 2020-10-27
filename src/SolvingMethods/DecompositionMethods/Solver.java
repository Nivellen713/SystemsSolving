package SolvingMethods.DecompositionMethods;

import Assay.MatrixException;
import Matrix.*;

abstract public class Solver {

    private Matrix matrixL;
    private Matrix matrixU;

    private Matrix matrix;
    private LinearMatrix dopMatrix;
    private LinearMatrix answers;

    public Solver(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
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

    public abstract void decomposition() throws MatrixException;
    public abstract LinearMatrix solve() throws MatrixException;
}
