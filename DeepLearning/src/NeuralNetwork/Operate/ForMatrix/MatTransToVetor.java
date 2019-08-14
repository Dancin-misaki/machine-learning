package NeuralNetwork.Operate.ForMatrix;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.FuncOp.Func2MulAdd;

public class MatTransToVetor extends MatOperate {
	public MatTransToVetor() {
		this.paramNum = 2;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix w = matrixs[0];
		Matrix x = matrixs[1];

		if(w.cols() != x.rows()*x.cols()) {
			throw new Exception(w.getName()+" and "+x.getName()+" cant Multiplication");
		}
		if(caller.rows() != w.rows() || caller.cols() != 1) {
			throw new Exception(caller.getName()+" size error");
		}
		int len = x.rows()*x.cols();
		for(int i = 0; i < caller.rows(); i++) {
			Scalar[] scalars = new Scalar[len * 2];
			for(int j = 0; j < len; j++) {
				scalars[2*j] = w.get(i, j);
				int row = j / x.cols();
				int col = j % x.cols();
				scalars[2*j+1] = x.get(row, col);
			}
			caller.get(i, 0).link(new Func2MulAdd(), scalars);
		}
	}
}
