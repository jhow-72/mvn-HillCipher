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

    public void writeListTransformedText(List<Matrix> allPossibleDecoderMatrixList, RealMatrix matrixCipheredNums, TextTrasnformer textTransformer, BigDecimal module26) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Matrix decoderMatrix : allPossibleDecoderMatrixList) {
                String initial = decoderMatrix.getStringMatrix()+" -> "; // a matriz retornada aqui eh a chave publica

                // cria um processador de matrizes e ja chama o metodo que tenta usar a matriz decodificadora da iteracao para decodificar a matriz de numeros
                String transformedText = new MatrixCipherProcessor(decoderMatrix, textTransformer, module26, initial).decodeCipheredNums(matrixCipheredNums);

                writer.write(transformedText);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

