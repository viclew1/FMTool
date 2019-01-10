package fr.lewon.dofus.fm.common;

import fr.lewon.dofus.fm.utils.LexicographyUtil;

/**
 *	All possible characteristics an item can have in Dofus
 */
public enum CaracType {

	VITALITE("Vitalité"),
	FORCE("Force"),
	INTELLIGENCE("Intelligence");

	private final String label;

	private CaracType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * Returns the CaracType with the same label as the passed label
	 *
	 * @param label
	 * @return
	 */
	public static CaracType getFromLabel(String label) {
		for (CaracType ct : values()) {
			if (ct.label.equals(label)) {
				return ct;
			}
		}
		return null;
	}

	/**
	 * Returns the CaracType with the closest label to the passed label (according to the Levenshtein distance)
	 *
	 * @param label
	 * @return
	 */
	public static CaracType getClosestFromLabel(String label) {
		CaracType closest = null;
		int minDist = Integer.MAX_VALUE;
		for (CaracType c : values()) {
			int distance = LexicographyUtil.INSTANCE.levenshteinDistance(label, c.label);
			if (distance < minDist) {
				minDist = distance;
				closest = c;
			}
		}
		return closest;
	}

}
