package components;

import java.util.Arrays;

public class Filter {

    private static final int SHORT_SHIFT = 32768;

    private final int timeToSimulate;
    private final short[] coefficients;
    private final int[] quantized_coefficients;
    private final short[] quantized_samples;
    private final short[] filtering_results;
    private final float[] samples;
    private final float[] filteringResultsConverted;

    public Filter(int filterOrder, int timeToSimulate) {
        this.coefficients = Coefs.getCoefficients();
        this.timeToSimulate = timeToSimulate;
        this.quantized_coefficients = new int[filterOrder + 1];
        this.samples = new float[timeToSimulate];
        this.quantized_samples = new short[timeToSimulate];
        this.filteringResultsConverted = new float[coefficients.length+samples.length];
        this.filtering_results = new short[filteringResultsConverted.length];}

    public void  convolve() {
        for (int i=0; i<quantized_samples.length; i++){
            for (int j = 0; j<quantized_coefficients.length; j++){
                filtering_results[i+j] += quantized_samples[i] * quantized_coefficients[j];
            }
        }
    }

    public void quantizeCoefficients() {
        for (int i = 0; i < coefficients.length; i++)
            quantized_coefficients[i] = coefficients[i];
    }

    public void quantizeSamples() {
        for (int i = 0; i < timeToSimulate; i++)
            quantized_samples[i] = (short) (samples[i] * SHORT_SHIFT);
    }

    public void convertResults() {
        for (int i = 0; i < filtering_results.length; i++)
            filteringResultsConverted[i] =  filtering_results[i] / 32768.0f;
    }

    public void simulateImp(float strength) {
        samples[0] = strength;
        for (int i = 1; i < samples.length; i++)
            samples[i] = 0;
    }

    public void simulateStep(float strength) {
        for (int i = 0; i < timeToSimulate; i++)
            samples[i] = strength;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (float v : filteringResultsConverted) {
            result.append(v);
            result.append("\n");
        }
        return result.toString();
    }
}