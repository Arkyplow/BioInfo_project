package consensus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import overlap.Arc;
import overlap.Overlap;
import semiGlobal.Alignement;
import semiGlobal.Fragment;

/**
 * Classe s'occupant de réaliser le consensus final du problème
 * en respectant les "gaps" calculés lors de l'alignement et en
 * propageant ceux-ci (dans les deux sens) lors du calcul de la
 * super-chaîne finale
 * 
 * @author santorin
 *
 */
public class Consensus {
	
	private Overlap graph;
	private ArrayList<Arc> path;

	/**
	 * Constructeur de l'objet Consensus
	 * @param graph le graphe de chevauchement du problème
	 * @param path le chemin hamiltonien représentant la super chaîne optimale
	 */
	public Consensus(Overlap graph, ArrayList<Arc> path){
		this.graph = graph;
		this.path = path;
	}
	
	/**
	 * Effectue le consensus du problème, en propageant les "gaps"
	 * lorsque deux itérations d'un même fragment sont différentes
	 * 
	 * @return le consensus final sous forme de Fragment
	 */
	public Fragment consensus(){
		
		ArrayList<Counter> consens = new ArrayList<Counter>();
		Fragment lastFrag = null;
		
		ArrayList<Fragment[]> alignements = new ArrayList<Fragment[]>();
		//on calcule les alignements
		for(Arc arc : path){
			alignements.add(graph.computeAlignement(arc));
		}
		
		//on ajoute le premier fragment au vote de majorité du consensus
		//tel quel
		for(int i = 0; i < alignements.get(0)[0].length(); i++){
			consens.add(new Counter());
			consens.get(i).addNucl(alignements.get(0)[0].get(i));
		}
		
		int h = 0;
		for(Fragment[] fragments : alignements){
			fragments[0].setInShortForm();
			fragments[1].setInShortForm();

			if(lastFrag != null){
				int l = lastFrag.getLeadingGaps() - fragments[0].getLeadingGaps();
				if(l > 0){
					while(l > 0){
						fragments[0].insertGap(lastFrag.getLeadingGaps() - l);
						fragments[1].insertGap(lastFrag.getLeadingGaps() - l);
						l--;
					}
				}else if(l > 0){
					l = -l;
					while(l > 0){
						consens.add(fragments[0].getLeadingGaps() - l, new Counter());
						l--;
					}
				}
				l = lastFrag.getTrailingGaps() - fragments[0].getTrailingGaps();
				if(l > 0){
					while(l > 0){
						int size = fragments[0].length();
						fragments[0].insertGap(fragments[0].getLeadingGaps() + size);
						fragments[1].insertGap(fragments[0].getLeadingGaps() + size);
						l--;
					}
				}else if(l < 0){
					l = -l;
					while(l > 0){
						consens.add(lastFrag.length() + lastFrag.getLeadingGaps(), new Counter());
						l--;
					}
				}
				//on compare le fragment courrant à sa dernière itération pour
				//propager les "gaps" dans un sens ou/et dans l'autre
				for(int i = 0; i < Math.min(lastFrag.length(), fragments[0].length()); i++){
					if(lastFrag.get(i) == 4 && fragments[0].get(i) != 4){
						fragments[0].insertGap(i + lastFrag.getLeadingGaps());
						fragments[1].insertGap(i + lastFrag.getLeadingGaps());
					}else if(lastFrag.get(i) != 4 && fragments[0].get(i) == 4){
						lastFrag.insertGap(i + fragments[0].getLeadingGaps());
						consens.add(i + fragments[0].getLeadingGaps(), new Counter());
					}
				}
			}
			
			//on s'assure que la taille du vote de  majorité est suffisante
			//pour acceuillir le nouveau fragment
			int init = fragments[1].length() + fragments[1].getLeadingGaps();
			while(consens.size() < init){
				consens.add(new Counter());
			}
			
			//on ajoute le fragment au vote
			for(int i = 0; i < fragments[1].length(); i++){
				consens.get(i + fragments[1].getLeadingGaps()).addNucl(fragments[1].get(i));
			}
			
			lastFrag = fragments[1];
		}
		
		byte[] result = new byte[consens.size()];
		//on effectue le vote pour chaque cas
		for(int i=0; i < consens.size(); i++){
			result[i] = consens.get(i).getMajority();
		}
		
		return new Fragment(result);
	}
	
	/**
	 * Print le résultat du consensus, au format souhaité,
	 * dans le fichier passé en paramètre
	 * @param f le fichier
	 * @param cons le resultat du consensus
	 */
	private void printConsensusInFile(File f, String cons){
		try {
			f.getParentFile().mkdirs();
			f.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			pw.println("> Groupe-12 Collection 1 Longueur " + cons.length());
			for(int i = 0; i < cons.length(); i++){
				if( i % 79 == 78){
					pw.print("\n");
				}
				pw.print(cons.charAt(i));
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit le resultat du consensus et son complémentaire inversé
	 * dans les fichiers passés en paramètre
	 * @param f fichier où écrire le résultat
	 * @param fileComp fichier où écrire le complémentaire inversé du résultat
	 */
	public void printConsensusInFiles(File f, File fileComp){
		Fragment frag = consensus();
		printConsensusInFile(f, frag.toString());
		printConsensusInFile(fileComp, frag.getComplementaire().toString());
	}

}



