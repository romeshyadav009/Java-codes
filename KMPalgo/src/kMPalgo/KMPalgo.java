package kMPalgo;

public class KMPalgo {
	void computeLPSArray(String pat,int M,int lps[]) {
		//length of the previous longest prefix suffix
		int len=0;
		int i=1;
		lps[0]=0; //lps[0] is always 0.
		
		//the loop calculates lps[i] for i=1 to i=M-1
		while(i<M) {
			if(pat.charAt(i)==pat.charAt(len)) {
				len++;
				lps[i]=len;
				i++;
			}
			else //pat[i]!=pat[len]
			{
				if(len!=0) {
					len=lps[len-1];
				}
				else // if len==0
				{
					lps[i]=len;
					i++;
				}
			}
		}
	}
	void KMPSearch(String pat, String txt) {
		int M=pat.length();
		int N=txt.length();
		int lps[]=new int[M];
		
		int j=0;
		
		//preprocess the pattern(calculate lps[] array
		computeLPSArray(pat, M, lps);
		
		int i=0; //index for txt[]
		while(i<N) {
			if(pat.charAt(j)==txt.charAt(i)) {
				j++;
				i++;
			}
			if(j==M) {
				System.out.println("pattern found at index: "+ (i-j));
				j=lps[j-1];
			}
			//mismatch after j matches
			else if(i<N && pat.charAt(j)!=txt.charAt(i)) {
				if(j!=0) {
					j=lps[j-1];
				}
				else {
					i=i+1;
				}
			}
		}
		
		}
	public static void main(String args[]) {
		String txt= "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        new KMPalgo().KMPSearch(pat,txt);
	}
}
