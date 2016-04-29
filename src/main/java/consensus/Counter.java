package consensus;

import java.util.Random;

/**
 * Classe s'occupant du vote de majorité il s'agit simplement de
 * compteur et au bout du compte on renvoie le maximum des compteur
 * ou aléatoirement parmis les comteurs maximums
 * @author santorin
 *
 */
public class Counter {
	
	private static Random rand = new Random();
	
	private int a;
	private int c;
	private int g;
	private int t;
	
	/**
	 * Constructeur d'un vote
	 */
	public Counter(){
		a = 0;
		c = 0;
		g = 0;
		t = 0;
	}
	
	/**
	 * On ajoute un nucléotide au vote
	 * @param nucl le nucléotide sous forme de byte avec
	 * 				0 pour 'a'
	 * 				1 pour 'c'
	 * 				2 pour 'g'
	 * 				3 pour 't'
	 */
	public void addNucl(byte nucl){
		switch(nucl){
		case 0:	a++;
			break;
		case 1:	c++;
			break;
		case 2:	g++;
			break;
		case 3:	t++;
			break;
		}
	}
	
	/**
	 * retourne le compteur ayant la plus grande valeur ou un compteur
	 * aléatoire parmis ceux de plus grande valeur
	 * 
	 * @return le nucléotide choisi sous forme de char
	 */
	public char getMajority(){
		if(a > Math.max(Math.max(c, g), t)){
			return 'a';
		}else if(c > Math.max(Math.max(a, g), t)){
			return 'c';
		}else if(g > Math.max(Math.max(c, a), t)){
			return 'g';
		}else if(t > Math.max(Math.max(c, g), a)){
			return 't';
		}
		switch(rand.nextInt(4)){
			case 0:	return 'a';
			case 1:	return 'c';
			case 2: return 'g';
			case 3:	return 't';
		}
		return '_';
		
	}

}
