import Matrix.*;
import SolvingMethods.CramerMethod;
import SolvingMethods.GaussMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIforInput extends JFrame {
    private JButton cramerButton;
    private JButton gaussButton;
    private JButton inputValuesButton;
    private JButton inputSizeButton;

    private Matrix matrix;
    private LinearMatrix dopMatrix;
    private LinearMatrix answers;
    private int size;

    private JLabel outputAnswerLabel;

    private JTextField a00, a01, a10, a11;
    private JTextField b0, b1;
    private JTextField matrixSize;

    eHandler handler = new eHandler();

    public GUIforInput(String title) throws MatrixException {
        super(title);

        setLayout(new FlowLayout());
        cramerButton = new JButton("Cramer");
        gaussButton = new JButton("Gauss");
        inputValuesButton = new JButton("Input values");
        inputSizeButton = new JButton("Input size");

        outputAnswerLabel = new JLabel("");

        a00 = new JTextField(5);
        a01 = new JTextField(5);
        a10 = new JTextField(5);
        a11 = new JTextField(5);

        b0 = new JTextField(5);
        b1 = new JTextField(5);

        matrixSize = new JTextField(5);

        add(cramerButton);
        add(gaussButton);

        add(matrixSize);

        add(a00);
        add(a01);
        add(a10);
        add(a11);

        add(b0);
        add(b1);

        add(inputValuesButton);
        add(inputSizeButton);
        add(outputAnswerLabel);

        inputValuesButton.addActionListener(handler);
        cramerButton.addActionListener(handler);
        gaussButton.addActionListener(handler);
        inputSizeButton.addActionListener(handler);

    }

    public class eHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(inputValuesButton)) {
                try {
                    matrix.setElement(0, 0, Double.parseDouble(a00.getText()));
                    matrix.setElement(0, 1, Double.parseDouble(a01.getText()));
                    matrix.setElement(1, 0, Double.parseDouble(a10.getText()));
                    matrix.setElement(1, 1, Double.parseDouble(a11.getText()));

                    dopMatrix.setElement(0, Double.parseDouble(b0.getText()));
                    dopMatrix.setElement(1, Double.parseDouble(b1.getText()));

                    outputAnswerLabel.setText("");
                } catch (MatrixException ex) {
                    ex.printStackTrace();
                }
            }

            if (e.getSource().equals(cramerButton)) {
                try {
                    new CramerMethod(matrix, dopMatrix, answers).solving();
                    outputAnswerLabel.setText(answers.toString());
                } catch (MatrixException ex) {
                    ex.printStackTrace();
                }
            }

            if (e.getSource().equals(gaussButton)) {
                try {
                    new GaussMethod(matrix, dopMatrix, answers).solving();
                    outputAnswerLabel.setText(answers.toString());
                } catch (MatrixException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource().equals(inputSizeButton)) {
                size = Integer.parseInt(matrixSize.getText());
                try {
                    matrix = new Matrix(size, size);
                    dopMatrix = new LinearMatrix(size);
                    answers = new LinearMatrix(size);
                } catch (MatrixException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}
