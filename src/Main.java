import components.Filter;

public class Main {
    public static void main(String[] args) {
        Filter filterWithStep = filterWithStep(0.99997f);
        Filter filterWithImp = filterWithImpulse(0.7f);
        System.out.println("Step reaction");
        System.out.println(filterWithStep);
        System.out.println("Impulse reaction");
        System.out.println(filterWithImp);
    }

    private static Filter filterWithStep(float amplitude){
        Filter filter = new Filter(15, 30);
        filter.simulateStep(amplitude);
        filter.quantizeSamples();
        filter.quantizeCoefficients();
        filter.convolve();
        filter.convertResults();
        return filter;
    }

    private static Filter filterWithImpulse(float amplitude){
        Filter filter = new Filter(15, 30);
        filter.simulateImp(amplitude);
        filter.quantizeSamples();
        filter.quantizeCoefficients();
        filter.convolve();
        filter.convertResults();
        return filter;
    }

}