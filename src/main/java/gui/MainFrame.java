package gui;

import semiGlobal.Alignement;
import semiGlobal.fragment.Fragment;
import utils.FileHelper;

import javax.swing.*;
import java.util.ArrayList;

/**
 * fenetre principale
 * @author Jannou Brohee
 */
public class MainFrame {

	/**
	 *
	 * @param args parametre min fenetre principale
     */
	public static void main(String[] args) {
		int b = 2;
		int a =b;
		int n = 3/2;
		b+=1;
		System.out.println(a+" "+n);
		ArrayList<Fragment> frags = FileHelper.load("/home/nanabaskint/Git/BioInfo_project/datas/Collections/10000/collection1.fasta");
		Fragment T = new Fragment("ATCGGCATTCAGT");//.getComplementaire();//frags.get(0); //
		Fragment S = new Fragment("ATTAGACCATGCGGC");//.getComplementaire();//frags.get(1); //
		Alignement ali = new Alignement();
		int[][] test = ali.matriceSim(S,T);
		for(int k =0; k<=S.length();k++){
			for(int j =0;j<=T.length();j++){
				System.out.print(test[k][j]+" , ");
			}
			System.out.print("\n");
		}
		int i =0;
		int j=0;
		int[] al = new Alignement().getBestScore(test,S,T);
		System.out.println("==> best equals "+test[al[0]][al[1]]+ " at i="+al[0]+" & j="+al[1]);
		System.out.println("S = "+S+ " "+S.length());
		System.out.println("T = "+T+" "+T.length());
		Fragment result = ali.aligne(S,T);
		System.out.println("result : "+result +" "+result.length());
		//initializeMain();
	}

	/**
	 * initialisation fenetre principale
	 */
	private static void initializeMain() {
		JFrame frame = new JFrame("BioInformatique");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,600);
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
