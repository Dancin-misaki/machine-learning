package NeuralNetwork.Operate.ForMatrix;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.FuncOp.FuncMax;

public class MatMaxPooling extends MatOperate {
	FuncMax max;
	public MatMaxPooling() {
		max = new FuncMax();
		this.paramNum = 1;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix m = matrixs[0];
		if(m.rows()%2 != 0 || m.cols()%2 != 0) {
			throw new Exception(m.getName()+" row and col should multiple of 2.");
		}
		for(int i = 0; i < m.rows(); i+=2) {
			for(int j = 0; j < m.cols(); j+=2) {
				Scalar[] scalars = new Scalar[4];
				for(int k = 0; k < 4; k++) {
					scalars[0] = m.get(i, j);
					scalars[1] = m.get(i, j+1);
					scalars[2] = m.get(i+1, j);
					scalars[3] = m.get(i+1, j+1);
				}
				caller.get(i/2, j/2).link(max, scalars);
			}
		}
	}

}
