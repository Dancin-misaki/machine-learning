package BaseElements.Scalar.Operate.BaseOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class VarSub extends Operate {
	public VarSub() {
		this.paramNum = 2;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = dualNums[0].a - dualNums[1].a;
		double b = dualNums[0].b - dualNums[1].b;
		return new DualNum(a,b);
	}

}
