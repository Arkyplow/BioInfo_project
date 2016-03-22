package overlap;

import semiGlobal.Alignement;
import semiGlobal.ArcBuilder;
import semiGlobal.fragment.Fragment;

import java.io.*;
import java.util.ArrayList;


/**
 * @author Jannou Brohee
 */
public class Overlap {

    private ArrayList<Sommet> sommets = new ArrayList<Sommet>();
    private ArrayList<Arc> arcs = new ArrayList<Arc>();
    public Overlap(){}
	public void addArc(Arc arc){
		arcs.add(arc);
	}
    public void addSommet(Sommet s ){
        sommets.add(s);
    }
    public static Overlap build(String filename){
		Overlap retour = new Overlap();
		Alignement ali = new Alignement();
		int index = -1;
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
							ArcBuilder.build(ali,retour,(index+=1));
							buff = "";
						}
						else if(!line.contains(">")){
							buff += line;
						}
					}
					line = br.readLine();
				}
				retour.addSommet(new Sommet(new Fragment(buff)));
				ArcBuilder.build(ali,retour,(index+=1));
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
	public Arc getArc(int index){
		return arcs.get(index);
	}
	public ArrayList<Arc> getArcs(){
		return arcs;
	}
	public Sommet getSommet(int index){
		return sommets.get(index);
	}
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}
}
