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
				return order(graph, result);
			}
		}
		return order(graph, result);
		
	}
	
	/**
	 * Réordonne les arcs donnés pour en faire un chemin valide
	 * @param graph Un overlap graph
	 * @param result une liste d'arcs de graph
	 */
	private static ArrayList<Arc> order(Overlap graph, ArrayList<Arc> result) {
		ArrayList<Arc> arcs = new ArrayList<Arc>();
		for(int i = 0; i < result.size(); i++){
			if(!graph.getSommet(result.get(i).getSource()).getLockIn(result.get(i).getSrcC())){
				arcs.add(result.get(i));
				result.remove(i);
				break;
			}
		}
		while(result.size() > 0){
			boolean found = false;
			int i = 0;
			while(!found){
				if(result.get(i).getSource() == arcs.get(arcs.size() - 1).getDestination() 
						&& result.get(i).getSrcC() == arcs.get(arcs.size() - 1).getDstC()){
					arcs.add(result.get(i));
					result.remove(i);
					found = true;
				}
				i++;
			}
		}
		return arcs;
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