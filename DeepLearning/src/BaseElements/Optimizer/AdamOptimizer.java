package BaseElements.Optimizer;

public class AdamOptimizer extends Optimizer{
	private static final double e = 0.001;
	private static final double beta1 = 0.9;
	private static final double beta2 = 0.999;
	private static final double d = 0.00000001;
	
	private double s = 0;
	private double r = 0;
	private int t = 0;
	
	@Override
	final public double update(double value, double grad) {
		t++;
		s = beta1 * s + (1 - beta1) * grad;
		r = beta2 * r + (1 - beta2) * grad * grad;
		double _s = s / (1 - Math.pow(beta1, t));
		double _r = r / (1 - Math.pow(beta2, t));
		double delta = -e * _s / (Math.sqrt(_r) + d);
		return value + delta;
	}
}
