package BaseElements.Scalar.Operate;

import BaseElements.Scalar.Scalar;

public abstract class Operate {
	private DualNum[] dualNums;
	protected Integer paramNum;

	public Integer getParamNum() {
		return paramNum;
	}

	public final double op(Scalar...nodes){
		if(paramNum != null && paramNum != nodes.length) {
			throw new IndexOutOfBoundsException();
		}
		double re = 0;
		dualNums = new DualNum[nodes.length];
		for(int i = 0; i < nodes.length; i++) {
			if(!nodes[i].inited) {
				throw new SecurityException(nodes[i].toString()+"Uninitialized");
			}
			dualNums[i] = new DualNum(nodes[i].getValue());
		}
		DualNum result = calculate(dualNums);
		re = result.a;
		return re;
	}
	public final void grad_op(double grad,Scalar...nodes) {
		if(paramNum != null && paramNum != nodes.length) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < nodes.length; i++) {
			if(nodes[i].needGrad) {
				if(dualNums == null) {
					dualNums = new DualNum[nodes.length];
					for(int j = 0; j < nodes.length; j++) {
						if(!nodes[j].inited) {
							throw new SecurityException(nodes[j].toString()+"Uninitialized");
						}
						dualNums[j] = new DualNum(nodes[j].getValue());
					}
				}
				dualNums[i].b = 1;
				DualNum result = calculate(dualNums);
				nodes[i].grad += result.b * grad;
				dualNums[i].b = 0;
			}
		}
		dualNums = null;
	}
	public abstract DualNum calculate(final DualNum...dualNums);
}
