package BaseElements.Matrix.Operate.MatOp;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.BaseOp.VarMultAdd;

public class MatMultAdd extends MatOperate {
	VarMultAdd madd;
	public MatMultAdd() {
		madd = new VarMultAdd();
		this.paramNum = null;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		for(int row = 0; row < caller.rows(); row++) {
			for(int col = 0; col < caller.cols(); col++) {
				Scalar[] scalars = new Scalar[matrixs.length];
				for(int i = 0; i < matrixs.length; i++) {
					if(!caller.sameSize(matrixs[i])) {
						throw new Exception("matrixs not same size "+caller.getName()+" "+matrixs[i].getName());
					}
					scalars[i] = matrixs[i].get(row, col);
				}
				caller.get(row, col).link(madd, scalars);
			}
		}
	}
}
