package BaseElements.Scalar.Operate;

public class DualNum {
	public double a;
	public double b;
	public DualNum(double a, double b) {
		this.a = a;
		this.b = b;
	}
	public DualNum(double a) {
		this.a = a;
		this.b = 0;
	}
	public DualNum(DualNum d) {
		this.a = d.a;
		this.b = d.b;
	}
	@Override
	public String toString() {
		return "(" + a + "+" + b + "¦Å)";
	}
}
