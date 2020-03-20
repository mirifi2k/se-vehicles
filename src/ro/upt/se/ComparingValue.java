package ro.upt.se;

public class ComparingValue {
	public static final String LESS = "<";
	public static final String LESS_EQUAL = "<=";
	public static final String GREATER = ">";
	public static final String GREATER_EQUAL = ">=";
	
	private int value;
	private String comparator;
	
	public ComparingValue(String comparator, int value) {
		this.value = value;
		this.comparator = comparator;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getComparator() {
		return this.comparator;
	}
}
