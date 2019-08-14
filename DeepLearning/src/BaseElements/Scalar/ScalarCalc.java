package BaseElements.Scalar;

import java.util.ArrayList;

public class ScalarCalc {
	public static final void calc(ArrayList<Scalar> topo, boolean train) {
		if(topo == null)return;
		//前向传播
		int len = topo.size();
		for(int i = len-1; i >= 0; i--) {
			topo.get(i).update();
		}
		if(!train)return;
		//反向传播
		Double grad = new Double(1);
		for(int i = 0; i < len; i++) {
			if(i == 0) {
				topo.get(i).grad_update(grad);
			}
			else {
				topo.get(i).grad_update(null);
			}
		}
	}
}
