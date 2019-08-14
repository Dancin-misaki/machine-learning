package BaseElements.Scalar.Operate.BaseOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class VarMul extends Operate {
	public VarMul() {
		this.paramNum = 2;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = dualNums[0].a * dualNums[1].a;
		double b = dualNums[0].a * dualNums[1].b + dualNums[0].b * dualNums[1].a;
		return new DualNum(a,b);
	}

}
