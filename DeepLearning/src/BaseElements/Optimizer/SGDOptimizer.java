package BaseElements.Optimizer;

public class SGDOptimizer extends Optimizer {
	private static final double e = 0.001;
	@Override
	final public double update(double value, double grad) {
		double delta = e * grad;
		return value - delta;
	}

}
