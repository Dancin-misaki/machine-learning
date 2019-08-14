package NeuralNetwork.Operate.ForMatrix;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Operate.BaseOp.VarEquals;

public class MatsToVector extends MatOperate {
	public MatsToVector() {
		this.paramNum = null;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		int size = 0;
		for(int i = 0; i < matrixs.length; i++) {
			size += matrixs[i].rows() * matrixs[i].cols();
		}
		if(caller.cols() != 1 || caller.rows() != size) {
			throw new Exception(caller.getName()+"size error");
		}
		int index = 0;
		for(int i = 0; i < matrixs.length; i++) {
			for(int j = 0; j < matrixs[i].rows(); j++) {
				for(int k = 0; k < matrixs[i].cols(); k++) {
					caller.get(index, 0).link(new VarEquals(), matrixs[i].get(j, k));
					index++;
				}
			}
		}
	}

}
