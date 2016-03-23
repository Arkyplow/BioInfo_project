package semiGlobal;

import overlap.Arc;
import overlap.Overlap;

/**
 * tache pour calcule les alignement entre un sommet T et tout les autres sommmet avec les quelle le sommet T n as pas encore ete compare
 * @author Jannou Brohee
 */
public class ArcTask {
	int sommetIndex;
	Overlap graph;
	Alignement al;

	/**
	 * Constructeur
	 * @param _sommetIndex l index du sommet T
	 * @param _graph le graph
	 * @param _al l alignement
	 */
	public ArcTask(int _sommetIndex, Overlap _graph, Alignement _al){
		sommetIndex = _sommetIndex;
		al = _al;
		graph = _graph;
	}

	/**
	 * Lancer la tache
	 */
	public void run(){
		int[] score;
		for(int i = sommetIndex ;i<graph.getSommets().size();i++){
			if(i!= sommetIndex){
				score = al.getBestScores(graph.getSommet(sommetIndex).getFrag(), graph.getSommet(i).getFrag());
				graph.addArc(new Arc(i, sommetIndex ,false, false, score[0]));
				graph.addArc(new Arc(sommetIndex , i, false, false, score[1]));
				score = al.getBestScores(graph.getSommet(sommetIndex).getFrag(), graph.getSommet(i).getFrag().getComplementaire());
				graph.addArc( new Arc(i, sommetIndex ,false, true, score[0]));
				graph.addArc( new Arc(sommetIndex , i, true, false,score[1]));
				score = al.getBestScores(graph.getSommet(sommetIndex).getFrag().getComplementaire(), graph.getSommet(i).getFrag());
				graph.addArc( new Arc(i, sommetIndex ,true, false, score[0]));
				graph.addArc( new Arc(sommetIndex , i, false, true,score[1]));
				score = al.getBestScores(graph.getSommet(sommetIndex).getFrag().getComplementaire(), graph.getSommet(i).getFrag().getComplementaire());
				graph.addArc( new Arc(i, sommetIndex ,true,true, score[0]));
				graph.addArc( new Arc(sommetIndex , i, true,true ,score[1]));
			}
		}
	}
}
