package BaseElements.Matrix.Operate.FuncOp;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.BaseOp.VarDiv;
import BaseElements.Scalar.Operate.BaseOp.VarMultAdd;
import BaseElements.Scalar.Operate.FuncOp.FuncSimilarReLU;

public class MatSoftMax0 extends MatOperate {
	public MatSoftMax0() {
		this.paramNum = 1;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix m = matrixs[0];
		if(!caller.sameSize(m)) {
			throw new Exception("matrixs not same size");
		}
		Scalar[] scalars = new Scalar[m.rows()*m.cols()];
		for(int i = 0; i < m.rows(); i++) {
			for(int j = 0; j < m.cols(); j++) {
				scalars[i*m.cols()+j] = new Scalar(null, true, new FuncSimilarReLU(), m.get(i, j));
			}
		}
		Scalar sumOut = new Scalar("sumOut", true, new VarMultAdd(), scalars);
		for(int i = 0; i < caller.rows(); i++) {
			for(int j = 0; j < caller.cols(); j++) {
				caller.get(i, j).link(new VarDiv(), scalars[i*caller.cols()+j], sumOut);
			}
		}
	}
}
