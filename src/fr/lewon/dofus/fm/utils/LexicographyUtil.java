package fr.lewon.dofus.fm.utils;

public enum LexicographyUtil {

	INSTANCE;

	private LexicographyUtil() {}

	/**
	 * Computes the distance between the two passed strings using the Levenshtein algorithm
	 *
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int levenshteinDistance(String s1, String s2) {
		int[][] d = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0 ; i <= s1.length() ; i++) {
			d[i][0] = i;
		}
		for (int i = 0 ; i <= s2.length() ; i++) {
			d[0][i] = i;
		}
		int substitutionCost = 0;
		for (int i = 1 ; i <= s1.length() ; i++) {
			for (int j = 1 ; j <= s2.length() ; j++) {
				substitutionCost = s1.charAt(i-1) == s2.charAt(j - 1) ? 0 : 1;
				d[i][j] = minVal(
						d[i - 1][j    ] + 1,
						d[i    ][j - 1] + 1,
						d[i - 1][j - 1] + substitutionCost);
			}
		}
		return d[s1.length()][s2.length()];
	}

	/**
	 * Returns the smallest integer between all the passed integers (vals)
	 *
	 * @param vals
	 * @return
	 */
	private Integer minVal(int... vals) {
		if (vals.length == 0) {
			return null;
		}
		if (vals.length == 1) {
			return vals[0];
		}
		int minVal = vals[0];
		for (int i = 1 ; i < vals.length ; i++) {
			minVal = Math.min(minVal, vals[i]);
		}
		return minVal;
	}
}
