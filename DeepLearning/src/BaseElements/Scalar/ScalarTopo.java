package BaseElements.Scalar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScalarTopo {
	public static int maxNodeSize;
	private static HashMap<Scalar,ArrayList<Scalar>> bufferedTopo;
	private static Scalar lastScalar;
	private static ArrayList<Scalar> lastTopo;
	static {
		bufferedTopo = new HashMap<>();
	}

	public static final ArrayList<Scalar> getTopo(Scalar root){
		if(root == lastScalar) {
			return lastTopo;
		}
		if(bufferedTopo.containsKey(root)) {
			lastScalar = root;
			lastTopo = bufferedTopo.get(root);
			return lastTopo;
		}
		
		LinkedHashMap<Scalar,Integer> scalarMap = getGraphMap(root);
		ArrayList<Scalar> re = topoSort(scalarMap);
		
		int size = re.size();
		if(maxNodeSize < size) {
			maxNodeSize = size;
		}
		bufferedTopo.put(root, re);
		lastScalar = root;
		lastTopo = re;
		return re;
	}

	//可从任意节点获取拓扑图
	private static final LinkedHashMap<Scalar,Integer> getGraphMap(Scalar root){
		LinkedHashMap<Scalar,Integer> scalarMap = new LinkedHashMap<>();

		//广度优先遍历 获取所有Scalar和它的入度(这里为子节点个数)存在scalarMap中
		
		ArrayList<Scalar> used = new ArrayList<>();
		ArrayList<Scalar> thisTurn = new ArrayList<>();
		ArrayList<Scalar> nextTurn = new ArrayList<>();
		thisTurn.add(root);
		scalarMap.put(root, 0);
		while(true) {
			for(int i = 0,len = thisTurn.size(); i < len; i++){
				if(used.contains(thisTurn.get(i))) {
					continue;
				}
				if(thisTurn.get(i).getParents() != null) {
					for(Scalar s : thisTurn.get(i).getParents()) {
						if(!scalarMap.containsKey(s)) {
							scalarMap.put(s, 1);
						}else {
							int d = scalarMap.get(s);
							scalarMap.replace(s, d+1);
						}
						if(!nextTurn.contains(s)) {
							nextTurn.add(s);
						}
					}
					used.add(thisTurn.get(i));
				}
			}
			if(nextTurn.size() == 0) {
				break;
			}
			thisTurn = nextTurn;
			nextTurn = new ArrayList<>();
		}
		return scalarMap;
	}

	private static final ArrayList<Scalar> topoSort(LinkedHashMap<Scalar,Integer> scalarMap){
		ArrayList<Scalar> re = new ArrayList<>();
		while(scalarMap.size() != 0) {
			ArrayList<Scalar> temp = new ArrayList<>();
			for(Map.Entry<Scalar,Integer> entry : scalarMap.entrySet()) {
				if(entry.getValue() == 0) {
					temp.add(entry.getKey());
				}
			}
			for(int i = 0,len = temp.size(); i < len; i++){
				if(temp.get(i).getParents() != null) {
					for(Scalar ss : temp.get(i).getParents()) {
						if(scalarMap.containsKey(ss)) {
							int d = scalarMap.get(ss);
							scalarMap.replace(ss, d-1);
						}
					}
				}
				scalarMap.remove(temp.get(i));
			}
			re.addAll(temp);
		}
		return re;
	}
}
