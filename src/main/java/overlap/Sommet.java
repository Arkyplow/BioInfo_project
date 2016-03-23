package overlap;

import semiGlobal.Fragment;

/**
 * Permet de représenter un sommet d'un graph
 * @author Jannou Brohee
 */
public class Sommet {
    private Fragment frag;
    private boolean out ;
	private boolean outC ;
	private boolean in;
	private boolean inC = out = outC = in =  false;

	/**
	 * Constructeur de sommet
	 * @param _frag le Fragment representer par le sommet
	 */
    public Sommet(Fragment _frag){
        frag = _frag;
    }

	/**
	 * Permet de recuperer le Fragment complementaire
	 * @return Le Fragment complementaire
	 */
    public Fragment getComplementaire(){
        return frag.getComplementaire();
    }

	/**
	 * Permet de recuperer le Fragment initial
	 * @return Le Fragment initial
	 */
	public Fragment getFrag() {
		return frag;
	}

	/**
	 * Permet de verouiller les sorties du sommet ( pour le chemin Hamiltonien)
	 * @param lockComplementaire true si on a choisi le Fragment complementaire dans le chemoin false sinon
	 */
	public void lockOut(boolean lockComplementaire){
		if(lockComplementaire){ // on a choisi le fragment complementaire, on doit donc verouiller les in et out du fragment initial
			out = outC = in = true;
		}
		else {  // on a choisi le fragment initial, on doit donc verouiller les in et out du fragment complementaire
			out = outC = inC = true;
		}
	}

	/**
	 * Permet de verouiller les entrees du sommet ( pour le chemin Hamiltonien)
	 * @param lockComplementaire true si on rentre dans le fragment complementaire dans le chemin false sinon
	 */
	public void lockIn( boolean lockComplementaire ){
		if(lockComplementaire) // on a choisi le fragment complementaire, on  verouille inC
			inC=true;
		else // on a choisi le fragment initial, on  verouille in
			in = true;
	}

	/**
	 * Permet de recuperer l etat d un verrou d entree
	 * @param complementaire true si on veut l etat du verrou pour le complementaire false sinon
	 * @return L etat d un verrou d entree
	 */
	public boolean getLockIn(boolean complementaire){
		if(complementaire) // on a choisi le fragment complementaire, on donne inC
			return inC;
		return in; // sinon on donne in
	}
	/**
	 * Permet de recuperer l etat d un verrou de sortie
	 * @param complementaire true si on veut l etat du verrou pour le complementaire false sinon
	 * @return L etat d un verrou de sortie
	 */
	public boolean getLockOut(boolean complementaire){
		if(complementaire) // on a choisi le fragment complementaire, on donne outC
			return outC;
		return out; // sinon on donne out
	}

	/**
	 * toString()
	 * @return toString()
	 */
	public String toString(){
		return frag.toString();
	}
}
