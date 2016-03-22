package semiGlobal;

import overlap.Arc;
import overlap.Overlap;

/**
 * permet de construire les arc entre un Sommet et tout les autres Sommet deja present dans le graph // on peut utiliser du mutli thread
 * @author Jannou Brohee
 */
public class ArcBuilder {
	public static void build(Alignement al, Overlap graph, int sommetIndex){
		int[] score;
		for(int i = 0 ;i<graph.getSommets().size();i++){
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
