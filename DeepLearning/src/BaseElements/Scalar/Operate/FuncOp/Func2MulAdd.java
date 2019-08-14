package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;
import BaseElements.Scalar.Operate.BaseOp.VarMul;
import BaseElements.Scalar.Operate.BaseOp.VarMultAdd;

public class Func2MulAdd extends Operate{

	private DualNum[] nums;
	private VarMul mul;
	private VarMultAdd mulAdd;

	public Func2MulAdd() {
		mul = new VarMul();
		mulAdd = new VarMultAdd();
		this.paramNum = null;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		nums = new DualNum[dualNums.length/2];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = mul.calculate(dualNums[2*i], dualNums[2*i+1]);
		}
		return mulAdd.calculate(nums);
	}

}
