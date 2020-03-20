package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

/**
 * This class represents the abstract type of a Clause.
 * 
 * @author razvanl
 * @author adamk
 * @author bogdanm
 */
public abstract class Clause {
	private String value;
	private String condition;
	private String var;
	
	public Clause(String var, String value, String condition) {
		this.var = var;
		this.value = value;
		this.condition = condition;
	}
	
	/**
	 * 
	 * This method represents the intersection status of two clauses.
	 * 
	 * @param c The clause to intersect 'this' object with.
	 * @return An Intersection type, which could be:
	 * <li>INCLULSIVE</li>
	 * <li>MUTUALLY_EXCLUSIVE</li>
	 * <li>UNKOWN</li>
	 * 
	 * @see Intersection
	 */
	public abstract Intersection intersect(Clause c);
	
	public Intersection matchClause(Clause c) {
		if (!this.var.equals(c.getVariable())) {
            return Intersection.UNKNOWN;
        }
        return intersect(c);
	}
	
	public String getVariable() {
		return this.var;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "Clause[variable=" + var + ",condition='" + condition + "',value=" + value + "]";
	}
}
