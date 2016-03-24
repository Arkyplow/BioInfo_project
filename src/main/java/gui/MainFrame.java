package gui;

import overlap.Arc;
import overlap.Overlap;

import javax.swing.*;

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
		//ArrayList<Fragment> frags = FileHelper.load("/home/nanabaskint/Git/BioInfo_project/datas/Collections/10000/collection1.fasta");
		/*Fragment T = new Fragment("ATCGGCATTCAGT");//.getComplementaire();//frags.get(0); //
		Fragment S = new Fragment("ATTAGACCATGCGGC");//.getComplementaire();//frags.get(1); //
		Alignement ali = new Alignement();
		int[][] test = ali.matriceSim(S,T);
		for(int k =0; k<=S.length();k++){
			for(int j =0;j<=T.length();j++){
				System.out.print(test[k][j]+" , ");
			}
			System.out.print("\n");
		}
		int i =0;
		int j=0;
		int[] al = new Alignement().getBestScore(test,S,T);
		int[] sco = ali.getBestScores(S,T);
		System.out.println("what0 "+sco[0]);
		System.out.println("what1 "+sco[1]);
		System.out.println("==> best equals "+test[al[0]][al[1]]+ " at i="+al[0]+" & j="+al[1]);
		System.out.println("S = "+S+ " "+S.length());
		System.out.println("T = "+T+" "+T.length());
		Fragment result = ali.aligne(S,T);
		System.out.println("result : "+result +" "+result.length());*/



		long debut = System.currentTimeMillis();
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
		for(Arc c : graph.getArcs()){
			//if(c.getSource()==0)
			//System.out.println(c.getSource()+1+"(complementaire: "+c.getSrcC()+") --> "+(c.getDestination()+1)+"(complementaire: "+c.getDstC()+") score: "+c.getScore());
			System.out.println(c.getScore());
		}
		//System.out.println("S = "+graph.getSommet(0).getComplementaire()+ " "+graph.getSommet(0).getComplementaire().length());
		//System.out.println("T = "+graph.getSommet(1).getFrag()+" "+graph.getSommet(1).getFrag().length());
		//Fragment result = new Alignement().aligne(graph.getSommet(0).getComplementaire(),graph.getSommet(1).getFrag());
		//System.out.println("result : "+result +" "+result.length());
		//initializeMain();
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
