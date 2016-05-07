package gui;

import consensus.Consensus;
import overlap.Arc;
import overlap.HamiltonPath;
import overlap.Overlap;
import semiGlobal.Alignement;
import semiGlobal.Fragment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
/**
 * fenetre principale
 * @author Jannou Brohee
 */
public class MainFrame {
	private  static JPanel mainContentPane ;
	private static OpenFileWindow open;
	private static JTextArea log;
	private static JTextArea mFile;
	/**
	 *
	 * @param args parametre min fenetre principale
     */
	public static void main(String[] args) {
		if(args.length==5) {
			if( args[1].equals("--out") && args[3].equals("--out-ic")) {
				for (int i = 0; i < args.length; i++) {
					System.out.println(i + " " + args[i]);
				}
			}
			else{
				System.out.println("Verifiez que vous avez bien les \'--out\'  et \'--out-ic\' ");
			}
		}
		else if(args.length==1) {
			if( args[0].equals("-gui") )
				initializeMain();
		}
		else{
			System.out.println("Verifiez vos paramètres : FragmentAssembler.jar <fichier.fasta> --out <sortie.fasta> --out-ic <sortie-ic.fasta> \nPour lancer la version graphique : FragmentAssembler.jar -gui");
		}

		//initializeMain();

		//test();
		//MainFrame.run2(run1());
	}
	public static void test(){
		//Overlap graph = Overlap.build("/home/santorin/BioInfo_project/test/test2.fasta");
		Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/test.fasta");
		graph.run();
		graph.sort();
		ArrayList<Arc> arcs = HamiltonPath.greedy(graph);

		Fragment T = graph.getSommet(0).getFrag();//new Fragment("ATCGGCATTCAGT");//.getComplementaire();//frags.get(0); //
		Fragment G = graph.getSommet(1).getFrag();//new Fragment("ATTAGACCATGCGGC");//.getComplementaire();//frags.get(1); //
		Alignement ali = new Alignement();
		//System.out.println(arc.getSource() + " - " + arc.getDestination());

		for(Arc arc : arcs) {
			//Arc arc = arcs.get(0);
			System.out.println(arc.getSource() + " is compl : " + arc.getSrcC() + " --> " + arc.getDestination() + " is compl : " + arc.getDstC() + " score: " + arc.getScore());
			Fragment[] pr = graph.computeAlignement(arc);
			System.out.println(pr[0]+"\n"+pr[1]);
			/*if(arc.getSrcC()){
				if(arc.getDstC()){
					String[] al = ali.aligne(graph.getSommet(arc.getSource()).getComplementaire(),graph.getSommet(arc.getDestination()).getComplementaire());
					System.out.println(al[0]+"\n"+al[1]);
				}
				else {
					String[] al = ali.aligne(graph.getSommet(arc.getSource()).getComplementaire(),graph.getSommet(arc.getDestination()).getFrag());
					System.out.println(al[0]+"\n"+al[1]);
				}
			}
			else{
				if(arc.getDstC()){
					String[] al = ali.aligne(graph.getSommet(arc.getSource()).getFrag(),graph.getSommet(arc.getDestination()).getComplementaire());
					System.out.println(al[0]+"\n"+al[1]);
				}
				else {
					String[] al = ali.aligne(graph.getSommet(arc.getSource()).getFrag(),graph.getSommet(arc.getDestination()).getFrag());
					System.out.println(al[0]+"\n"+al[1]);
				}
			}*/


		}//System.out.println(arcs.get(arcs.size()-1).getDestination());

		int[] blabla = ali.getBestScores(T.getComplementaire(),G);
//		System.out.println("score : "+blabla[0]+" i :"+blabla[1]+" j :"+blabla[2]);
//		System.out.println("score : "+blabla[1]+" i :"+blabla[4]+" j :"+blabla[5]);
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
		Fragment[] al = ali.aligne(graph.getSommet(1).getFrag(),graph.getSommet(0).getComplementaire());
		System.out.println(al[0]+"\n"+al[1]);
		al = ali.aligne(graph.getSommet(1).getFrag(),graph.getSommet(0).getComplementaire());
		System.out.println(al[0]+"\n"+al[1]);



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
		System.out.println("matrice compl - compl ");
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
		/*int i =0;
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
		System.out.println("G result : "+result[1] +" "+result[0].length());*/

		//Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/collection1.fasta");




		//Overlap graph = Overlap.build("/home/santorin/BioInfo_project/datas/Collections/10000/collection1.fasta");
		//System.out.println("Bio-Info");
		//Overlap graph = Overlap.build("/home/nanabaskint/Git/BioInfo_project/test/test.fasta");



	}
	/**
	 * initialisation fenetre principale
	 */
	private static void initializeMain() {
		final JFrame frame = new JFrame("BioInformatique");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,600);
		frame.setResizable(false);
		mainContentPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		mainContentPane.setLayout(gridbag);
		open = new OpenFileWindow();
		ChoseBar cbar = new ChoseBar(open);
		log = new JTextArea("log", 30,1);
		log.setBackground(Color.BLACK);
		//log.setDisabledTextColor(Color.white);
		log.setForeground(Color.white);
		log.setEditable(false);

