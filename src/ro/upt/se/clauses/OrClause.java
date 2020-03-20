package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

public class OrClause extends Clause {
	
	private Clause[] clauses;

	public OrClause(String var, Clause[] clauses) {
		super(var, clauses[0].getValue(), "|");
		this.clauses = clauses;
	}
	
	public Clause[] getClauses() {
		return clauses;
	}

	@Override
	public Intersection intersect(Clause clause) {
		for (Clause c : clauses) {
			Intersection intersection = c.intersect(clause);
			
			if (intersection == Intersection.INCLUSIVE) {
				return Intersection.INCLUSIVE;
			} else {
				continue;
			}
		}
		return Intersection.MUTUALLY_EXCLUSIVE;
	}
}
