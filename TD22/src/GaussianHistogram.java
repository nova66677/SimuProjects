import enstabretagne.base.math.MoreRandom;
import javax.swing.*;
import java.awt.*;

public class GaussianHistogram extends JPanel {
    private static final int NUM_SAMPLES = 10000; // More samples for accuracy
    private static final int NUM_BINS = 50; // Number of histogram bars
    private static final int WIDTH = 800; // Frame width
    private static final int HEIGHT = 500; // Frame height
    private static final double MEAN = 0; // Mean of normal distribution
    private static final double STD_DEV = 1; // Standard deviation

    private int[] bins;
    private int maxBinCount; // Maximum count for scaling histogram bars

    public GaussianHistogram() {
        bins = new int[NUM_BINS];
        generateGaussianValues();
    }

    private void generateGaussianValues() {
        MoreRandom mr = new MoreRandom();

        for (int i = 0; i < NUM_SAMPLES; i++) {
            double value = mr.nextGaussian() * STD_DEV + MEAN; // Keep mean = 0, std = 1

            // Map Gaussian range (-3σ to +3σ) into bins
            int binIndex = (int) ((value + 3) / 6 * NUM_BINS);

            // Ensure binIndex is within bounds
            if (binIndex >= 0 && binIndex < NUM_BINS) {
                bins[binIndex]++;
                maxBinCount = Math.max(maxBinCount, bins[binIndex]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        int binWidth = WIDTH / NUM_BINS;

        for (int i = 0; i < NUM_BINS; i++) {
            int binHeight = (int) ((double) bins[i] / maxBinCount * (HEIGHT - 50)); // Scale bar height

            int x = i * binWidth;
            int y = HEIGHT - binHeight - 30; // Adjust for bottom margin

            g2.fillRect(x, y, binWidth - 2, binHeight); // Draw histogram bars
        }

        // Draw Axes
        g2.setColor(Color.BLACK);
        g2.drawLine(0, HEIGHT - 30, WIDTH, HEIGHT - 30); // X-axis
        g2.drawLine(WIDTH / 2, 20, WIDTH / 2, HEIGHT - 30); // Y-axis
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gaussian Distribution Histogram");
            GaussianHistogram histogram = new GaussianHistogram();

            frame.add(histogram);
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
