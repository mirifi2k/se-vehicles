package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

public class NotEqualClause extends Clause {

	private Clause negClause;
	
	public NotEqualClause(String var, String value) {
		super(var, value, "!=");
	}
	
	public NotEqualClause(Clause c) {
		super(c.getVariable(), c.getValue(), "!=");
		negClause = c;
	}

	@Override
	public Intersection intersect(Clause c) {
		Intersection intersection = negClause.intersect(c);
		
		if (intersection == Intersection.INCLUSIVE) {
			return Intersection.MUTUALLY_EXCLUSIVE;
		} else if (intersection == Intersection.MUTUALLY_EXCLUSIVE) {
			return Intersection.INCLUSIVE;
		}
		return Intersection.UNKNOWN;
	}
}
