package p;

import java.util.List;

public interface Occasion {
	
	boolean prevented(List<Cat> cats);
	
	void goOn(List<Cat> cats);
}