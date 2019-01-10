package fr.lewon.dofus.fm.common;

public class FMLine {

	private CaracType caracType;
	private int minStat;
	private int maxStat;
	private int currentStat;
	private int variation;


	public FMLine(CaracType caracType, int minStat, int maxStat, int currentStat, int variation) {
		this.caracType = caracType;
		this.minStat = minStat;
		this.maxStat = maxStat;
		this.currentStat = currentStat;
		this.variation = variation;
	}


	public CaracType getCaracType() {
		return caracType;
	}
	public void setCaracType(CaracType caracType) {
		this.caracType = caracType;
	}
	public int getMinStat() {
		return minStat;
	}
	public void setMinStat(int minStat) {
		this.minStat = minStat;
	}
	public int getMaxStat() {
		return maxStat;
	}
	public void setMaxStat(int maxStat) {
		this.maxStat = maxStat;
	}
	public int getCurrentStat() {
		return currentStat;
	}
	public void setCurrentStat(int currentStat) {
		this.currentStat = currentStat;
	}
	public int getVariation() {
		return variation;
	}
	public void setVariation(int variation) {
		this.variation = variation;
	}
}
