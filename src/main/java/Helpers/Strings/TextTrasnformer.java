package Helpers.Strings;

import Helpers.Dictionaries.Dictionarie;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;

public class TextTrasnformer {
    Dictionarie dictionarie;
    public TextTrasnformer(){
        this.dictionarie = new Dictionarie();
    }

    //Transforma uma RealMatrix contendo valores numéricos em uma string de caracteres
    public String transformNumToChars(RealMatrix matrix){
        Map<Integer, Character> dec2alf = dictionarie.getDecriptDict();
        String output = "";

        int numRows = matrix.getRowDimension();
        int numCols = matrix.getColumnDimension();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                double value = matrix.getEntry(i, j);
                if(value<0){
                    value += 26;
                }
                output+=dec2alf.get(Double.valueOf(value).intValue()); // Converte o valor numérico em um caractere e concatena na string output
            }
        }

        return output;
    }

    public void iterateMatrix(RealMatrix matrix) {
        int numRows = matrix.getRowDimension();
        int numCols = matrix.getColumnDimension();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                double value = matrix.getEntry(i, j);
                // Process the value as needed
                System.out.print(value + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }


    public RealMatrix transformCharsToNums(String input) {
        Map<Character, Integer> enc2alf = this.dictionarie.getEncriptDict();
        int numCols = input.length() / 2; // Divide por 2 para acomodar em pares
        double[][] matrixData = new double[numCols][2]; // matriz que vai dentro da principal e contem os indexes em pares

        // aqui eh onde sera gerada a matriz de dados
        for (int i = 0; i < numCols; i++) {
            char char1 = input.charAt(i * 2);     // multiplica por 2 para acessar o numero par certo (0, 2, 4, 6, ...)
            char char2 = input.charAt(i * 2 + 1); // mesmo de cima, porem, primos, (1, 3, 5, 7, ...)

            matrixData[i][0] = enc2alf.get(char1);
            matrixData[i][1] = enc2alf.get(char2);
        }

        return new Array2DRowRealMatrix(matrixData);
    }

}
