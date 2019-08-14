package BaseElements.Matrix.Operate;

import BaseElements.Matrix.Matrix;

public abstract class MatOperate {
	protected Integer paramNum;
	
	public int getParamNum() {
		return paramNum;
	}
	public final void link(Matrix caller, Matrix...matrixs) throws Exception {
		if(paramNum != null && paramNum != matrixs.length) {
			throw new IndexOutOfBoundsException();
		}
		method(caller, matrixs);
	}
	protected abstract void method(Matrix caller, Matrix...matrixs) throws Exception;
}
