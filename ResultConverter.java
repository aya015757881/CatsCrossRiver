package p;

import java.util.List;

public class ResultConverter {
	
	public static String result(List<Situation> path){
		
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < path.size() - 1; i++){
			
			Situation former = path.get(i);
			Situation latter = path.get(i+1);
			
			if(former.isBoatLeft() != latter.isBoatLeft()){
				
				for(Cat c : former.getBoat())
					result.append(c);
				
				if(former.isBoatLeft())
					result.append("ȥ, ");
				else
					result.append("��, ");
			}
		}
		
		result.delete(result.length() - 2, result.length());
		
		return result.toString();
	}
}