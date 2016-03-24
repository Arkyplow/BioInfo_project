package overlap;

import java.util.ArrayList;

/**
 * @author Jannou Brohee
 */
public class HeapSort {

	private static Arc tmp;
	public static void Sort(Overlap _graph){
		ArrayList<Arc> arcs = new ArrayList<Arc>();
		/*
		while(!_graph.getArcs().isEmpty()){
			Arc print = getMax(_graph.getArcs());
			System.out.println(print.getScore());
			arcs.add(print);
		}*/
		int index=-1;
		int score = -1;
		while(!_graph.getArcs().isEmpty()){
			for(int k = 0; k<_graph.getArcs().size();k++) {
				if(score<_graph.getArc(k).getScore()){
					score = _graph.getArc(k).getScore();
					index = k;
				}
			}
			arcs.add(_graph.getArcs().remove(index));
			score = -1;
			index = -1;
		}
		_graph.setArcs(arcs);

	}
	private static void erasmus(ArrayList<Arc> toSort, int index1, int index2){
		tmp = toSort.get(index1);
		toSort.set(index1,toSort.get(index2));
		toSort.set(index2,tmp);
	}
	private static Arc getMax(ArrayList<Arc> toSort){
		Arc retour;
		if(toSort.size()>1) {
			retour = toSort.remove(0);
			toSort.add(0, toSort.remove(toSort.size() - 1));
			heapity(toSort, 0);
		}
		else
			retour = toSort.remove(0);
		return retour;
	}
	private static void heapity(ArrayList<Arc> toSort, int i){
		int left = 2*(i+1)-1;
		int right = 2*(i+1);
		int L ;
		if(left<toSort.size() && (toSort.get(left).getScore()>toSort.get(i).getScore()))
			L=left;
		else
			L= i ;

		if(right<toSort.size() && (toSort.get(right).getScore()>toSort.get(L).getScore()))
			L=right;
		if( L != i){
			//System.out.println(toSort.get(i).getScore()+"<"+toSort.get(L).getScore());
			erasmus(toSort,i,L);
			heapity(toSort, L);
			heapity(toSort, i);
			heapity(toSort, left);
			heapity(toSort, right);
		}
	}
}