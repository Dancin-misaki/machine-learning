package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncAverage extends Operate {
	public FuncAverage() {
		this.paramNum = null;
	}
	@Override
	final public DualNum calculate(DualNum... dualNums) {
		double a = 0;
		double b = 0;
		for(int i = 0; i < dualNums.length; i++) {
			a += dualNums[i].a;
			b += dualNums[i].b;
		}
		a /= dualNums.length;
		b /= dualNums.length;
		return new DualNum(a,b);
	}

}
