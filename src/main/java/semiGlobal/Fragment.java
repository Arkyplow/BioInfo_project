package semiGlobal;

/**
 * representation d'un fragment
 * @author Jannou Brohee
 */
public class Fragment {
	private byte[] frag;

	/**
	 * Constructeur d'un fragment
	 * @param _frag le fragment a représenter
	 */
	public Fragment(byte[] _frag){
		frag = _frag;
	}

	/**
	 * Donne le caractère a l'indice i du fragment
	 * @param i L'indice
	 * @return Le caractère a l'indice i du fragment
	 */
	public byte get(int i) {
		return frag[i];
	}


	/**
	 * Donne la longueur du fragment
	 * @return La longueur du fragment
	 */
	public int length() {
		return frag.length;
	}

	/**
	 * calcule le complementaire inversé d'un fragment
	 * @return le complementaire inversé d'un fragment
	 */
	public Fragment getComplementaire(){
		byte ret [] = new byte[frag.length];
		for (int i = frag.length-1;i>=0;i--){
			switch(frag[i]){
				case 0:
					ret[ret.length-(i+1)] = 3;
					break;
				case 1:
					ret[ret.length-(i+1)] = 2;
					break;
				case 2:
					ret[ret.length-(i+1)] = 1;
					break;
				case 3:
					ret[ret.length-(i+1)] = 0;
					break;
				case 4:
					ret[ret.length-(i+1)] = 4;
					break;
			}
		}
		return new Fragment(ret);
	}
	
	/**
	 * Permet d'insérer un gap à l'indice i du fragment 
	 * en décalant le reste du fragment
	 * 
	 * @param i l'indice où insérer le gap
	 */
	public void insertGap(int i){
		byte[] newFrag = new byte[frag.length + 1];
		for(int j = 0; j < newFrag.length; j++){
			if(i == j){
				newFrag[j] = 4;
			}else if(j < i){
				newFrag[j] = frag[j];
			}else{
				newFrag[j] = frag[j-1];
			}
		}
		frag = newFrag;
	}

	/**
	 * Permet de  recuperer la partie du fragment commencant a l indice i
	 * @param i l indice
	 * @return la partie du fragment commencant a l indice i
	 */
	public String substring(int i){
		String ret ="";
		for (int _i = i;_i<frag.length;_i++){
			switch(frag[_i]){
				case 0:
					ret+= "a";
					break;
				case 1:
					ret+= "c";
					break;
				case 2:
					ret+= "g";
					break;
				case 3:
					ret+= "t";
					break;
				case 4:
					ret+= "_";
					break;
			}
		}
		return ret;
	}

	/**
	 * toString()
	 * @return toString()
	 */
	public String toString() {
		String ret ="";
		for (int i = 0;i<frag.length;i++){
			switch(frag[i]){
				case 0:
					ret+= "a";
					break;
				case 1:
					ret+= "c";
					break;
				case 2:
					ret+= "g";
					break;
				case 3:
					ret+= "t";
					break;
				case 4:
					ret+= "_";
					break;
			}
		}
		return ret;
	}
}
