package NeuralNetwork.Operate.ForMatrix;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.Operate.Operate;
import NeuralNetwork.Operate.ForScalar.Func2MulAddWithAdd;

public class MatConvolution extends MatOperate {
	Func2MulAddWithAdd func;
	public MatConvolution(Operate actfunc) {
		func = new Func2MulAddWithAdd(actfunc);
		this.paramNum = 2;
	}
	@Override
	protected void method(Matrix caller, Matrix... matrixs) throws Exception {
		Matrix picture = matrixs[0];
		Matrix filter = matrixs[1];
		if(picture.rows() < filter.rows() || picture.cols() < filter.cols()) {
			throw new Exception(picture.getName()+" size is smaller than "+filter.getName());
		}
		if(filter.rows()%2 != 1 || filter.cols()%2 != 1) {
			throw new Exception(filter.getName()+" size error");
		}
		if(!picture.sameSize(caller)) {
			throw new Exception(caller.getName()+" size is not same "+picture.getName());
		}
		final int filterCenterX = filter.cols()/2;
		final int filterCenterY = filter.rows()/2;
		for(int i = 0; i < caller.rows(); i++) {
			for(int j = 0; j < caller.cols(); j++) {
				int rowStart = 0, rowEnd = 0, rowLen = 0;
				int colStart = 0, colEnd = 0, colLen = 0;
				if(i < filter.rows()/2) {
					rowStart = filterCenterY - i;
					rowEnd = filter.rows();
				}
				else if(caller.rows()-1-i < filter.rows()/2) {
					rowStart = 0;
					rowEnd = filter.rows() - filter.rows()/2 + (caller.rows()-1-i);
				}
				else {
					rowStart = 0;
					rowEnd = filter.rows();
				}
				rowLen = rowEnd - rowStart;
				if(j < filter.cols()/2) {
					colStart = filterCenterX - j;
					colEnd = filter.cols();
				}
				else if(caller.cols()-1-j < filter.cols()/2) {
					colStart = 0;
					colEnd = filter.cols() - filter.cols()/2 + (caller.cols()-1-j);
				}
				else {
					colStart = 0;
					colEnd = filter.cols();
				}
				colLen = colEnd - colStart;
				int k = rowLen * colLen;
				Scalar[] scalars = new Scalar[k * 2];
				k = 0;
				for(int a = rowStart; a < rowEnd; a++) {
					for(int b = colStart; b < colEnd; b++) {
						scalars[2*k] = picture.get(i-filterCenterY+rowStart, j-filterCenterX+colStart);
						scalars[2*k+1] = filter.get(a, b);
						k++;
					}
				}
				caller.get(i, j).link(func, scalars);
			}
		}
	}
}
