package semiGlobal;

import overlap.Arc;
import overlap.Overlap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * permet de construire les arc entre un Sommet et tout les autres Sommet deja present dans le graph // on peut utiliser du mutli thread
 * @author Jannou Brohee
 */
public class ArcBuilder {
	public void build( Overlap graph){

		// alignement semi-global
		Alignement al = new Alignement();
		// # de cors sur la machine
		int job =Runtime.getRuntime().availableProcessors();
		// tableau de job thread
		ComputeThread[] jobs = new ComputeThread[job];
		Queue<Arc>[] topush = new LinkedList[job];
		//compteur pour répartir les tache sur les threads
		int count = 0;
		//initiliser le tableau
		for(int i = 0; i<job;i++){
			jobs[i]= new ComputeThread();
			topush[i]=new LinkedList<Arc>();
		}
		//assigner les taches aux threads
		for(int i = 0; i<graph.getSommets().size();i++){
			jobs[count%job].add(new ArcTask(i,graph,al,jobs[count%job],topush[count%job]));
			count+=1;
		}
		//lancer tous les threads en meme temps
		for(int i = 0; i<job;i++){
			jobs[i].start();
		}
		// attendre la fins des calcules ....
		try {
			for(int i = 0; i<job;i++){
				jobs[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i = 0; i<job;i++){
			while(!topush[i].isEmpty()){
				graph.addArc(topush[i].poll());
			}
		}
	}
}
