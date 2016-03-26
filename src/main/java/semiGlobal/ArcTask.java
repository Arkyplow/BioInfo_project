package semiGlobal;

import overlap.Arc;
import overlap.Overlap;

import java.util.Queue;

/**
 * tache pour calcule les alignement entre un sommet T et tout les autres sommmet avec les quelle le sommet T n as pas encore ete compare
 * @author Jannou Brohee
 */
public class ArcTask {
	int sommetIndex;
	Overlap graph;
	Alignement al;
	ComputeThread thread;
	Queue<Arc> topush;

	/**
	 * Constructeur
	 * @param _sommetIndex l index du sommet T
	 * @param _graph le graph
	 * @param _al l alignement
	 */
	public ArcTask(int _sommetIndex, Overlap _graph, Alignement _al,ComputeThread _thread,Queue<Arc> _topush){
		topush=_topush;
		sommetIndex = _sommetIndex;
		al = _al;
		graph = _graph;
		thread=_thread;
	}

	/**
	 * Lancer la tache
	 */
	public void run(){
		int[] score;
		for(int i = sommetIndex+1 ;i<graph.getSommets().size();i++){
			score = al.getBestScores(graph.getSommet(sommetIndex).getFrag(), graph.getSommet(i).getFrag());
			topush.add(new Arc(i, sommetIndex ,false, false, score[0],score[2],score[3]));
			topush.add(new Arc(sommetIndex , i, false, false, score[1],score[4],score[5]));
			score = al.getBestScores(graph.getSommet(sommetIndex).getFrag(), graph.getSommet(i).getFrag().getComplementaire());
			topush.add( new Arc(i, sommetIndex ,false, true, score[0],score[2],score[3]));
			topush.add( new Arc(sommetIndex , i, true, false,score[1],score[4],score[5]));
			score = al.getBestScores(graph.getSommet(sommetIndex).getFrag().getComplementaire(), graph.getSommet(i).getFrag());
			topush.add( new Arc(i, sommetIndex ,true, false, score[0],score[2],score[3]));
			topush.add( new Arc(sommetIndex , i, false, true,score[1],score[4],score[5]));
			score = al.getBestScores(graph.getSommet(sommetIndex).getFrag().getComplementaire(), graph.getSommet(i).getFrag().getComplementaire());
			topush.add( new Arc(i, sommetIndex ,true,true, score[0],score[2],score[3]));
			topush.add( new Arc(sommetIndex , i, true,true ,score[1],score[4],score[5]));
		}
		upp();
	}
	private void upp(){
		thread.upp();
	}

}
