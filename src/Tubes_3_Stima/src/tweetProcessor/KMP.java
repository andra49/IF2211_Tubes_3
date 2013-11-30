package tweetProcessor;

public class KMP {
	public void KMP(){
		
	}
	
	public static int KMPMatch(String text,String pattern){
		int n = text.length();
		int m = pattern.length();
		
		int border[] = ComputeBorder(pattern);
		
		int i=0,j=0;
		
		while (i<n) {
			if(pattern.charAt((j) == pattern.charAt(i)){
				if(j==m-1)
					return i-m+1;
				i++; j++;
			}else if( j > 0){
				j = border[j-1];
			}else{
				i++;
			}
		}
		
		return -1; // gak ketemu
	}
	
	public static int[] ComputeBorder(String pattern){
		int border[] = new int[pattern.length()];
		border[0] = 0;
		int m = pattern.length(), j=0, i=1;
		
		while (i<m) {
			if(pattern.charAt(j) == pattern.charAt(i)){
				border[i] = j+1;
				i++;
				j++;
			}
			else if(j>0){
				j=border[j-1];
			}
			else{
				border[i] = 0;
				i++;
			}
		}
		return border;
	} 
}
