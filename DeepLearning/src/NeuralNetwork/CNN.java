package NeuralNetwork;

import BaseElements.Matrix.Matrix;
import BaseElements.Matrix.Operate.FuncOp.MatLossCrossEntropy;
import BaseElements.Matrix.Operate.FuncOp.MatSoftMax0;
import BaseElements.Matrix.Operate.MatOp.MatMultAdd;
import BaseElements.Optimizer.AdamOptimizer;
import BaseElements.Scalar.Operate.FuncOp.FuncReLU;
import NeuralNetwork.Operate.ForMatrix.MatConvolution;
import NeuralNetwork.Operate.ForMatrix.MatMaxPooling;
import NeuralNetwork.Operate.ForMatrix.MatTransToVetor;
import NeuralNetwork.Operate.ForMatrix.MatWXPlusB;

public class CNN {

	public Matrix in = new Matrix("input", false, 16, 16);
	public Matrix ex = new Matrix("expect", false, 10, 1);
	public Matrix out = new Matrix("out", true, 10, 1);
	public Matrix loss = new Matrix("loss", true, 1, 1);

	public Matrix filter1 = new Matrix("filter1", true, 3, 3);
	public Matrix filter2 = new Matrix("filter2", true, 3, 3);
	public Matrix filter3 = new Matrix("filter3", true, 3, 3);
	public Matrix filterB = new Matrix("filterB", true, 16, 16);
	public Matrix matWeight = new Matrix("matWeight", true, 10, 8*8);
	public Matrix weight1 = new Matrix("weight1", true, 10, 10);
	public Matrix deviation1 = new Matrix("deviation1", true, 10, 1);
	public Matrix weight2 = new Matrix("weight2", true, 10, 10);
	public Matrix deviation2 = new Matrix("deviation2", true, 10, 1);
	
	
	public double Averloss;

	public CNN() throws Exception {
		
		filter1.setOptimizer(new AdamOptimizer());
		filter2.setOptimizer(new AdamOptimizer());
		filter3.setOptimizer(new AdamOptimizer());
		filterB.setOptimizer(new AdamOptimizer());
		matWeight.setOptimizer(new AdamOptimizer());
		weight1.setOptimizer(new AdamOptimizer());
		deviation1.setOptimizer(new AdamOptimizer());
		weight2.setOptimizer(new AdamOptimizer());
		deviation2.setOptimizer(new AdamOptimizer());
		
		Matrix a1 = new Matrix("a1", true, 16, 16);
		a1.link(new MatConvolution(new FuncReLU()), in, filter1);
		Matrix a2 = new Matrix("a2", true, 16, 16);
		a2.link(new MatConvolution(new FuncReLU()), in, filter2);
		Matrix a3 = new Matrix("a3", true, 16, 16);
		a3.link(new MatConvolution(new FuncReLU()), in, filter3);
		
		Matrix b = new Matrix("b", true, 16, 16);
		b.link(new MatMultAdd(), a1, a2, a3, filterB);
		
		Matrix c = new Matrix("c", true, 8, 8);
		c.link(new MatMaxPooling(), b);
		
		Matrix d = new Matrix("d", true, 10, 1);
		d.link(new MatTransToVetor(), matWeight, c);
		
		Matrix e = new Matrix("e", true, 10, 1);
		e.link(new MatWXPlusB(new FuncReLU()), weight1, d, deviation1);
		
		//out.link(new MatWXPlusB(new FuncReLU()), weight2, e, deviation2);
		
		//loss.link(new MatLossVariance(), ex, out);
		
		Matrix f = new Matrix("f", true, 10, 1);
		f.link(new MatWXPlusB(new FuncReLU()), weight2, e, deviation2);
		
		out.link(new MatSoftMax0(), f);

		loss.link(new MatLossCrossEntropy(), ex, out);
	}

	public boolean train(double[][][] input, double[][][] expect) throws Exception {
		double Averloss = Double.MAX_VALUE;
		double lastloss = 0;
		long deltaTime = 0;
		long t1;
		long t2;
		while(Averloss > 0.01) {
			Averloss = 0;

			t1 = System.currentTimeMillis();
			for(int i = 0; i < input.length; i++){
				in.setDatas(input[i]);
				ex.setDatas(expect[i]);
				loss.update(true);
				Averloss += loss.getValue(0, 0);
			}
			this.loss.updateValue();
			Averloss = Averloss / input.length;
			this.Averloss = Averloss;
			t2 = System.currentTimeMillis();
			deltaTime += t2 - t1;
			int frame = 1;
			if(deltaTime > 1000/frame) {
				deltaTime -= 1000/frame;
				synchronized (this.getClass()) {
					System.out.print(Thread.currentThread().getName());
					System.out.printf("Æ½¾ùÎó²î:%.16f",Averloss);
					System.out.println();
				}

			}
			if(Math.abs(Averloss - lastloss) < 0.00000001) {
				System.out.print(Thread.currentThread().getName());
				System.out.println("ÑµÁ·Ê§°Ü!");
				return false;
			}
			lastloss = Averloss;
		}
		System.out.print(Thread.currentThread().getName());
		System.out.println("ÑµÁ·³É¹¦!");
		return true;
	}

	public double[][] test(double[][] input) throws Exception {
		in.setDatas(input);
		loss.update(false);
		return out.toArrays();
	}
}
