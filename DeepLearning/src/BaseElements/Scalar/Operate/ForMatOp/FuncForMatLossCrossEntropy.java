package BaseElements.Scalar.Operate.ForMatOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;
import BaseElements.Scalar.Operate.BaseOp.VarMultAdd;
import BaseElements.Scalar.Operate.FuncOp.FuncCrossEntropy;

public class FuncForMatLossCrossEntropy extends Operate {
	private DualNum[] nums;
	private FuncCrossEntropy ce;
	private VarMultAdd mulAdd;
	
	public FuncForMatLossCrossEntropy() {
		ce = new FuncCrossEntropy();
		mulAdd = new VarMultAdd();
		this.paramNum = null;
	}

	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		int pairs = dualNums.length/2;
		nums = new DualNum[pairs];
		for(int i = 0; i < pairs; i++) {
			if(pairs == 1) {
				nums[i] = ce.old(dualNums[2*i], dualNums[2*i+1]);
			}
			else {
				nums[i] = ce.calculate(dualNums[2*i], dualNums[2*i+1]);
			}
		}
		return mulAdd.calculate(nums);
	}

}
