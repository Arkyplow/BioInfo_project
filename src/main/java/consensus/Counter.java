package consensus;

import java.util.Random;

public class Counter {
	
	private static Random rand = new Random();
	
	private int a;
	private int c;
	private int g;
	private int t;
	
	public Counter(){
		a = 0;
		c = 0;
		g = 0;
		t = 0;
	}
	
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
	
	public char getMajority(){
		if(a == 0 && c == 0 && g == 0 && t == 0){
			return 'x';
		}
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
