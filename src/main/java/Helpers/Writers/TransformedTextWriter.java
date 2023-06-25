package Helpers.Writers;

import Helpers.Matrices.Matrix;
import Helpers.Matrices.MatrixCipherProcessor;
import Helpers.Strings.TextTrasnformer;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class TransformedTextWriter {
    private String filename;

    public TransformedTextWriter(String filename) {
        this.filename = filename;
    }

    public void writeTransformedText(List<Matrix> matrixList, RealMatrix matrixCipheredNums, TextTrasnformer textTransformer, BigDecimal module26) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Matrix matrix : matrixList) {
                String transformedText = new MatrixCipherProcessor(matrix, textTransformer, module26).decodeCipheredNums(matrixCipheredNums);
                writer.write(transformedText);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

