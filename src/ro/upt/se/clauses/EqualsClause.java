package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

/**
 * This class represents the Equals Clause used for Rules.
 * 
 * @author razvanl
 * @author adamk
 * @author bogdanm
 */
public class EqualsClause extends Clause {
	
	public EqualsClause(String var, String value) {
		super(var, value, "=");
	}
	
	@Override
	public Intersection intersect(Clause c) {
		if (c instanceof EqualsClause) {
			if (this.getValue().equals(c.getValue())) {
				return Intersection.INCLUSIVE;
			} else {
				return Intersection.MUTUALLY_EXCLUSIVE;
			}
		} else if (c instanceof OrClause) {
			for (Clause clause : ((OrClause)c).getClauses()) {
				if (clause.getValue().equals(this.getValue())) {
					return Intersection.INCLUSIVE;
				} else {
					continue;
				}
			}
			return Intersection.MUTUALLY_EXCLUSIVE;
		} else {
			String thisVal = this.getValue();
			String compVal = c.getValue();
			
			double v1 = -1, v2 = -1;
			boolean numeric = true;
			
			try {
				v1 = Double.parseDouble(thisVal);
				v2 = Double.parseDouble(compVal);
			} catch (NumberFormatException e) {
				numeric = false;
			}
			
			if (numeric) {
				if (c instanceof LessEqualClause) {
					if (v1 <= v2) {
						return Intersection.INCLUSIVE;
					}
					
					return Intersection.MUTUALLY_EXCLUSIVE;
				} else if (c instanceof GreaterEqualClause) {
					if (v1 >= v2) {
						return Intersection.INCLUSIVE;
					}
					
					return Intersection.MUTUALLY_EXCLUSIVE;
				} else if (c instanceof LessClause) {
					if (v1 < v2) {
						return Intersection.INCLUSIVE;
					}
					
					return Intersection.MUTUALLY_EXCLUSIVE;
				} else if (c instanceof GreaterClause) {
					if (v1 > v2) {
						return Intersection.INCLUSIVE;
					}
					
					return Intersection.MUTUALLY_EXCLUSIVE;
				}
			}
		}
		return Intersection.UNKNOWN;
	}
}
