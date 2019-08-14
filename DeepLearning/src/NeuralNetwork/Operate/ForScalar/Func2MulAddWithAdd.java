package NeuralNetwork.Operate.ForScalar;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;
import BaseElements.Scalar.Operate.BaseOp.VarAdd;
import BaseElements.Scalar.Operate.BaseOp.VarMul;
import BaseElements.Scalar.Operate.BaseOp.VarMultAdd;

public class Func2MulAddWithAdd extends Operate {

	private DualNum[] nums;
	private VarMul mul;
	private VarMultAdd mulAdd;
	private VarAdd add;
	private Operate actfunc;

	public Func2MulAddWithAdd(Operate actfunc) {
		mul = new VarMul();
		mulAdd = new VarMultAdd();
		add = new VarAdd();
		this.actfunc = actfunc;
		this.paramNum = null;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		nums = new DualNum[(dualNums.length-1)/2];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = mul.calculate(dualNums[2*i], dualNums[2*i+1]);
		}
		DualNum temp = mulAdd.calculate(nums);
		temp = add.calculate(temp,dualNums[dualNums.length-1]);
		if(actfunc != null) {
			return actfunc.calculate(temp);
		}else {
			return temp;
		}
	}
}
