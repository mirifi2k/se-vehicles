package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

public class GreaterClause extends Clause {

	public GreaterClause(String var, String value) {
		super(var, value, ">");
	}

	@Override
	public Intersection intersect(Clause c) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
