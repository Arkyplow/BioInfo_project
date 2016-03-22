package overlap;

import semiGlobal.fragment.Fragment;

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
    public Sommet(Fragment _frag){
        frag = _frag;
    }
    public Fragment getComplementaire(){
        return frag.getComplementaire();
    }


	public Fragment getFrag() {
		return frag;
	}
	public void lockOut(boolean lockComplementaire){
		if(lockComplementaire){ // on a choisi le fragment complementaire, on doit donc verouiller les in et out du fragment initial
			out = outC = in = true;
		}
		else {  // on a choisi le fragment initial, on doit donc verouiller les in et out du fragment complementaire
			out = outC = inC = true;
		}
	}
	public void lockIn( boolean lockComplementaire ){
		if(lockComplementaire) // on a choisi le fragment complementaire, on  verouille inC
			inC=true;
		else // on a choisi le fragment initial, on  verouille in
			in = true;
	}
	public boolean getLockIn(boolean complementaire){
		if(complementaire) // on a choisi le fragment complementaire, on donne inC
			return inC;
		return in; // sinon on donne in
	}
	public boolean getLockOut(boolean complementaire){
		if(complementaire) // on a choisi le fragment complementaire, on donne outC
			return outC;
		return out; // sinon on donne out
	}
	public String toString(){
		return frag.toString();
	}
}
