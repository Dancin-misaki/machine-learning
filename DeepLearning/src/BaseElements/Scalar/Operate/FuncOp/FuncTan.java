package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncTan extends Operate {
	public FuncTan() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = Math.tan(dualNums[0].a);
		double b = dualNums[0].b / Math.pow(Math.cos(dualNums[0].a), 2);
		return new DualNum(a,b);
	}

}
