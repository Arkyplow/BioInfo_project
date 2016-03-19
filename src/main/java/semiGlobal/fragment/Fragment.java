package semiGlobal.fragment;

/**
 * representation d'un fragment
 * @author Jannou Brohee
 */
public class Fragment {
    private static final char GAP = ' ';
    private final String frag;

    /**
     * Constructeur d'un fragment
     * @param _frag le fragment a représenter
     */
    public Fragment(String _frag){
        frag = _frag;
    }

    /**
     * Donne le caractère a l'indice i du fragment
     * @param i L'indice
     * @return Le caractère a l'indice i du fragment
     */
    public char get(int i) {
        return frag.charAt(i);
    }

    /**
     * Donne la longueur du fragment
     * @return La longueur du fragment
     */
    public int length() {
        return frag.length();
    }

    /**
     * calcule le complementaire inversé d'un fragment
     * @return le complementaire inversé d'un fragment
     */
    public Fragment getComplementaire(){
        String compl= "";
        char work [] = frag.toCharArray();
        for (int i = work.length-1;i>=0;i--){
            switch (work[i]) {
                case 'A':
                    compl += 'T';
                    break;
                case 'T':
                    compl += 'A';
                    break;
                case 'C':
                    compl += 'G';
                    break;
                case 'G':
                    compl += 'C';
                    break;
                default:
                    compl += GAP ;
                    break;
            }
        }
        return new Fragment(compl);
    }
    public String substring(int i){
        return frag.substring(i);
    }
    public String toString() {
        return  frag ;
    }
}
