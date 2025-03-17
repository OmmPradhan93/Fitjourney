package model;

public class Goal extends Item {
	
	private double multiplier;
	
	public Goal(String name, double multiplier) {
		super(name);
		
		
		if(multiplier <= 0) throw new IllegalArgumentException("Negative multiplier set!");
		
		this.multiplier = multiplier;
	}
	
	public double getMultiplier() {
		return this.multiplier;
	}
}
