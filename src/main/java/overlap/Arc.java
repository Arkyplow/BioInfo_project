package overlap;

/**
 * permet de représenter un arc d'un graph orienté
 * @author Jannou Brohee
 */
public class Arc {
    private int indexSommetSrc;
    private int indexSommetDst;
    private int score;
	private int i;
	private int j;
    private boolean srcC= false;
    private boolean dstC = false;

	/**
	 * Constructeur
	 * @param _indexSommetSrc index du sommet source
	 * @param _indexSommetDst index du sommet destination
	 * @param _srcC true si on prends le complementaire de la source
	 * @param _dstC true si on prends le complementaire de la source
	 * @param _score le score de l alignement
	 */
    public Arc(int _indexSommetSrc, int _indexSommetDst, boolean _srcC, boolean _dstC, int _score,int _i,int _j){
        indexSommetSrc = _indexSommetSrc;
        indexSommetDst = _indexSommetDst;
        srcC = _srcC;
        dstC = _dstC;
        score = _score;
		i=_i;
		j=_j;
    }

	/**
	 * Met a jour le score
	 * @param i le nouveau score
	 */
    public void setScore(int i){
        score = i;
    }

	/**
	 * Retourne l index du sommet source
	 * @return L index du sommet source
	 */
    public int getSource(){
        return indexSommetSrc;
    }

	/**
	 * Retourne l index du sommet destination
	 * @return L index du sommet destination
	 */
    public int getDestination(){
		return indexSommetDst;
    }

	/**
	 * Retourne true si on prends le complementaire de la source false sinon
	 * @return true si on prends le complementaire de la source false sinon
	 */
	public boolean getSrcC(){
		return srcC;
	}

	/**
	 * Retourne true si on prends le complementaire de la destionation false sinon
	 * @return true si on prends le complementaire de la destionation false sinon
	 */
	public boolean getDstC(){
		return dstC;
	}

	/**
	 * Retourne le score
	 * @return Le score
	 */
	public int getScore(){
		return score;
	}
	public int getI(){
		return i;
	}
	public int getJ(){
		return j;
	}
}
