package p;

import java.util.ArrayList;
import java.util.List;

public class Checker {
	
	static boolean adjacent(Situation s1, Situation s2){

		if(s1.isBoatLeft() != s2.isBoatLeft()){
			if(s1.getBoat().size() == 0 || s2.getBoat().size() == 0)
				return false;
			else if(!s1.getBoat().equals(s2.getBoat()))
				return false;
			else if(!s1.getLeftBank().equals(s2.getLeftBank()))
				return false;
			else if(!s1.getRightBank().equals(s2.getRightBank()))
				return false;
			
			return true;
		}else if(s1.isBoatLeft()){
			if(!s1.getRightBank().equals(s2.getRightBank()))
				return false;
			else
				return adjacent(s1.getLeftBank(), s1.getBoat(), s2.getLeftBank(), s2.getBoat());
		}else{
			if(!s1.getLeftBank().equals(s2.getLeftBank()))
				return false;
			else
				return adjacent(s1.getRightBank(), s1.getBoat(), s2.getRightBank(), s2.getBoat());
		}
	}
	
	private static boolean adjacent(List<Cat> bank1, List<Cat> boat1, List<Cat> bank2, List<Cat> boat2){
		
		if(bank1.size() > bank2.size()){
			if(!bank1.containsAll(bank2))
				return false;
			else if(!boat2.containsAll(boat1))
				return false;
			else{
				List<Cat> bank = new ArrayList<>(bank1);
				List<Cat> boat = new ArrayList<>(boat2);
				bank.removeAll(bank2);
				boat.removeAll(boat1);
				
				if(!bank.equals(boat))
					return false;
			}
			
			return true;	
		}else if(bank2.size() > bank1.size()){
			if(!bank2.containsAll(bank1))
				return false;
			else if(!boat1.containsAll(boat2))
				return false;
			else{
				List<Cat> bank = new ArrayList<>(bank2);
				List<Cat> boat = new ArrayList<>(boat1);
				bank.removeAll(bank1);
				boat.removeAll(boat2);
				
				if(!bank.equals(boat))
					return false;
			}
			
			return true;
		}
		
		return false;
	}
}