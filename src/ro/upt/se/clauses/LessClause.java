package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

public class LessClause extends Clause {

	public LessClause(String var, String value) {
		super(var, value, "<");
	}

	@Override
	public Intersection intersect(Clause c) {
		double v1 = -1;
        double v2 = -1;
        boolean numeric = true;
        
        try {
            v1 = Double.parseDouble(this.getValue());
            v2 = Double.parseDouble(c.getValue());
        } catch (NumberFormatException e) {
        	numeric = false;
        }
        
        if (numeric) {
            if (c instanceof LessClause) {
                if (v2 <= v1) {
                    return Intersection.INCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof LessEqualClause) {
                if (v2 < v1) {
                    return Intersection.INCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof EqualsClause) {
                if (v2 < v1) {
                    return Intersection.INCLUSIVE;
                }
                return Intersection.MUTUALLY_EXCLUSIVE;
            } else if (c instanceof GreaterEqualClause) {
            	if (v2 >= v1) {
                    return Intersection.MUTUALLY_EXCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof GreaterClause) {
                if (v2 >= v1) {
                    return Intersection.MUTUALLY_EXCLUSIVE;
                }
                return Intersection.UNKNOWN;
            }
        }
        return Intersection.UNKNOWN;
	}
}
