package BaseElements.Scalar.Operate.ForMatOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;
import BaseElements.Scalar.Operate.BaseOp.VarMultAdd;
import BaseElements.Scalar.Operate.FuncOp.FuncVariance;

public class FuncForMatLossVariance extends Operate {

	private DualNum[] nums;
	private FuncVariance fv;
	private VarMultAdd mulAdd;

	public FuncForMatLossVariance() {
		fv = new FuncVariance();
		mulAdd = new VarMultAdd();
		this.paramNum = null;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		nums = new DualNum[dualNums.length/2];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = fv.calculate(dualNums[2*i], dualNums[2*i+1]);
		}
		return mulAdd.calculate(nums);
	}

}
