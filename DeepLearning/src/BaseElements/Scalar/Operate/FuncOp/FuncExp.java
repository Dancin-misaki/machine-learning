package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncExp extends Operate {
	public FuncExp() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(DualNum... dualNums) {
		double a = Math.exp(dualNums[0].a);
		double b = Math.exp(dualNums[0].a) * dualNums[0].b;
		return new DualNum(a,b);
	}
}
