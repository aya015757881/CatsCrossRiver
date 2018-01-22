package p;

import java.util.Scanner;
import java.util.TreeSet;

public class CatsCrossRiver {
	
	public static void main(String[] args){
		
		int count;
		Scanner sc = new Scanner(System.in);
		Searcher s = new Searcher();
		
		count = 0;
		double start = System.currentTimeMillis();
		
		TreeSet<String> results = new TreeSet<String>(s.paths());
		
		String JP = "ab去, a回, ac去, a回, BC去, Bb回, Aa去, Cc回, BC去, a回, ab去, a回, ac去";
		String MJ = "ac去, a回, ab去, a回, BC去, Cc回, Aa去, Bb回, BC去, a回, ac去, a回, ab去";
		
		System.out.println("猫过河问题的所有答案如下：\n");
		
		for(String result : results){
			System.out.print( ++count + ": " + result );
			
			if(MJ.equals(result))
				System.out.print("\tMJ的答案");
			else if(JP.equals(result))
				System.out.print("\tJP的答案");
			
			System.out.println("\n");
		}
		
		System.out.println("耗时："+(System.currentTimeMillis()-start)/1000+"秒\n\n");
		
		sc.close();
	}
}