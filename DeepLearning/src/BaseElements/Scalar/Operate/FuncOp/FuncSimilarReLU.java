package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;

public class FuncSimilarReLU extends Operate {
	
	public FuncSimilarReLU() {
		this.paramNum = 1;
	}
	@Override
	final public DualNum calculate(DualNum... dualNums) {
		double a,b;
		if(dualNums[0].a > 0) {
			a = Math.pow(dualNums[0].a + 0.5, 2) + 0.75;
			b = 2 * (dualNums[0].a + 0.5) * dualNums[0].b;
		}
		else if(dualNums[0].a <= 0 && dualNums[0].a > -2) {
			a = 0.45 * dualNums[0].a + 1;
			b = 0.45 * dualNums[0].b;
		}
		else if(dualNums[0].a <= -2 && dualNums[0].a > -10002) {
			a = 0.00001 * dualNums[0].a + 0.10002;
			b = 0.00001 * dualNums[0].b;
		}
		else {
			a = 0;
			b = 0;
		}
		return new DualNum(a,b);
	}

}
