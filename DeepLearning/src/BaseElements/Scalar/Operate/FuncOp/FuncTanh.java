package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncTanh extends Operate {
	public FuncTanh() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		double z = Math.exp(dualNums[0].a);
		double _z = Math.exp(-dualNums[0].a);
		double a = (z - _z)/(z + _z);
		double b = (1 - a * a) * dualNums[0].b;
		return new DualNum(a,b);
	}
}
