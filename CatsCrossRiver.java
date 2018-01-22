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
		
		String JP = "abȥ, a��, acȥ, a��, BCȥ, Bb��, Aaȥ, Cc��, BCȥ, a��, abȥ, a��, acȥ";
		String MJ = "acȥ, a��, abȥ, a��, BCȥ, Cc��, Aaȥ, Bb��, BCȥ, a��, acȥ, a��, abȥ";
		
		System.out.println("è������������д����£�\n");
		
		for(String result : results){
			System.out.print( ++count + ": " + result );
			
			if(MJ.equals(result))
				System.out.print("\tMJ�Ĵ�");
			else if(JP.equals(result))
				System.out.print("\tJP�Ĵ�");
			
			System.out.println("\n");
		}
		
		System.out.println("��ʱ��"+(System.currentTimeMillis()-start)/1000+"��\n\n");
		
		sc.close();
	}
}