		JButton button2 = new JButton("Start ");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread(){
					@Override
					public void run() {
						MainFrame.run2(false ,run1(false,null),null,null);
					}
				};
				if(open.getPath() != null)
					t.start();
				else {
					JOptionPane.showMessageDialog(frame, "Please select a file ", "WARNING", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;

		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		gridbag.setConstraints(cbar, c);
		mainContentPane.add(cbar);

		c.weightx = 0.0;
		gridbag.setConstraints(button2, c);
		mainContentPane.add(button2);
		c.gridwidth = GridBagConstraints.REMAINDER; //end row
		c.gridheight = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(log, c);
		mainContentPane.add(log);
		frame.setContentPane(mainContentPane);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void log(String _log){
		log.insert(_log,log.getText().length());
	}

	public static Overlap run1(boolean term , String input){
		String logg = "\n";
		Overlap graph;
		if(term)
			graph = Overlap.build(input);
		else
			graph = Overlap.build(open.getPath());
		int  n = graph.getSommets().size();
		logg+=" \n"+"> # fragments d'ADN  n = "+n;
		logg+=" \n"+"> # alignements a calculer => (n*(n-1))*4 = "+(n*(n-1))*4;
		logg+="\n> # alignements à prendre theorie : "+(n-1);
		logg+="\n------------------------------------------------";
		if(term)
			System.out.println(logg);
		else
			log(logg);
		return graph;
	}
	public static void run2(boolean term,Overlap graph,String output1, String output2){
		int  n = graph.getSommets().size();
		String logg = "";
		long debut = System.currentTimeMillis();
		long debut2 = System.currentTimeMillis();
		graph.run();
		logg+="\n Temps calcule des alignements : "+(double)(System.currentTimeMillis()-debut)/1000+"s";
		logg+="\n> # alignements manquants  : " +(((n*(n-1))*4)- graph.getArcs().size() );
		if(term)
			System.out.println(logg);
		else
			log(logg);
		logg = "";
		//for(Arc c : graph.getArcs()){
	//		System.out.println(" score: "+c.getScore());
	//	}
		debut = System.currentTimeMillis();
		graph.sort();

		System.out.println(" score: ");
		/*for(Arc c : graph.getArcs()){
			System.out.println(" score: "+c.getScore());
		}*/
		logg+="\n Temps calcul trie des alignements : "+(double)(System.currentTimeMillis()-debut)/1000+"s";
		if(term)
			System.out.println(logg);
		else
			log(logg);
		logg = "";
		debut = System.currentTimeMillis();
		ArrayList<Arc> arcs = HamiltonPath.greedy(graph);
		logg+="\n Temps calcul chemin Hamiltonien  : "+(double)(System.currentTimeMillis()-debut)/1000+"s";
		logg+="\n Temps total : "+(double)(System.currentTimeMillis()-debut2)/1000+"s";
		if(term)
			System.out.println(logg);
		else
			log(logg);
		logg = "";
		/*for(Arc arc : arcs)
			System.out.print(arc.getSource() + " - " );
		System.out.println(arcs.get(arcs.size()-1).getDestination());*/
		logg+="\n> # alignements selectionés : "+ arcs.size();
		//System.out.println(logg);
		if(term)
			System.out.println(logg);
		else
			log(logg);
		
		//on crée un objet consensus
		Consensus c = new Consensus(graph, arcs);
		//voila le résultat

		String cons = c.consensus();
		System.out.println(cons.length());

		System.out.println(cons);
		//on a aussi une méthode pour formater le résultat dans un fichier
		//sauver consensus init
		if(output1!=null)
			c.printConsensusInFile(new File(output1));
		//sauver consensus complementaire
		if(output2!=null)
			c.printConsensusInFile(new File(output2));
		
	}
}
