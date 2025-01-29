import enstabretagne.base.math.MoreRandom;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.util.Arrays;

public class ChiSquareGaussianTest {
    private static final int NUM_SAMPLES = 10000; // Taille de l'échantillon
    private static final int NUM_BINS = 10; // Nombre de classes (bins)
    private static final double MEAN = 0; // Moyenne μ
    private static final double STD_DEV = 1; // Écart-type σ
    private static final double MIN_X = -3; // Minimum des valeurs considérées (-3σ)
    private static final double MAX_X = 3; // Maximum des valeurs considérées (+3σ)

    public static void main(String[] args) {
        MoreRandom mr = new MoreRandom();
        double[] samples = new double[NUM_SAMPLES];

        // Génération des échantillons suivant une loi normale
        for (int i = 0; i < NUM_SAMPLES; i++) {
            samples[i] = mr.nextGaussian();
        }

        // Définition des bornes des bins
        double binWidth = (MAX_X - MIN_X) / NUM_BINS;
        long[] observedFrequencies = new long[NUM_BINS];
        double[] expectedFrequencies = new double[NUM_BINS];

        // Remplissage des fréquences observées
        for (double sample : samples) {
            int binIndex = (int) ((sample - MIN_X) / binWidth);
            if (binIndex >= 0 && binIndex < NUM_BINS) {
                observedFrequencies[binIndex]++;
            }
        }

        // Calcul des fréquences attendues en utilisant la distribution normale théorique
        NormalDistribution normalDist = new NormalDistribution(MEAN, STD_DEV);
        for (int i = 0; i < NUM_BINS; i++) {
            double binStart = MIN_X + i * binWidth;
            double binEnd = binStart + binWidth;
            double probability = normalDist.probability(binStart, binEnd);
            expectedFrequencies[i] = probability * NUM_SAMPLES;
        }

        // Application du test du Khi²
        ChiSquareTest chiSquareTest = new ChiSquareTest();
        double chiSquareValue = chiSquareTest.chiSquareTest(expectedFrequencies, observedFrequencies);

        System.out.println("Statistique de test Khi² : " + chiSquareValue);
        System.out.println("Si p-value > 0.05, alors la distribution est gaussienne.");

        // Vérification avec un seuil de 5%
        double pValue = chiSquareTest.chiSquareTest(expectedFrequencies, observedFrequencies);
        System.out.println("p-value : " + pValue);
        if (pValue > 0.05) {
            System.out.println("✅ L'hypothèse d'une distribution gaussienne est acceptée.");
        } else {
            System.out.println("❌ L'hypothèse est rejetée. La distribution n'est pas normale.");
        }
    }
}
