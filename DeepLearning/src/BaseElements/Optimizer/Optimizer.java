package BaseElements.Optimizer;


public abstract class Optimizer implements Cloneable{
	
	public abstract double update(double value, double grad);
	
	public final Optimizer clone() throws CloneNotSupportedException {
		return (Optimizer)super.clone();
	}
}
