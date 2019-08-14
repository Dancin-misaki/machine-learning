package test;

import BaseElements.Matrix.Operate.FuncOp.MatLossCrossEntropy;
import BaseElements.Matrix.Operate.FuncOp.MatLossVariance;
import BaseElements.Optimizer.AdamOptimizer;
import BaseElements.Scalar.ScalarTopo;
import BaseElements.Scalar.Operate.FuncOp.FuncReLU;
import BaseElements.Scalar.Operate.FuncOp.FuncSigmoid;
import NeuralNetwork.NN;
import tools.Tools;

public class NNTest {

	public static void main(String[] args) throws Exception {
		
		double[][][] input =  {{{1},{1}},{{1},{0}},{{0},{1}},{{0},{0}}};
		double[][][] expect = {  {{0}}  ,  {{1}}  ,  {{1}}  ,  {{0}}  };
		
		boolean f = false;
		long t1 = System.currentTimeMillis();
		while(!f) {

			NN nn = new NN();
			nn.setInSize(2, 1);
			
			double[][] wIn = Tools.rand2DArr(4, 2);
			double[][] bIn = Tools.fill2DArr(4, 1, 0);
			nn.addLayer(wIn, bIn, new FuncReLU());
			
			for(int i = 0; i < 1; i++) {
				double[][] w = Tools.rand2DArr(4, 4);
				double[][] b = Tools.fill2DArr(4, 1, 0);
				nn.addLayer(w, b, new FuncReLU());
			}
			
			double[][] wOut = Tools.rand2DArr(1, 4);
			double[][] bOut = Tools.fill2DArr(1, 1, 0);
			nn.addLayer(wOut, bOut, new FuncSigmoid());
			
			nn.endSetting(true, new MatLossCrossEntropy(), new AdamOptimizer());
			
			f = nn.trainAll(input, expect, 0.0000001, 0.000000000001, true);
		
			if(f) {
				for(int i = 0; i < input.length; i++) {
					System.out.print("输入:"+Tools.arrayToStr(input[i]));
					nn.setIn(input[i]);
					System.out.println("\t预测:"+Tools.arrayToStr(nn.getOut()));
				}
			}
			System.out.println("节点数:"+ScalarTopo.maxNodeSize);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("总时长:"+(t2-t1)+"ms");
	}

}
