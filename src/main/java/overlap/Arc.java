package overlap;

/**
 * permet de représenter un arc d'un graph orienté
 * @author Jannou Brohee
 */
public class Arc {
    private int indexSommetSrc;
    private int indexSommetDst;
    private int score;
    private boolean srcC= false;
    private boolean dstC = false;
    public Arc(int _indexSommetSrc, int _indexSommetDst, boolean _srcC, boolean _dstC, int _score){
        indexSommetSrc = _indexSommetSrc;
        indexSommetDst = _indexSommetDst;
        srcC = _srcC;
        dstC = _dstC;
        score = _score;
    }
    public void setScore(int i){
        score = i;
    }
    public void compute(Sommet src , Sommet dst){

    }
    public int getSource(){
        return indexSommetSrc;
    }
    public int getDestination(){
		return indexSommetDst;
    }
	public boolean getSrcC(){
		return srcC;
	}
	public boolean getDstC(){
		return dstC;
	}
	public int getScore(){
		return score;
	}
}
