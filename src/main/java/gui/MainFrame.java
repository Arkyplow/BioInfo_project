package gui;

import overlap.Arc;
import overlap.HamiltonPath;
import overlap.Overlap;
import semiGlobal.Alignement;
import semiGlobal.Fragment;

import javax.swing.*;
import java.util.ArrayList;

/**
 * fenetre principale
 * @author Jannou Brohee
 */
public class MainFrame {

	/**
	 *
	 * @param args parametre min fenetre principale
     */
	public static void main(String[] args) {
		Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/test.fasta");
		Fragment T = new Fragment("ATCGGCATTCAGT");//.getComplementaire();//frags.get(0); //
		Fragment G = new Fragment("ATTAGACCATGCGGC");//.getComplementaire();//frags.get(1); //
		Alignement ali = new Alignement();
		int[][] test = ali.matriceSim(G,T);
		System.out.println("matrice init - init ");
		for(int k =0; k<=G.length();k++){
			for(int j =0;j<=T.length();j++){
				if(k==0 && j!=0)
					System.out.print(T.get(j-1)+" , ");
				else if(k!=0 && j==0)
					System.out.print(G.get(k-1)+" , ");
				else
					System.out.print(test[k][j]+" , ");
			}
			System.out.print("\n");
		}
		for(Arc c : graph.getArcs()){
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(G.toString()) && !c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(T.toString()) &&! c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(T.toString()) &&! c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(G.toString()) && !c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
		}
		System.out.println();
		int[][] test2 = ali.matriceSim(G,T.getComplementaire());
		System.out.println("matrice init - compl ");
		for(int k =0; k<=G.length();k++){
			for(int j =0;j<=T.length();j++){
				if(k==0 && j!=0)
					System.out.print(T.getComplementaire().get(j-1)+" , ");
				else if(k!=0 && j==0)
					System.out.print(G.get(k-1)+" , ");
				else
					System.out.print(test2[k][j]+" , ");
			}
			System.out.print("\n");
		}
		for(Arc c : graph.getArcs()){
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(G.toString()) && !c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(T.toString()) && c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(T.toString()) && c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(G.toString()) && !c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
		}
		int[][] test3 = ali.matriceSim(G.getComplementaire(),T);
		System.out.println("matrice compl - init ");
		for(int k =0; k<=G.length();k++){
			for(int j =0;j<=T.length();j++){
				if(k==0 && j!=0)
					System.out.print(T.get(j-1)+" , ");
				else if(k!=0 && j==0)
					System.out.print(G.getComplementaire().get(k-1)+" , ");
				else
					System.out.print(test3[k][j]+" , ");
			}
			System.out.print("\n");
		}
		for(Arc c : graph.getArcs()){
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(G.toString()) && c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(T.toString()) && !c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(T.toString()) && !c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(G.toString()) && c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
		}
		int[][] test4 = ali.matriceSim(G.getComplementaire(),T.getComplementaire());
		System.out.println("matrice compl - complet ");
		for(int k =0; k<=G.length();k++){
			for(int j =0;j<=T.length();j++){
				if(k==0 && j!=0)
					System.out.print(T.getComplementaire().get(j-1)+" , ");
				else if(k!=0 && j==0)
					System.out.print(G.getComplementaire().get(k-1)+" , ");
				else
					System.out.print(test4[k][j]+" , ");
			}
			System.out.print("\n");
		}
		for(Arc c : graph.getArcs()){
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(G.toString()) && c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(T.toString()) && c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
			if((graph.getSommet(c.getSource()).getFrag().toString().equals(T.toString()) && c.getSrcC()) && (graph.getSommet(c.getDestination()).getFrag().toString().equals(G.toString()) && c.getDstC()))
				System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
		}
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");

		for(Arc c : graph.getArcs()){
			System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
		}
		int i =0;
		int j=0;
		int[] al = new Alignement().getBestScore(test,G,T);
		int[] sco = ali.getBestScores(G,T);
		System.out.println("what0 "+sco[0]);
		System.out.println("what1 "+sco[1]);
		System.out.println("==> best equals "+test[al[0]][al[1]]+ " at i="+al[0]+" & j="+al[1]);
		System.out.println("G = "+G+ " "+G.length());
		System.out.println("T = "+T+" "+T.length());
		System.out.println("\nAlignement de T sur G");
		String result[] = ali.aligne(G,T);
		System.out.println("G result : "+result[0] +" "+result[0].length());
		System.out.println("T result : "+result[1] +" "+result[1].length());
		System.out.println("\nAlignement de G sur T");
		result = ali.aligne(T,G);
		System.out.println("T result : "+result[0] +" "+result[1].length());
		System.out.println("G result : "+result[1] +" "+result[0].length());




		/*long debut = System.currentTimeMillis();
		//Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/collection1.fasta");
		Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/datas/Collections/10000/collection1.fasta");
		System.out.println("Bio-Info");
		System.out.println("temps calcule des alignements : "+(double)(System.currentTimeMillis()-debut)/1000+"s");
		//Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/test.fasta");
		int  n = graph.getSommets().size();
		System.out.println("nombre de fragments d'ADN  n = "+n);
		System.out.println("# alignements a calculer => (n*(n-1))*4 = "+(n*(n-1))*4);
		System.out.println("# alignements calcules : "+graph.getArcs().size());
		System.out.println("# alignements manquants  : " +(((n*(n-1))*4)- graph.getArcs().size() ));
		Arc test = graph.getArc(0);
		Fragment f1 ;//= test.getSource().getFrag();
		Fragment f2 = graph.getSommet(test.getDestination()).getFrag();*/

		//test.getSrcC() ? f1= graph.getSommet(test.getSource()).getComplementaire() : f1 = graph.getSommet(test.getSource()).getFrag();
		/*for(Arc c : graph.getArcs()){
			//if(c.getSource()==0)
			//System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
			//System.out.println(c.getScore());
		}*/
		//System.out.println("S = "+graph.getSommet(0).getComplementaire()+ " "+graph.getSommet(0).getComplementaire().length());
		//System.out.println("T = "+graph.getSommet(1).getFrag()+" "+graph.getSommet(1).getFrag().length());
		//Fragment result = new Alignement().aligne(graph.getSommet(0).getComplementaire(),graph.getSommet(1).getFrag());
		//System.out.println("result : "+result +" "+result.length());
		//initializeMain();
		ArrayList<Arc> arcs = HamiltonPath.greedy(graph);
		for(Arc arc : arcs){
			System.out.print(arc.getSource() + " - " + arc.getDestination() + " - ");
		}
		System.out.println("\n" + arcs.size());
	}

	/**
	 * initialisation fenetre principale
	 */
	private static void initializeMain() {
		JFrame frame = new JFrame("BioInformatique");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,600);
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
