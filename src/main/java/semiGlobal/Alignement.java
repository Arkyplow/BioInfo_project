package semiGlobal;

import semiGlobal.fragment.Fragment;

/**
 * @author Jannou Brohee
 */
public class Alignement {
    /**
     * valeur d un match entre 2 symboles (entre -128 et 127)
     */
    private byte match;
    /**
     * valeur d un mismatch entre 2 symboles (entre -128 et 127)
     */
    private byte mismatch;
    /**
     * valeur d un gap (entre -128 et 127)
     */
    private byte gap;

    /**
     * Constructeur Alignement
     */
    public Alignement(){
        setGapValue((byte) -2);
        setMatchValue((byte)1);
        setMismatchValue((byte) -1);
    }

    /**
     * Retourne le score d un paire de symboles
     * @param s symbole du Fragment S
     * @param t symbole du Fragment T
     * @return le score d un paire de symboles
     */
    public int getMatchScore(char s, char t){
        return s == t ? (int)getMatch() : (int)getMismatch();
    }

    /**
     * Retourne la matrice de similiraite de 2 Fragments
     * @param s le premier Fragment
     * @param t le deuxieme Fragment
     * @return la matrice de similiraite de 2 Fragments
     */
    public int[][] matriceSim(Fragment s, Fragment t) {
        int n = s.length();
        int m = t.length();
        int[][] matriceSim = new int[n + 1][m + 1]; // ajouter un ligne et une colone de zero
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //3 cas : une série max de symb de T allignes avec des GAP, une série max de symb de S allignes avec des GAP et une paire de symbole de A
                matriceSim[i][j] = Math.max(Math.max(matriceSim[i - 1][j] +(int)getGap(), matriceSim[i][j - 1] +(int)getGap()), matriceSim[i - 1][j - 1] + getMatchScore(s.get(i - 1), t.get(j - 1)));
            }
        }
        return matriceSim;
    }

    /**
     * Met a jour la valeur d un match entre 2 symboles (entre -128 et 127)
     * @param i la nouvelle valeur d un match entre 2 symboles (entre -128 et 127)
     */
    public void setMatchValue(byte i){
        match=i;
    }

    /**
     *  Met a jour la vaeur d un mismatch entre 2 symboles (entre -128 et 127)
     * @param i la nouvelle valeur d un mismatch entre 2 symboles (entre -128 et 127)
     */
    public void setMismatchValue(byte i){
        mismatch=i;
    }

    /**
     * Met a jour la valeur d un gap (entre -128 et 127)
     * @param i la nouvelle valeur d un gap (entre -128 et 127)
     */
    public void setGapValue(byte i){
        gap=i;
    }

	/**
     * Retourne la valeur d un match entre 2 symboles (entre -128 et 127)
     * @return la valeur d un match entre 2 symboles (entre -128 et 127)
     */
    public byte getMatch(){
        return match;
    }

	/**
	 * Retourne la valeur d un mismatch entre 2 symboles (entre -128 et 127)
	 * @return la valeur d un mismatch entre 2 symboles (entre -128 et 127)
	 */
    public byte getMismatch(){
        return mismatch;
    }

	/**
	 * Retourne la valeur d un gap (entre -128 et 127)
	 * @return la valeur d un gap (entre -128 et 127)
	 */
    public byte getGap(){
        return gap;
    }
	public int[] getBestScore( int[][] matrice,Fragment s, Fragment t){
		int[] returne = new int[2];
		int score=0;
		for(int i=1;i<=s.length();i++){
			if(matrice[i][t.length()]>score){
				score = matrice[i][t.length()];
				returne[0]=i;
				returne[1]=t.length();
			}
		}
		for(int j=1;j<=t.length();j++){
			if(matrice[s.length()][j]>score){
				score = matrice[s.length()][j];
				returne[0]=s.length();
				returne[1]=j;
			}
		}
		return returne;
	}
	public Fragment aligne( Fragment s, Fragment t){
		String ret= "";
		int[][] matrice = matriceSim(s,t);
		int[] tmp = getBestScore(matrice,s,t);
		int ib = tmp[0];
		int jb = tmp[1];
		//differencier si best est trouver en fin de S ou en fin de T
		if(ib==s.length()) // best en fin de S => copier partie jb->m de t    0__________jb_________m
			ret = t.substring(jb);
		if(jb == t.length()) // best en fin de T => copier partie ib->n de s    0__________ib_________n
			ret = s.substring(ib);
		while(jb !=0 && ib !=0){
			if(matrice[ib][jb] == matrice[ib-1][jb]+getGap()){ // gap en s
				ret = s.get(ib-1) + ret;
				ib--;
			}
			else if(matrice[ib][jb] == matrice[ib][jb-1]+getGap()){ // gap en t
				ret = t.get(jb-1) + ret;
				jb --;
			}
			else if(matrice[ib][jb] == matrice[ib - 1][jb - 1] + getMatchScore(s.get(ib - 1), t.get(jb - 1))) {
				if (getMatchScore(s.get(ib - 1), t.get(jb - 1)) == getMatch())
					ret = s.get(ib - 1) + ret;
				else
					ret = "F" + ret;
				ib--;
				jb--;
			}
		}
		if(ib!=0) // best en fin de S => copier partie jb->m de t    0__________jb_________m
			ret = s.substring(0).substring(0,ib-1)+ret;
		if(jb!=0) // best en fin de T => copier partie ib->n de s    0__________ib_________n
			ret = t.substring(0).substring(0,jb-1)+ret;
		return new Fragment(ret);
	}
}
