import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final int ROWS =10;
    public static final int COLUMNS =20;
    public static final int STEPS=101;
    
    public static void main(String[] args) throws FileNotFoundException {

        double[][] matrixA =  Helper.readMatrix("src/main/resources/matrixA.txt");
        double[][] matrixB =  Helper.readMatrix("src/main/resources/matrixB.txt");
        double[][] matrixP =  Helper.readMatrix("src/main/resources/matrixP.txt");
        Map<Integer, double[][]> predictedMatrix = new HashMap<Integer, double[][]>();

        double[] similarityIndex = new double [STEPS];
        double MaxA = Helper.matrixMaxFinder(matrixA);
        double MaxB = Helper.matrixMaxFinder(matrixB);
        for(int d=0;d<STEPS;d++) {
            double[][] predictedMatrixForStep = new double[ROWS][COLUMNS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                     predictedMatrixForStep[i][j] = ((d / MaxA) * matrixA[i][j]) + (((STEPS - 1 - d) / MaxB) * matrixB[i][j]);
                }
            }
            predictedMatrix.put(d, predictedMatrixForStep);
        }

        for(int d=0;d<STEPS;d++){
            similarityIndex[d]= Helper.computeSumOfMatricesElements(predictedMatrix,matrixP,d)/(Helper.addPredictedMatrixElements(predictedMatrix,d)* Helper.addMatrixElements(matrixP));
            System.out.println(similarityIndex[d]);
        }

        final Helper demo = new Helper("Similarity index", similarityIndex);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }


}

