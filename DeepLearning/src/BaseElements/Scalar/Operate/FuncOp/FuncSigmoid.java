package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncSigmoid extends Operate {
	public FuncSigmoid() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		double a = 1 / (1 + Math.exp(-dualNums[0].a));
		double b = a * (1 - a) * dualNums[0].b;
		return new DualNum(a,b);
	}

}
