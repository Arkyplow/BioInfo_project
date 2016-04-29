package semiGlobal;

import utils.String2Byte;

import java.util.ArrayList;

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
    public int getMatchScore(byte s, byte t){
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
            for (int j = 1; j <= m; j++)
                //3 cas : une série max de symb de T allignes avec des GAP, une série max de symb de S allignes avec des GAP et une paire de symbole de A
                matriceSim[i][j] = Math.max(Math.max(matriceSim[i - 1][j] +(int)getGap(), matriceSim[i][j - 1] +(int)getGap()), matriceSim[i - 1][j - 1] + getMatchScore(s.get(i - 1), t.get(j - 1)));
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

	/**
	 * Calcule le score d un alignement

	 * @param G le Fragment G
	 * @param T le Fragment T
	 * @return le meilleur score ainsi que les indice dans la matrice ou il faut commencer pour construire l alignement
	 */
	public int[] getBestScores(Fragment G , Fragment T){
		int[] retour = new int[]{0, 0};
		int[][] matrice = matriceSim(G,T);
		int tmpscore=Integer.MIN_VALUE;

		//indexs.get(0) ==> paire de iG,jG dans G
		//indexs.get(1) ==> paire de iG,jG dans T
		// iG,jG ==> indice quand on commence par G
		for(int j=1;j<=T.length();j++){
			if(matrice[G.length()][j]>=tmpscore){ // nouveau max en enlève ce qu'on avait jusqu'a present
				tmpscore = matrice[G.length()][j];
				retour[0]=tmpscore;
			}
		}
		tmpscore=Integer.MIN_VALUE;
		for(int i=1;i<=G.length();i++) {
			if (matrice[i][T.length()] >= tmpscore) {
				tmpscore = matrice[i][T.length()];
				retour[1]=tmpscore;
			}
		}
		return retour;
	}
	/**
	 * Retourne un tableau de Fragment de taille 2 representant l alignement de T sur G ou on maximise les gap a la fin de G et au debut de T.
	 * Le premier element du tableau represente G et le deuxieme element represente T .
	 * @param G Le fragment qui commece l'alignement
	 * @param T Le fragment qui est aligne
	 *
	 * @return Un tableau de String de taille 2 ou le premier element represente G et le deuxieme element represente T .
	 */
	public Fragment[] aligne( Fragment G, Fragment T){
		ArrayList<Byte> ter1 = new ArrayList<Byte>();
		ArrayList<Byte> ter2 = new ArrayList<Byte>();

		int[][] matrice = matriceSim(G,T);
		int tmpscore=Integer.MIN_VALUE;
		int iG = 0,jG = 0;
		for(int j=1;j<=T.length();j++){
			if(matrice[G.length()][j]>=tmpscore){
				tmpscore = matrice[G.length()][j];
				iG=G.length();
				jG=j;
			}
		}
		// comme on aligne un fragment sur un autre on sait que l'un des deux fragment ne sera pas modifier,
		// on cherche donc a construire le deuxieme fragment qui a ete modifier pour s aligner
		// ==> on a donc T.lenght - jG gap a mettre pour G
		//==> on doit recuperer les T.lenght - jG dernier element de T

		for(int i = 0; i<(T.length()-jG);i++ ) {
			ter1.add(0, (byte) 4);
			ter2.add(0,T.get((T.length()-i)-1));
		}
		// on construit les alignement
		while(jG !=0 && iG !=0){
			if(matrice[iG][jG] == matrice[iG-1][jG]+getGap()){ // gap en G + recuperer une base dans T
				ter1.add(0, G.get(iG-1));
				ter2.add(0,(byte)4);
				iG--;
			}
			else if(matrice[iG][jG] == matrice[iG][jG-1]+getGap()){ // gap en T + recuperer une base dans G
				ter1.add(0, (byte) 4);
				ter2.add(0,T.get(jG-1));
				jG --;
			}
			else if(matrice[iG][jG] == matrice[iG - 1][jG - 1] + getMatchScore(G.get(iG - 1), T.get(jG - 1))) { //on copie un base de G et une base de T
				ter1.add(0, G.get(iG-1));
				ter2.add(0,T.get(jG-1));
				iG--;
				jG--;
			}
		}
		// 2 cas possible,
		if(iG!=0){ // il reste des base dans G mais pas dans T ==> rajouter des gap dans T (chevauchement )
			while(iG>0){
				ter1.add(0, G.get(iG-1));
				ter2.add(0,(byte)4);
				iG--;
			}
		}
		if(jG!=0){ // il reste des base dans T mais pas dans G ==> rajouter des gap dans G (G est "inclus" dans T )
			while (jG > 0) {
				ter1.add(0, (byte) 4);
				ter2.add(0,T.get(jG-1));
				jG--;
			}
		}
		Byte[] ter1a = new Byte[ter1.size()];
		ter1.toArray(ter1a);

		byte[] cast = new byte[ter1.size()];
		for(int i = 0;i<cast.length;i++){
			cast[i]=ter1a[i];
		}
		Byte[] ter2a = new Byte[ter2.size()];
		ter2.toArray(ter2a);

		byte[] cast2 = new byte[ter2.size()];
		for(int i = 0;i<cast2.length;i++){
			cast2[i]=ter2a[i];
		}
		Fragment retour[]  ={new Fragment(cast),new Fragment(cast2)};
		return retour;
	}

}
