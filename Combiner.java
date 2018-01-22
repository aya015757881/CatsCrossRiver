package p;

import java.util.ArrayList;
import java.util.List;

public class Combiner {
	
	private List<Cat> cats = new ArrayList<>();
	
	private List<Cat> leftBank;
	private List<Cat> boat;
	private List<Cat> rightBank;
	private List<Cat> boatRest;
	
	private List<Situation> situations = new ArrayList<>();
	
	private Boat b = new Boat();
	private LeftBank l = new LeftBank();
	
	Combiner(){
		cats.add(new Cat("A"));
		cats.add(new Cat("B"));
		cats.add(new Cat("C"));
		cats.add(new Cat("a"));
		cats.add(new Cat("b"));
		cats.add(new Cat("c"));
	}
	
	class Boat implements Occasion {

		public boolean prevented(List<Cat> cats) {
			
			if(cats.size() == 1){
				String name = cats.get(0).toString();
				if("b".equals(name) || "c".equals(name))
					return true;
				else
					return false;
			}else if(cannotRow(cats))
				return true;
			else if(bigCatEatLittleCat(cats))
				return true;
			else
				return false;
		}
		
		public void goOn(List<Cat> cats) {
			
			boat = cats;
			boatRest = new ArrayList<>(Combiner.this.cats);
			boatRest.removeAll(boat);
			
			for(int i = 0; i <= boatRest.size(); i++)
				onto(i, l, boatRest);
		}
	}

	class LeftBank implements Occasion {

		public boolean prevented(List<Cat> cats) {
			
			return bigCatEatLittleCat(cats);
		}
		
		public void goOn(List<Cat> cats) {
			
			leftBank = cats;
			rightBank = new ArrayList<>(boatRest);
			rightBank.removeAll(leftBank);
			
			if(!bigCatEatLittleCat(rightBank)){
				situations.add(new Situation(boat, leftBank, rightBank, true));
				situations.add(new Situation(boat, leftBank, rightBank, false));
			}
		}
	}
	
	List<Situation> situations(){
	
		for(int i = 0; i <= 2; i++)
			onto(i, b, cats);

		clearBoatBankTragedyNodes(situations);
		connectAdjacentNodes(situations);
		clearIsolatedNodes(situations);
		
		return situations;
	}
	
	void onto(int catCount, Occasion occ, List<Cat> totalCats){
		
		if(catCount == 0){
			occ.goOn(new ArrayList<Cat>());
			return;
		}
		
		new Object(){
			
			List<Cat> groupedCats;
			
			void combine(int[] arr, int index, int start, int end){
				for(arr[index] = start; arr[index] <= end; arr[index]++)
					if(index < arr.length - 1)
						combine(arr, index+1, arr[index]+1, end+1);
					else if(occ.prevented(groupedCats = groupedCats(arr,totalCats)))
						continue;
					else
						occ.goOn(groupedCats);
			}
		}.combine(new int[catCount], 0, 0, totalCats.size() - catCount);
	}
	
	void clearBoatBankTragedyNodes(List<Situation> situations){
		
		for(Situation s : new ArrayList<>(situations)){
			List<Cat> boat = s.getBoat();
			List<Cat> bank = s.isBoatLeft() ? s.getLeftBank() : s.getRightBank();
			List<Cat> total = new ArrayList<>(boat);
			total.addAll(bank);
			if(bigCatEatLittleCat(total))
				situations.remove(s);
		}
	}
	
	void connectAdjacentNodes(List<Situation> situations){
		
		for(Situation s : situations)
			for(Situation s1 : situations)
				if(s1 != s && Checker.adjacent(s1, s))
					s.getAdjacentNodes().add(s1);
	}
	
	void clearIsolatedNodes(List<Situation> situations){
		
		for(Situation s : new ArrayList<>(situations))
			if(s.getAdjacentNodes().size() == 0)
				situations.remove(s);
	}
	
	List<Cat> groupedCats(int[] arr, List<Cat> totalCats){
		
		List<Cat> cats = new ArrayList<>();
		
		for(int a : arr)
			cats.add(totalCats.get(a));
		
		return cats;
	}
	
	boolean cannotRow(List<Cat> cats){
		
		String name1 = cats.get(0).toString();
		String name2 = cats.get(1).toString();
		
		if("b".equals(name1) && "c".equals(name2) || "b".equals(name2) && "c".equals(name1))
			return true;
		
		return false;
	}
	
	boolean bigCatEatLittleCat(List<Cat> cats){
		
		List<Cat> littleCats = new ArrayList<>();
		
		for(Cat c : cats)
			if(Character.isLowerCase(c.toString().charAt(0)))
				littleCats.add(c);
		
		if(littleCats.size() == 0)
			return false;
		
		for(Cat littleCat : littleCats)
			if(momIsNotHere(littleCat,cats) && otherBigCatIsHere(cats))
				return true;
			
		return false;
	}
	
	boolean momIsNotHere(Cat littleCat, List<Cat> cats){
		
		char momName = Character.toUpperCase(littleCat.toString().charAt(0));
		
		for(Cat c : cats)
			if(momName == c.toString().charAt(0))
				return false;
		
		return true;
	}
	
	boolean otherBigCatIsHere(List<Cat> cats){
		
		for(Cat c : cats)
			if(Character.isUpperCase(c.toString().charAt(0)))
				return true;
		
		return false;
				
	}
}