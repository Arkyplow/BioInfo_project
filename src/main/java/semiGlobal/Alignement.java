package semiGlobal;

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
	 * Dans le cas ou il y a plusieurs alignement optimaux on calcule le score de chacun d entre eux et on retourne le meilleur
	 * @param G le Fragment G
	 * @param T le Fragment T
	 * @return le meilleur score ainsi que les indice dans la matric ou il faut commencer pour construire l alignement
	 */
	public int[] getBestScores(Fragment G , Fragment T){
		int[] retour = new int[]{0, 0, 0, 0, 0, 0};
		int[][] matrice = matriceSim(G,T);
		int tmpscore=0;
		ArrayList<ArrayList<int[]>> indexs = new ArrayList();
		indexs.add(new ArrayList<int[]>());
		indexs.add(new ArrayList<int[]>());
		//indexs.get(0) ==> paire de iG,jG dans G
		//indexs.get(1) ==> paire de iG,jG dans T
		int iG ,jG; // iG,jG ==> indice quand on commence par G
		for(int j=1;j<=T.length();j++){
			if(matrice[G.length()][j]>tmpscore){ // nouveau max en enlève ce qu'on avait jusqu'a present
				tmpscore = matrice[G.length()][j];
				indexs.get(0).clear();
				indexs.get(0).add(new int[]{G.length(), j});
			}
			else if(matrice[G.length()][j]>=tmpscore) // doublon ==> on ajoute
				indexs.get(0).add(new int[]{G.length(), j});
		}
		tmpscore=0;
		for(int i=1;i<=G.length();i++) {
			if (matrice[i][T.length()] > tmpscore) {
				tmpscore = matrice[i][T.length()];
				indexs.get(1).clear();
				indexs.get(1).add(new int[]{i, T.length()});
			} else if (matrice[i][T.length()] >= tmpscore)
				indexs.get(1).add(new int[]{i, T.length()});
		}
		//score pour G -> T
		// voir tout les score possible et garder meilleur
		for(int[] t : indexs.get(0)){
			tmpscore =0;
			iG =t[0];
			jG =t[1];
			while(jG !=0 && iG !=0){
				if(matrice[iG][jG] == matrice[iG-1][jG]+getGap()){ // gap en s
					iG--;
					tmpscore+=(int)getGap();
				}
				else if(matrice[iG][jG] == matrice[iG][jG-1]+getGap()){ // gap en t
					jG --;
					tmpscore+=(int)getGap();
				}
				else if(matrice[iG][jG] == matrice[iG - 1][jG - 1] + getMatchScore(G.get(iG - 1), T.get(jG - 1))) {
					if (G.get(iG - 1)== T.get(jG - 1))
						tmpscore+=(int)getMatch();
					else
						tmpscore+=(int)getMismatch();
					iG--;
					jG--;
				}
			}
			if(retour[0]<tmpscore){
				retour[0] = tmpscore;
				retour[2]=t[0];
				retour[3]=t[1];
			}
		}
		// score pour T -> G
		for(int[] t : indexs.get(1)) {
			tmpscore = 0;
			jG=t[1];
			iG=t[0];
			while(jG !=0 && iG !=0){
				if(matrice[iG][jG] == matrice[iG-1][jG]+getGap()){ // gap en s
					iG--;
					tmpscore+=(int)getGap();
				}
				else if(matrice[iG][jG] == matrice[iG][jG-1]+getGap()){ // gap en t
					jG --;
					tmpscore+=(int)getGap();
				}
				else if(matrice[iG][jG] == matrice[iG - 1][jG - 1] + getMatchScore(G.get(iG - 1), T.get(jG - 1))) {
					if (G.get(iG - 1)== T.get(jG - 1))
						tmpscore+=(int)getMatch();
					else
						tmpscore+=(int)getMismatch();
					iG--;
					jG--;
				}
			}
			if(retour[1]<tmpscore){
				retour[1] = tmpscore;
				retour[4]=t[0];
				retour[5]=t[1];
			}
		}
		return retour;
	}
	/**
	 * Retourne un tableau de String de taille 2 representant l alignement de T sur G ou on maximise les gap a la fin de G et au debut de T.
	 * Le premier element du tableau represente G et le deuxieme element represente T .
	 * @param G Le fragment ou on maximise les gap a la fin
	 * @param T Le fragment ou on maximise les gap au debut
	 * @return Un tableau de String de taille 2 ou le premier element represente G et le deuxieme element represente T .
	 */
	public String[] aligne( Fragment G, Fragment T,int _iG,int _jG){
		String retour[] = new String[2];
		retour[0] = retour[1] = "";
		int[][] matrice = matriceSim(G,T);
		int tmpscore=-1;
		int iG = 0,jG = 0;
		for(int j=1;j<=T.length();j++){
			if(matrice[G.length()][j]>tmpscore){
				tmpscore = matrice[G.length()][j];
				iG=G.length();
				jG=j;
			}
		}
		// comme on aligne un fragment sur un autre on sait que l'un des deux fragment ne sera pas modifier,
		// on cherche donc a construire le deuxieme fragment qui a ete modifier pour s aligner
		// ==> on a donc T.lenght - jG gap a mettre pour G
		//==> on doit recuperer les T.lenght - jG dernier element de T
		for(int i = 0; i<(T.length()-jG);i++ )
			retour[0] += "_";
		retour[1] = T.substring(jG); // O(1)
		// on construit les alignement
		while(jG !=0 && iG !=0){
			if(matrice[iG][jG] == matrice[iG-1][jG]+getGap()){ // gap en G + recuperer une base dans T
				retour[0] = G.get(iG-1) + retour[0];
				retour[1] = "_"+retour[1];
				iG--;
			}
			else if(matrice[iG][jG] == matrice[iG][jG-1]+getGap()){ // gap en T + recuperer une base dans G
				retour[0] = "_"+retour[0];
				retour[1] = T.get(jG) + retour[1];
				jG --;
			}
			else if(matrice[iG][jG] == matrice[iG - 1][jG - 1] + getMatchScore(G.get(iG - 1), T.get(jG - 1))) { //on copie un base de G et une base de T
				retour[1] = T.get(jG-1) + retour[1];
				retour[0] = G.get(iG-1) + retour[0];
				iG--;
				jG--;
			}
		}
		// 2 cas possible,
		if(iG!=0){ // il reste des base dans G mais pas dans T ==> rajouter des gap dans T (chevauchement )
			while(iG>0){
				retour[0] = G.get(iG-1) + retour[0];
				retour[1] = "_"+retour[1];
				iG--;
			}
		}
		if(jG!=0){ // il reste des base dans T mais pas dans G ==> rajouter des gap dans G (G est "inclus" dans T )
			while (jG > 0) {
				retour[0] = "_" +retour[0];
				retour[1] = T.get(jG-1) + retour[1];
				jG--;
			}
		}
		return retour;
	}
}
