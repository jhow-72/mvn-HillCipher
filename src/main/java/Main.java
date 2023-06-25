import Helpers.Dictionaries.Dictionarie;
import Helpers.Matrices.Matrix;
import Helpers.Strings.TextEncripted;
import Helpers.Strings.TextTrasnformer;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Main {

    public static void main(String[] args){
        String cipherText = new TextEncripted().getEncriptedText();

        TextTrasnformer textTrasnformer = new TextTrasnformer();
        RealMatrix matrix = textTrasnformer.transformCharsToNums(cipherText);

        String transformedText = textTrasnformer.transformNumToChars(matrix);

    }
}