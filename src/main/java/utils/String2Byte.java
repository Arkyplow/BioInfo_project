package utils;

/**
 * @author Jannou Brohee
 */
public class String2Byte {
	private static StringBuffer buff;
	public static byte[] encode(String in){
		char[] work = in.toCharArray();
		byte[] btab = new byte[work.length];
		for (int i = 0;i<work.length;i++){
			switch(work[i]){
				case 'A':
					btab[i]= (byte)0;
					break;
				case 'T':
					btab[i]= (byte)3;
					break;
				case 'C':
					btab[i]= (byte)1;
					break;
				case 'G':
					btab[i]= (byte)2;
					break;
				case 'a':
					btab[i] = (byte)0;
					break;
				case 't':
					btab[i] = (byte)3;
					break;
				case 'c':
					btab[i]= (byte)1;
					break;
				case 'g':
					btab[i]= (byte)2;
					break;
				default:
					btab[i] = (byte)4;
					break;
			}
		}
		return btab;
	}
	public static String decode(byte[] in){
		buff = new StringBuffer(in.length);
		for(int i = 0;i<in.length;i++){
			switch(in[i]){
				case 0:
					buff.insert(i,'a');
					break;
				case 1:
					buff.insert(i,'c');
					break;
				case 2:
					buff.insert(i,'g');
					break;
				case 3:
					buff.insert(i,'t');
					break;
				case 4:
					buff.insert(i,'_');
					break;
			}
		}
		return buff.toString();
	}

}
