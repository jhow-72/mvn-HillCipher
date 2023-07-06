import Helpers.Matrices.Matrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
    private Matrix instance;

    @Before
    public void setUp() {
        double[][] data = {
                {2, 1},
                {-1, 4}
        };

        instance = new Matrix(new Array2DRowRealMatrix(data), 26); // Instantiate your class here
    }

    @Test
    public void testSetDecodeMatrix() {
        // Define the expected result
        double[][] expectedDecodeMatrix = {{12, 23}, {3, 6}};
        int expectedInvMult = 3;

        // Assert the result
        Assert.assertEquals(expectedInvMult, instance.getInvMulplicativoModular());
        Assert.assertArrayEquals(expectedDecodeMatrix, instance.getDecodeMatrix().getData());
    }

}

