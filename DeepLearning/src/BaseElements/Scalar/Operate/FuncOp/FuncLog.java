package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncLog extends Operate {
	public FuncLog() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = Math.log(dualNums[0].a);
		double b = dualNums[0].b / dualNums[0].a;
		return new DualNum(a,b);
	}
}
