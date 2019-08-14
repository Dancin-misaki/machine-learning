package BaseElements.Matrix.Operate.MatOp;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.FuncOp.Func2MulAdd;

public class MatMul extends MatOperate {
	
	public MatMul() {
		this.paramNum = 2;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix m1 = matrixs[0];
		Matrix m2 = matrixs[1];
		if(m1.cols() != m2.rows()) {
			throw new Exception("matrixs cannot multiply");
		}
		int numLength = m1.cols();
		int row = m1.rows();
		int col = m2.cols();
		if(row != caller.rows() || col != caller.cols()) {
			throw new Exception(caller.getName()+" size error");
		}
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Scalar[] scalars = new Scalar[numLength * 2];
				for(int k = 0; k < numLength; k++) {
					Scalar s1 = m1.get(i, k);
					Scalar s2 = m2.get(k, j);
					scalars[2*k] = s1;
					scalars[2*k+1] = s2;
				}
				caller.get(i, j).link(new Func2MulAdd(), scalars);
			}
		}
	}

}
