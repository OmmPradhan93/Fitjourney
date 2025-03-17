package model;

public class Helper {
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static boolean isDouble(String value) {
		boolean valid = true;
		
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			
			valid = false;
		}
		return valid;
	}
}
