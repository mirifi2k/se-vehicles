package ro.upt.se.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all the utils methods needed for the project.
 * All the methods must be static.
 * 
 * @author razvanl
 */
public class Utils {
	@SuppressWarnings("serial")
	private static final Map<String, String> OPTIONS_MAP = new HashMap<String, String>() {{
		put("Gasoline", "gasoline");
		put("Diesel", "diesel");
		put("Hybrid", "hybrid");
		put("Electrical", "electrical");
		put("Sedan", "sedan");
		put("Avant (Break)", "avant");
		put("Hatchback", "hatchback");
		put("Coupe", "coupe");
		put("Cabrio", "cabrio");
		put("SUV", "SUV");
		put("Automatic", "automatic");
		put("Manual", "manual");
		put("New cars", "new");
		put("Old School cars", "old");
		put("Two", "2");
		put("Four/Five", "5");
		put("More than five", "8");
	}};
	
	public static String mapOption(String option) {
		return OPTIONS_MAP.get(option);
	}
	
	public static int getNumberFromString(String str) {
		return Integer.valueOf(str.replaceAll("\\D+", ""));
	}
}