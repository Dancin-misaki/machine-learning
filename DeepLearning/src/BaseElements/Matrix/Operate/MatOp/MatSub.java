package BaseElements.Matrix.Operate.MatOp;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.BaseOp.VarSub;

public class MatSub extends MatOperate {
	public MatSub() {
		this.paramNum = 2;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix m1 = matrixs[0];
		Matrix m2 = matrixs[1];
		if(!m1.sameSize(m2) || !m1.sameSize(caller)) {
			throw new Exception("matrixs not same size");
		}
		int row = caller.rows();
		int col = caller.cols();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Scalar s1 = m1.get(i, j);
				Scalar s2 = m2.get(i, j);
				caller.get(i, j).link(new VarSub(), s1, s2);
			}
		}
	}
}
