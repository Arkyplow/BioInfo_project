package gui;

import overlap.Arc;
import overlap.HamiltonPath;
import overlap.Overlap;

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
		/*Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/test.fasta");
		//Overlap graph = Overlap.build("/home/santorin/BioInfo_project/test/test.fasta");
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

		for(Arc c : graph.getArcs()){
			System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
		}

		int i =0;
		int j=0;
		int[] sco = ali.getBestScores(G,T);
		System.out.println("what0 "+sco[0]);
		System.out.println("what1 "+sco[1]);
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

*/


		long debut = System.currentTimeMillis();
		//Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/collection1.fasta");
		Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/datas/Collections/10000/collection1.fasta");
		ArrayList<Arc> arcs = HamiltonPath.greedy(graph);
		//Overlap graph = Overlap.build("/home/santorin/BioInfo_project/datas/Collections/10000/collection1.fasta");
		System.out.println("Bio-Info");
		System.out.println("temps calcule des alignements & recherche chemin : "+(double)(System.currentTimeMillis()-debut)/1000+"s");
		//Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/test.fasta");
		int  n = graph.getSommets().size();
		System.out.println("nombre de fragments d'ADN  n = "+n);
		System.out.println("# alignements a calculer => (n*(n-1))*4 = "+(n*(n-1))*4);
		System.out.println("# alignements calcules : "+graph.getArcs().size());
		System.out.println("# alignements manquants  : " +(((n*(n-1))*4)- graph.getArcs().size() ));
		for(Arc arc : arcs)
			System.out.print(arc.getSource() + " - " );
		System.out.println(arcs.get(129).getDestination());
		System.out.println("\n" + arcs.size());

		//initializeMain();


		//Arc por= null;
		/*for(Arc arc : arcs){
			System.out.print(arc.getSource() + " - " + arc.getDestination() + " - ");
			if(arc.getSource()==111)
				por = arc;
		}
		Fragment tes1;
		Fragment tes2;

		System.out.println("\n" + arcs.size());
		if(!por.getSrcC()){
			tes1=graph.getSommet(por.getSource()).getFrag();
		}
		else{
			tes1=graph.getSommet(por.getSource()).getComplementaire();
		}
		if(!por.getDstC()){
			tes2=graph.getSommet(por.getDestination()).getFrag();
		}
		else{
			tes2=graph.getSommet(por.getDestination()).getComplementaire();
		}
		//int[][] test = new Alignement().matriceSim(tes1,tes2);
		//System.out.println("matrice init - init ");
		//for(int j=1;j<=tes2.length();j++){
		//	System.out.print(test[tes1.length()][j]+" , ");
		//}System.out.println("\n");
		//for(int i=1;i<=tes1.length();i++){
		//	System.out.print(test[i][tes2.length()]+" , ");
		//}
		//System.out.println(tes1+" "+tes1.length());
		//System.out.println(tes2+" "+tes2.length());
		System.out.println("wtf "+por.getScore());
		String tmp2[] = graph.computeAlignement(por);
		System.out.println(tmp2[0] );
		System.out.println(tmp2[1]+"\n");
*/


		/*for(Arc arc : arcs){
			System.out.println(arc.getScore());
			String tmp[] = graph.computeAlignement(arc);
			System.out.println(tmp[0] );
			System.out.println(tmp[1]+"\n");
		}*/


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
