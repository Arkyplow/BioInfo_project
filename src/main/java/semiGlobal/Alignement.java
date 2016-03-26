package semiGlobal;

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
	public int[] getBestScores(Fragment G , Fragment T){
		int[] retour = new int[2];
		int[][] matrice = matriceSim(G,T);
		int tmpscore=0;
		int iG = 0,iT=0,jG = 0,jT=0; // iG,jG ==> indice quand on commence par G
		for(int j=1;j<=T.length();j++){
			if(matrice[G.length()][j]>tmpscore){
				tmpscore = matrice[G.length()][j];
				iG=G.length();
				jG=j;
			}
		}
		tmpscore=0;
		for(int i=1;i<=G.length();i++){
			if(matrice[i][T.length()]>tmpscore){
				tmpscore= matrice[i][T.length()];
				iT=i;
				jT=T.length();
			}
		}
		//score pour G -> T
		while(jG !=0 && iG !=0){
			if(matrice[iG][jG] == matrice[iG-1][jG]+getGap()){ // gap en s
				iG--;
				retour[0]+=(int)getGap();
			}
			else if(matrice[iG][jG] == matrice[iG][jG-1]+getGap()){ // gap en t
				jG --;
				retour[0]+=(int)getGap();
			}
			else if(matrice[iG][jG] == matrice[iG - 1][jG - 1] + getMatchScore(G.get(iG - 1), T.get(jG - 1))) {
				if (getMatchScore(G.get(iG - 1), T.get(jG - 1)) == getMatch())
					retour[0]+=(int)getMatch();
				else
					retour[0]+=(int)getMismatch();
				iG--;
				jG--;
			}
		}
		// score pour T -> G
		while(jT !=0 && iT !=0){
			if(matrice[iT][jT] == matrice[iT-1][jT]+getGap()){ // gap en s
				iT--;
				retour[1]+=(int)getGap();
			}
			else if(matrice[iT][jT] == matrice[iT][jT-1]+getGap()){ // gap en t
				jT --;
				retour[1]+=(int)getGap();
			}
			else if(matrice[iT][jT] == matrice[iT - 1][jT - 1] + getMatchScore(G.get(iT - 1), T.get(jT - 1))) {
				if (getMatchScore(G.get(iT - 1), T.get(jT - 1)) == getMatch())
					retour[1]+=(int)getMatch();
				else
					retour[1]+=(int)getMismatch();
				iT--;
				jT--;
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
	public String[] aligne( Fragment G, Fragment T){
		String retour[] = new String[2];
		retour[0] = retour[1] = "";
		int[][] matrice = matriceSim(G,T);
		int tmpscore=0;
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
