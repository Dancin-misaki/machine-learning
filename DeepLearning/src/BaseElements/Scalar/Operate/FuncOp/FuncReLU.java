package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncReLU extends Operate {
	public FuncReLU() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = dualNums[0].a >= 0 ? dualNums[0].a : 0;
		double b = dualNums[0].a >= 0 ? dualNums[0].b : 0;
		return new DualNum(a,b);
	}

}
