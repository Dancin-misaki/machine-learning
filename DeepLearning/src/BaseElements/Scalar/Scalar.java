package BaseElements.Scalar;

import BaseElements.Optimizer.Optimizer;
import BaseElements.Scalar.Operate.Operate;

public class Scalar {
	private Scalar[] parents;
	private Operate op;
	private double value;
	private Optimizer optimizer;

	public String name;
	public boolean inited;
	public boolean needGrad;
	public double grad;
	public double sumGrad;
	public int time;

	public Scalar(String name, double value, boolean needGrad) {
		this.name = name;
		this.value = value;
		this.inited = true;
		this.needGrad = needGrad;
		parents = null;
		op = null;
	}
	public Scalar(String name, boolean needGrad) {
		this.name = name;
		this.inited = false;
		this.needGrad = needGrad;
		parents = null;
		op = null;
	}
	public Scalar(String name, boolean needGrad, Operate op, Scalar...nodes) throws Exception {
		link(op, nodes);
		this.inited = false;
		this.name = name;
		this.needGrad = needGrad;
	}
	public void link(Operate op, Scalar...nodes) throws Exception {
		if(op.getParamNum() != null && op.getParamNum() != nodes.length) {
			throw new IndexOutOfBoundsException();
		}
		for(Scalar s : nodes) {
			if(s == this) {
				throw new Exception("dangerous operation:"+s.name+" link itself");
			}
		}
		this.op = op;
		parents = nodes;
		for(int i = 0; i < parents.length; i++) {
		}
	}

	final public void update() {
		if(parents != null && op != null) {
			value = op.op(parents);
			this.inited = true;
		}
	}
	final public void grad_update(Double grad) {
		if(parents != null && op != null) {
			if(grad != null) {
				this.grad = grad;
			}
			op.grad_op(this.grad, parents);
		}
		if(optimizer != null) {
			time++;
			this.sumGrad += this.grad;
			//当数据太多时要做好防溢出
		}
		this.grad = 0;
	}
	
	final public void updateValue() {
		if(optimizer != null) {
			double grad = sumGrad;
			grad /= time;
			sumGrad = 0;
			time = 0;
			value = optimizer.update(value, grad);
		}
	}

	final public double getValue() {
		return value;
	}
	final public void setValue(double value) {
		this.value = value;
		this.inited = true;
	}
	final public Scalar[] getParents() {
		return parents;
	}
	final public int getParentNum() {
		if(parents != null) {
			return parents.length;
		}
		return 0;
	}
	public void setOptimizer(Optimizer o) {
		if(this.needGrad) {
			this.optimizer = o;
		}
	}
	
	public Optimizer getOptimizer() {
		return this.optimizer;
	}

	@Override
	public String toString() {
		return "['" + name + "'," + String.format("%.10f", value) + "]";
	}

}
