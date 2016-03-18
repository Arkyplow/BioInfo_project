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
    public byte getMatch(){
        return match;
    }
    public byte getMismatch(){
        return mismatch;
    }
    public byte getGap(){
        return gap;
    }

}
