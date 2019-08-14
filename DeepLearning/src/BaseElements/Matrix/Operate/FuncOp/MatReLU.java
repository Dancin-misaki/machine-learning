package BaseElements.Matrix.Operate.FuncOp;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.FuncOp.FuncReLU;

public class MatReLU extends MatOperate {
	public MatReLU() {
		this.paramNum = 1;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix m1 = matrixs[0];
		if(!m1.sameSize(caller)) {
			throw new Exception("matrixs not same size");
		}
		int row = caller.rows();
		int col = caller.cols();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Scalar s = m1.get(i, j);
				caller.get(i, j).link(new FuncReLU(), s);
			}
		}
	}

}
