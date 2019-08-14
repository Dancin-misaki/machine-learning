package NeuralNetwork.Operate.ForMatrix;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.Operate;
import NeuralNetwork.Operate.ForScalar.Func2MulAddWithAdd;

public class MatWXPlusB extends MatOperate {
	Func2MulAddWithAdd func;
	public MatWXPlusB(Operate actfunc) {
		func = new Func2MulAddWithAdd(actfunc);
		this.paramNum = 3;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix w = matrixs[0];
		Matrix x = matrixs[1];
		Matrix b = matrixs[2];
		
		if(w.cols() != x.rows()) {
			throw new Exception("matrixs cannot multiply:"+w.getName()+" and "+x.getName());
		}
		int numLength = w.cols();
		int row = w.rows();
		int col = x.cols();
		if(row != b.rows() || col != b.cols()) {
			throw new Exception(caller.getName()+" size error");
		}
		if(row != caller.rows() || col != caller.cols()) {
			throw new Exception(caller.getName()+" size error");
		}
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Scalar[] scalars = new Scalar[numLength * 2 + 1];
				for(int k = 0; k < numLength; k++) {
					Scalar s1 = w.get(i, k);
					Scalar s2 = x.get(k, j);
					scalars[2*k] = s1;
					scalars[2*k+1] = s2;
				}
				scalars[scalars.length-1] = b.get(i, j);
				caller.get(i, j).link(func, scalars);
			}
		}
	}
}
