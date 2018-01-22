package p;

import java.util.ArrayList;
import java.util.List;

public class Situation {
	
	private List<Cat> boat;
	private List<Cat> leftBank;
	private List<Cat> rightBank;
	private boolean boatLeft;
	private List<Situation> adjacentNodes = new ArrayList<>();
	
	public Situation(List<Cat> boat, List<Cat> leftBank, List<Cat> rightBank, boolean boatLeft) {
		this.boat = boat;
		this.leftBank = leftBank;
		this.rightBank = rightBank;
		this.boatLeft = boatLeft;
	}
	
	public List<Cat> getBoat() {
		return boat;
	}

	public List<Cat> getLeftBank() {
		return leftBank;
	}

	public List<Cat> getRightBank() {
		return rightBank;
	}

	public boolean isBoatLeft() {
		return boatLeft;
	}

	public List<Situation> getAdjacentNodes(){
		return adjacentNodes;
	}
}