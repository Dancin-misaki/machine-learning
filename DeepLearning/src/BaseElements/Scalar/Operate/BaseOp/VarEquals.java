package BaseElements.Scalar.Operate.BaseOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class VarEquals extends Operate {
	public VarEquals() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		double a = dualNums[0].a;
		double b = dualNums[0].b;
		return new DualNum(a,b);
	}
}
