package BaseElements.Scalar.Operate.BaseOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class VarMultAdd extends Operate {
	public VarMultAdd() {
		this.paramNum = null;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		double a = 0;
		double b = 0;
		for(int i = 0; i < dualNums.length; i++) {
			a += dualNums[i].a;
			b += dualNums[i].b;
		}
		return new DualNum(a,b);
	}

}
