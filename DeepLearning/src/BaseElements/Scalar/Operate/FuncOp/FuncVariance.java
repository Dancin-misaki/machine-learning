package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;
import BaseElements.Scalar.Operate.BaseOp.VarMul;
import BaseElements.Scalar.Operate.BaseOp.VarSub;

public class FuncVariance extends Operate {
	private VarSub sub;
	private VarMul mul;
	public FuncVariance() {
		sub = new VarSub();
		mul = new VarMul();
		this.paramNum = 2;
	}
	@Override
	final public DualNum calculate(final DualNum...dualNums) {
		DualNum temp = sub.calculate(dualNums);
		return mul.calculate(temp, temp);
	}

}
