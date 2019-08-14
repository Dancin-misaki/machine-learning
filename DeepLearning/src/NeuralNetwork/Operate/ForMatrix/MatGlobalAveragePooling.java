package NeuralNetwork.Operate.ForMatrix;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.FuncOp.FuncAverage;

public class MatGlobalAveragePooling extends MatOperate {
	FuncAverage aver;
	public MatGlobalAveragePooling() {
		aver = new FuncAverage();
		this.paramNum = null;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		if(caller.cols() != 1 || caller.rows() != matrixs.length) {
			throw new Exception(caller.getName()+"size error");
		}
		
		for(int k = 0; k < matrixs.length; k++) {
			int len = matrixs[k].rows()*matrixs[k].cols();
			Scalar[] scalars = new Scalar[len];
			for(int i = 0; i < len; i++) {
				int row = i / matrixs[k].cols();
				int col = i % matrixs[k].cols();
				scalars[i] = matrixs[k].get(row, col);
			}
			caller.get(k, 0).link(aver, scalars);
		}
	}
}
