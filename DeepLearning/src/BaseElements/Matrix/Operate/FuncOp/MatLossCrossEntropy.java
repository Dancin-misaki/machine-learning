package BaseElements.Matrix.Operate.FuncOp;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.ForMatOp.FuncForMatLossCrossEntropy;

public class MatLossCrossEntropy extends MatOperate {
	public MatLossCrossEntropy() {
		this.paramNum = 2;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix m1 = matrixs[0];
		Matrix m2 = matrixs[1];
		if(!m1.sameSize(m2)) {
			throw new Exception("matrixs not same size");
		}
		if(caller.rows() != 1 || caller.cols() != 1) {
			throw new Exception(caller.getName()+" size error");
		}
		int row = m1.rows();
		int col = m1.cols();
		Scalar[] scalars = new Scalar[row*col*2];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Scalar s1 = m1.get(i, j);
				Scalar s2 = m2.get(i, j);
				scalars[2*i*col+2*j] = s1;
				scalars[2*i*col+2*j+1] = s2;
			}
		}
		caller.get(0, 0).link(new FuncForMatLossCrossEntropy(), scalars);
	}
}
