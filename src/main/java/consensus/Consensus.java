package consensus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import overlap.Arc;
import overlap.Overlap;
import semiGlobal.Alignement;
import semiGlobal.Fragment;

public class Consensus {
	
	public static String consensus(Overlap graph, ArrayList<Arc> path){
		String result ="";
		
		ArrayList<Counter> consens = new ArrayList<Counter>();
		Fragment lastFrag = null;
		
		ArrayList<Fragment[]> alignements = new ArrayList<Fragment[]>();
		for(Arc arc : path){
			alignements.add(graph.computeAlignement(arc));
		}
		
		for(int i = 0; i < alignements.get(0)[0].length(); i++){
			consens.add(new Counter());
			consens.get(i).addNucl(alignements.get(0)[0].get(i));
		}
		int k = 0;
		int h = 0;
		for(Fragment[] fragments : alignements){
			/*fragments[0].setInShortForm();
			fragments[1].setInShortForm();*/
			h++;
			System.out.println("alignement n: " + h);
			if(lastFrag != null){
				/*if(lastFrag.getLeadingGap() < fragments[0].getLeadingGap()){
					int l = lastFrag.getLeadingGap();
					while(l < fragments[0].getLeadingGap()){
						consens.add(0, new Counter());
						l++;
					}
				}else if(fragments[0].getLeadingGap() < lastFrag.getLeadingGap()){
					int l = fragments[0].getLeadingGap();
					while(l < lastFrag.getLeadingGap()){
						fragments[0].insertGap(l);
						fragments[1].insertGap(l);
						l++;
					}
				}*/
				
				for(int i = 0; i < Math.min(lastFrag.length(), fragments[0].length()); i++){
					if(lastFrag.get(i) == 4 && fragments[0].get(i) != 4){
						fragments[0].insertGap(i);
						fragments[1].insertGap(i);
					}else if(lastFrag.get(i) != 4 && fragments[0].get(i) == 4){
						lastFrag.insertGap(i);
						consens.add(i, new Counter());
					}
				}
			}
			
			/*int init = fragments[1].getLeadingGap() + fragments[1].length();
			while(consens.size() < init){
				consens.add(new Counter());
			}
			for(int i = 0; i < fragments[1].length(); i++){
				consens.get(i + fragments[1].getLeadingGap()).addNucl(fragments[1].get(i));
			}*/
			
			int init = fragments[1].length();
			while(consens.size() < init){
				consens.add(new Counter());
			}
			for(int i = 0; i < fragments[1].length(); i++){
				consens.get(i + fragments[1].getLeadingGap()).addNucl(fragments[1].get(i));
			}
			
			lastFrag = fragments[1];
		}
		for(Counter c : consens){
			result += c.getMajority();
		}
		System.out.println(k);
		System.out.println(result.length());
		
		
		return result;
	}

}



