package BaseElements.Scalar.Operate.FuncOp;

import BaseElements.Scalar.Operate.DualNum;
import BaseElements.Scalar.Operate.Operate;
import BaseElements.Scalar.Operate.BaseOp.VarAdd;
import BaseElements.Scalar.Operate.BaseOp.VarMul;

public class FuncCrossEntropy extends Operate {
	private VarAdd add;
	private VarMul mul;
	private FuncLog log;
	public FuncCrossEntropy() {
		add = new VarAdd();
		mul = new VarMul();
		log = new FuncLog();
		this.paramNum = 2;
	}
	@Override
	final public DualNum calculate(final DualNum... dualNums) {
		DualNum real = new DualNum(dualNums[0]);
		DualNum y = new DualNum(dualNums[1]);
		
		y = log.calculate(y);
		DualNum n = mul.calculate(real,y);
		
		n.a = -n.a;
		n.b = -n.b;
		
		return n;
	}
	
	final public DualNum old(final DualNum... dualNums) {
		DualNum real = new DualNum(dualNums[0]);
		DualNum y = new DualNum(dualNums[1]);
		
		DualNum _real = new DualNum(dualNums[0]);
		DualNum _y = new DualNum(dualNums[1]);
		
		_real.a = 1 -_real.a;
		_real.b = -_real.b;
		_y.a = 1 - _y.a;
		_y.b = -_y.b;
		
		y = log.calculate(y);
		_y = log.calculate(_y);
		
		DualNum n1 = mul.calculate(real,y);
		DualNum n2 = mul.calculate(_real,_y);
		
		DualNum n = add.calculate(n1,n2);
		
		n.a = -n.a;
		n.b = -n.b;
		
		return n;
	}
}
