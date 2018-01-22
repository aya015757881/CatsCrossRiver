package p;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Searcher {
	
	private List<Situation> situations = new Combiner().situations();
	private Situation startNode = certainNode(situations, "start");
	private Situation endNode = certainNode(situations, "end");
	private HashSet<String> resultSet;
	
	HashSet<String> paths(){
		
		resultSet = new HashSet<String>();
		searchPaths(startNode, new ArrayList<Situation>(), 1);
		return resultSet;
	}
	
	private void searchPaths(Situation s, List<Situation> passedNodes, int count){
		
		if(count > 36)
			return;
		
		passedNodes.add(s);
	
		if(s == endNode)
			resultSet.add(ResultConverter.result(passedNodes));
		else{
			List<Situation> adjacents = new ArrayList<>(s.getAdjacentNodes());
			adjacents.removeAll(passedNodes);
		
			for(Situation si : adjacents){
				List<Situation> pNodes = new ArrayList<>(passedNodes);
				searchPaths(si, pNodes, count+1);
			}
		}
	}
	
	private Situation certainNode(List<Situation> situations, String nodeName){
		
		for(Situation s : situations)
			if(isCertainNode(s,nodeName))
				return s;
		
		return null;
	}
	
	private boolean isCertainNode(Situation s, String nodeName){
		
		boolean isRightSide;
		boolean isBoatEmpty = s.getBoat().size() == 0;
		boolean isBankEmpty;
		
		if("start".equals(nodeName)){
			isRightSide = s.isBoatLeft();
			isBankEmpty = s.getRightBank().size() == 0;
		}else{
			isRightSide = !s.isBoatLeft();
			isBankEmpty = s.getLeftBank().size() == 0;
		}
		
		return isRightSide && isBoatEmpty && isBankEmpty;
	}
}