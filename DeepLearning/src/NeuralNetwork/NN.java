package NeuralNetwork;

import java.util.ArrayList;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.MatOperate;
import BaseElements.Optimizer.Optimizer;
import BaseElements.Scalar.Operate.Operate;
import NeuralNetwork.Operate.ForMatrix.MatWXPlusB;
import tools.Tools;

public class NN {
	private Matrix in;
	private Matrix real;
	private Matrix loss;
	private int hidelayernum;
	public ArrayList<Matrix> var;
	private ArrayList<Matrix> hide;
	private boolean train;
	
	public NN() {
		train = false;
		var = new ArrayList<>();
		hide = new ArrayList<>();
	}
	
	public boolean trainAll(double[][][] in, double[][][] real, double targetLoss, double minDeltaLoss, boolean show) {
		double loss = Double.MAX_VALUE;
		double lastAverLoss = 0;
		double delta = 0;

		long deltaTime = 0;

		while(loss > targetLoss) {
			loss = 0;
			if(show) {
				long t1 = System.currentTimeMillis();

				int[] selque = Tools.outOfOrderArr(in.length);
				for(int j = 0; j < selque.length; j++){

					setIn(in[selque[j]]);
					setReal(real[selque[j]]);

					this.loss.update(true);
					loss += this.loss.getValue(0, 0);
				}
				this.loss.updateValue();
				loss = loss / in.length;
				delta = Math.abs(lastAverLoss - loss);
				if(loss > targetLoss && delta < minDeltaLoss) {
					System.out.println("ÑµÁ·Ê§°Ü");
					return false;
				}
				lastAverLoss = loss;
				
				long t2 = System.currentTimeMillis();
				deltaTime += t2 - t1;
				int frame = 8;
				if(deltaTime > 1000/frame) {
					deltaTime -= 1000/frame;
					System.out.printf("Æ½¾ùÎó²î:%.16f",lastAverLoss);
					System.out.println();
				}
			}
			else {
				int[] selque = Tools.outOfOrderArr(in.length);
				for(int j = 0; j < selque.length; j++){

					setIn(in[selque[j]]);
					setReal(real[selque[j]]);

					this.loss.update(true);
					loss += this.loss.getValue(0, 0);
				}
				this.loss.updateValue();
				loss = loss / in.length;
				delta = Math.abs(lastAverLoss - loss);
				if(loss > targetLoss && delta < minDeltaLoss) {
					System.out.println("ÑµÁ·Ê§°Ü");
					return false;
				}
				lastAverLoss = loss;
			}
		}
		System.out.println("ÑµÁ·³É¹¦");
		return true;
	}
	
	public void setIn(double[][] in) {
		this.in.setDatas(in);
	}
	private void setReal(double[][] real) {
		this.real.setDatas(real);
	}
	public double[][] getOut() {
		Matrix out = hide.get(hide.size()-1);
		out.update(false);
		return out.toArrays();
	}
	public void setInSize(int row, int col) {
		in = new Matrix("in", false, row, col);
	}
	public void addLayer(double[][] w, double[][] b, Operate actfunc) throws Exception {
		Matrix m_w = new Matrix("w"+hidelayernum, true, w);
		Matrix m_b = new Matrix("b"+hidelayernum, true, b);
		var.add(m_w);
		var.add(m_b);

		Matrix last_x = null;
		if(hidelayernum == 0) {
			last_x = in;
		}else {
			last_x = hide.get(hide.size()-1);
		}
		Matrix m_next_x = new Matrix("x"+(hidelayernum+1), true, m_b.rows(), m_b.cols());
		m_next_x.link(new MatWXPlusB(actfunc), m_w, last_x, m_b);
		hide.add(m_next_x);
		hidelayernum++;
	}
	public void endSetting(boolean train, MatOperate lossfunc, Optimizer optimizer) throws Exception {
		this.train = train;
		if(this.train) {
			for(int i = 0; i < var.size(); i++) {
				var.get(i).setOptimizer(optimizer);
			}
			loss = new Matrix("loss", true, 1, 1);
			Matrix last_x = hide.get(hide.size()-1);
			real = new Matrix("real", false, last_x.rows(), last_x.cols());
			loss.link(lossfunc, real, last_x);
		}
	}
}
