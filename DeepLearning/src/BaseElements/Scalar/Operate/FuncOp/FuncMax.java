package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncMax extends Operate {
	public FuncMax() {
		this.paramNum = null;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		double a = 0;
		double b = 0;
		a = dualNums[0].a;
		b = dualNums[0].b;
		for(int i = 0; i < dualNums.length; i++) {
			if(a < dualNums[i].a) {
				a = dualNums[i].a;
				b = dualNums[i].b;
			}
		}
		return new DualNum(a,b);
	}
}
