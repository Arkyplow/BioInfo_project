package overlap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * permet de trouver un chemin hamiltonnien dans un graphe
 * @author Santorin Ledru
 */
public class  HamiltonPath{
	
	/**
	 * Trouve un chemin hamiltonien par une approche greedy
	 * @param graph Un overlap graph
	 */
	public static ArrayList<Arc> greedy(Overlap graph){
		ArrayList<Arc> result = new ArrayList<Arc>();
		
		ArrayList<Set<Integer>> sets = new ArrayList<Set<Integer>>();
		for(int i=0; i < graph.getSommets().size(); i++){
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(i);
			sets.add(set);
		}
		
		for(Arc arc : graph.getArcs()){
			Sommet out = graph.getSommet(arc.getSource());
			Sommet in = graph.getSommet(arc.getDestination());
			if(!in.getLockIn(arc.getDstC()) && !out.getLockOut(arc.getSrcC())){
				Set<Integer> inSet = findSet(sets, arc.getDestination());
				Set<Integer> outSet = findSet(sets, arc.getSource());
				if(!inSet.equals(outSet)){
					result.add(arc);
					out.lockOut(arc.getSrcC());
					in.lockIn(arc.getDstC());
					inSet.addAll(outSet);
					sets.remove(outSet);
				}
			}
			if(sets.size() == 1){
				return result;
			}
		}
		return result;
		
	}
	
	/**
	 * Trouve l'ensemble qui contient l'indice donné
	 * @param sets ensemble de tous les ensembles
	 * @param i l'indice du sommet recherché
	 */
	public static Set<Integer> findSet(ArrayList<Set<Integer>> sets, int i){
		for(Set set : sets){
			if(set.contains(i)){
				return set;
			}
		}
		return null;
	}
	
}