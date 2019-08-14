package BaseElements.Matrix;

import java.util.ArrayList;

import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Optimizer.Optimizer;
import BaseElements.Scalar.Scalar;
import BaseElements.Scalar.ScalarCalc;
import BaseElements.Scalar.ScalarTopo;

public class Matrix {
	private String name;
	private Scalar[][] datas;
	private int row;
	private int col;

	public Matrix(String name, boolean needGrad, double[][] datas) {
		this.name = name;
		this.row = datas.length;
		this.col = datas[0].length;
		this.datas = new Scalar[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				String tempName = null;
				if(name != null) {
					tempName = name+" "+(i+1)+" "+(j+1);
				}
				this.datas[i][j] = new Scalar(tempName,datas[i][j],needGrad);
			}
		}
	}
	public Matrix(String name, boolean needGrad, int row, int col) {
		this.name = name;
		this.row = row;
		this.col = col;
		datas = new Scalar[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				String tempName = null;
				if(name != null) {
					tempName = name+" "+(i+1)+" "+(j+1);
				}
				this.datas[i][j] = new Scalar(tempName,needGrad);
			}
		}
	}
	public final void link(MatOperate op,Matrix...matrixs) throws Exception {
		for(Matrix m : matrixs) {
			if(m == this) {
				throw new Exception("dangerous operation:"+m.getName()+" link itself");
			}
		}
		op.link(this, matrixs);
	}
	public final void setDatas(double[][] datas) {
		if(datas.length != row || datas[0].length != col) {
			throw new IndexOutOfBoundsException(datas.length+"x"+datas[0].length+" cannot into "+row+"x"+col);
		}
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				this.setValue(i, j, datas[i][j]);
			}
		}
	}
	
	public final void setDatasAll(double value) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				this.setValue(i, j, 1);
			}
		}
	}
	
	public final void update(boolean train) {
		ArrayList<Scalar> list = null;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				list = ScalarTopo.getTopo(this.get(i, j));
				ScalarCalc.calc(list, train);
			}
		}
	}
	
	public final void updateValue() {
		ArrayList<Scalar> list = null;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				list = ScalarTopo.getTopo(this.get(i, j));
				for(int k = 0, len = list.size(); k < len; k++) {
					list.get(k).updateValue();
				}
			}
		}
	}
	
	public final void setOptimizer(Optimizer o) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				try {
					this.get(i, j).setOptimizer(o.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public final String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{'").append(name).append("',");
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				str.append(this.get(i, j));
			}
		}
		str.append( "}");
		return str.toString();
	}
	public final double[][] toArrays(){
		double[][] re = new double[this.rows()][this.cols()];
		for(int i = 0; i < this.rows(); i++) {
			for(int j = 0; j < this.cols(); j++) {
				re[i][j] = this.getValue(i, j);
			}
		}
		return re;
	}
	public final String getName() {
		return name;
	}
	public final int cols() {
		return col;
	}
	public final int rows() {
		return row;
	}
	public final Scalar get(int row, int col) {
		return datas[row][col];
	}
	public final double getValue(int row, int col) {
		return datas[row][col].getValue();
	}
	public final void setValue(int row, int col, double value) {
		datas[row][col].setValue(value);
	}
	public final boolean sameSize(Matrix m) {
		if(this.row != m.rows() || this.col != m.cols())
			return false;
		return true;
	}
	
}
