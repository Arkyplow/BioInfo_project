package overlap;

import semiGlobal.Alignement;
import semiGlobal.ArcBuilder;
import semiGlobal.Fragment;

import java.io.*;
import java.util.ArrayList;


/**
 * @author Jannou Brohee
 */
public class Overlap {

    private ArrayList<Sommet> sommets = new ArrayList<Sommet>();
    private ArrayList<Arc> arcs = new ArrayList<Arc>();

	/**
	 * Constructeur
	 */
	public Overlap(){}

	/**
	 * Permet d ajouter un arc au graph
	 * @param arc larc a ajouter
	 */
	public void addArc(Arc arc){
		arcs.add(arc);
		int i = arcs.size()-1;
		while(i>0 && arcs.get(i/2).getScore()<arcs.get(i).getScore()){
			Arc tmp = arcs.get(i/2);
			arcs.set(i/2,arcs.get(i));
			arcs.set(i,tmp);
			i=i/2;
		}
	}
	public void setArcs(ArrayList<Arc> _arcs){
		arcs=_arcs;
	}
	/**
	 * Permet d ajouter un sommet au graph
	 * @param s le sommet a ajouter
	 */
	public void addSommet(Sommet s ){
		sommets.add(s);
	}

	/**
	 * Permet de recuperer l arc d indice "index"
	 * @param index l 'indice de l arc
	 * @return L arc d indice "index"
	 */
	public Arc getArc(int index){
		return arcs.get(index);
	}

	/**
	 * Permet de recuperer l ensemble des arcs du graph
	 * @return L ensemble des arcs du graph
	 */
	public ArrayList<Arc> getArcs(){
		return arcs;
	}

	/**
	 * Permet de recuperer le sommet d indice "index"
	 * @param index l 'indice du sommet
	 * @return Le sommet d indice "index"
	 */
	public Sommet getSommet(int index){
		return sommets.get(index);
	}

	/**
	 * Permet de recuperer l ensemble des sommets du graph
	 * @return L ensemble des sommets du graph
	 */
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	/**
	 * Permet de construire un graph pour les fragments contenu dans le fichier "filename"
	 * @param filename le nom du fichier
	 * @return Un graph pour les fragments contenu dans le fichier "filename"
	 */
	public static Overlap build(String filename){
		Overlap retour = new Overlap();
		Alignement ali = new Alignement();
		try{
			File f = new File (filename);
			FileReader fr = new FileReader (f);
			BufferedReader br = new BufferedReader (fr);
			try
			{
				String line = br.readLine();
				String buff ="";
				while (line != null){
					if(!line.isEmpty()){
						if(line.contains(">") && !buff.isEmpty()){
							retour.addSommet(new Sommet(new Fragment(buff)));
							buff = "";
						}
						else if(!line.contains(">"))
							buff += line;
					}
					line = br.readLine();
				}
				retour.addSommet(new Sommet(new Fragment(buff)));

				br.close();
				fr.close();
			}
			catch (IOException exception)
			{
				System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
			}
		}catch (FileNotFoundException exception){
			System.out.println ("Le fichier n'a pas été trouvé");
		}

		return retour;
	}
	public void run(){
		new ArcBuilder().build(this);
		HeapSort.Sort(this, true);
	}

	/**
	 * Calcule l alignement represente par un arc du graph
	 * @param c l arc dont on veut recuperer l alignement
	 * @return Un tableau de String de taille 2 ou le premier element represente G et le deuxieme element represente T, null si il y a eu un probleme
	 */
	public String[] computeAlignement(Arc c){
		Fragment G ;
		Fragment T ;
		Alignement ali = new Alignement();
		if(c.getSrcC())
			G = getSommet(c.getSource()).getComplementaire();
		else
			G = getSommet(c.getSource()).getFrag();
		if(c.getDstC())
			T = getSommet(c.getDestination()).getComplementaire();
		else
			T = getSommet(c.getDestination()).getFrag();
		if(!(G==null && T ==null))
			return ali.aligne(G,T,c.getI(),c.getJ());
		return null;
	}
}
