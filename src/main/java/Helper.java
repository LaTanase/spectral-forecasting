import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

public class Helper extends ApplicationFrame{

    public Helper(final String title, double[] S ) {

        super(title);
        final XYSeries series = new XYSeries("Similarity index");
        for(int i = 0; i < S.length; i++){
            series.add(i, S[i]);
        }
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Similarity Index Chart",
                "Steps",
                "Similarity index",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 250));
        chartPanel.setZoomAroundAnchor(true);
        setContentPane(chartPanel);
    }

    public static double[][] readMatrix(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        double[][] matrix = new double[Main.ROWS][Main.COLUMNS];
        while(scanner.hasNextLine()) {
            for (int i = 0; i < Main.ROWS; i++) {
                String[] line = scanner.nextLine().trim().split(" ");
                for (int j = 0; j < Main.COLUMNS; j++) {
                    matrix[i][j] = Double.parseDouble(line[j]);
                }
            }
        }
        return matrix;
    }

    public static double matrixMaxFinder(double[][] matrix){
        double max = matrix[0][0];
        for(int i = 0; i< Main.ROWS; i++)
            for(int j = 0; j< Main.COLUMNS; j++)
                if(matrix[i][j]>max)
                    max=matrix[i][j];
        return max;
    }

    public static double addMatrixElements(double[][] actualMatrix){
        double total = 0;
        for(int i = 0; i< Main.ROWS; i++)
            for(int j = 0; j< Main.COLUMNS; j++){
                total += actualMatrix[i][j] * actualMatrix[i][j];
            }
        return  total;
    }

    public static double addPredictedMatrixElements(Map<Integer, double[][]> predictedMatrix, int d){
        double total = 0;
        for(int i = 0; i< Main.ROWS; i++)
            for(int j = 0; j< Main.COLUMNS; j++){
                total += predictedMatrix.get(d)[i][j] * predictedMatrix.get(d)[i][j];
            }
        return total;
    }

    public static double computeSumOfMatricesElements(Map<Integer, double[][]> predictedMatrix, double[][] actualMatrix, int d){
        double sum = 0;
        for(int i = 0; i< Main.ROWS; i++)
            for(int j = 0; j< Main.COLUMNS; j++){
                sum += (predictedMatrix.get(d)[i][j] * actualMatrix[i][j]);
            }
        sum *= sum;
        return sum;
    }


}
