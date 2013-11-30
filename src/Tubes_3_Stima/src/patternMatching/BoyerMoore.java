package patternMatching;

public class BoyerMoore {
	private String string;
	private String pattern;

	public BoyerMoore(){
		
	}
	
	public BoyerMoore(String string, String pattern) {
		this.string = string;
		this.pattern = pattern;
	}

	public int run() {
		int last[] = buildLast();
		int n = string.length();
		int m = pattern.length();
		int i = m - 1;
		if (i > n - 1)
			return -1; // no match if pattern is  // longer than text
		int j = m - 1;
		do {
			if (pattern.charAt(j) == string.charAt(i)) {
				if (j == 0) {
					return i; // match
				} else { // looking-glass technique
					i--;
					j--;
				}
			} else { // character jump technique
				int lo = last[string.charAt(i)]; // last occ
				i = i + m - Math.min(j, 1 + lo);
				j = m - 1;
			}
		} while (i <= n - 1);
		return -1; // no match
	}

	private int[] buildLast() {
		int last[] = new int[128];
		for (int i = 0; i < 128; i++)
			last[i] = -1; // initialize array
		for (int i = 0; i < pattern.length(); i++)
			last[pattern.charAt(i)] = i;
		return last;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
