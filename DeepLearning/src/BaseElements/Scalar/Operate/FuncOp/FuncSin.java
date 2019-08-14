package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncSin extends Operate {
	public FuncSin() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = Math.sin(dualNums[0].a);
		double b = Math.cos(dualNums[0].a)*dualNums[0].b;
		return new DualNum(a,b);
	}

}
