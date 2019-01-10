package fr.lewon.dofus.fm.common;

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

	public static CaracType getFromLabel(String label) {
		for (CaracType ct : values()) {
			if (ct.label.equals(label)) {
				return ct;
			}
		}
		return null;
	}

}
