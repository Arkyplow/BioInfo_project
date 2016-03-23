package semiGlobal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * thread de calcul des alignements
 * @author Jannou Brohee
 */


public class ComputeThread extends Thread{
	private final Queue<ArcTask> queue = new LinkedList<ArcTask>();
	private final Object empty = new Object();
	private int finish;

	/**
	 * Constructeur du thread de caclule
	 */
	public ComputeThread(){
	}
	synchronized public void run() {
		finish = queue.size();
		while (!queue.isEmpty()) {
			ArcTask task = queue.poll();
			task.run();
		}
		while(finish!=0){
		}
		notify();
	}

	/**
	 * ajouter une tache au thread
	 * @param arc tache
	 */
	public void add(ArcTask arc){
		queue.add(arc);
	}
	public void upp(){
		finish -=1;
	}
}
