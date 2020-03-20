package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

public class LessEqualClause extends Clause {

	public LessEqualClause(String var, String value) {
		super(var, value, "<=");
	}

	@Override
	public Intersection intersect(Clause c) {
		if (c instanceof NotEqualClause) {
			return c.intersect(this);
		}
		
		double x = -1.0;
		double y = -1.0;
		boolean numeric = true;
		
		try {
			x = Double.parseDouble(this.getValue());
			y = Double.parseDouble(c.getValue());
		} catch (NumberFormatException e) {
			numeric = false;
		}
		
		if (numeric) {
			if (c instanceof LessEqualClause) {
				if (y <= x) {
					return Intersection.INCLUSIVE;
				}
				return Intersection.UNKNOWN;
            } else if (c instanceof LessClause) {
                if (y <= x) {
                    return Intersection.INCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof EqualsClause) {
                if (y <= x) {
                    return Intersection.INCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof GreaterEqualClause) {
                if (y > x) {
                    return Intersection.MUTUALLY_EXCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof GreaterClause) {
                if (y >= x) {
                    return Intersection.MUTUALLY_EXCLUSIVE;
                }
                return Intersection.UNKNOWN;
            }
		}
		
		return Intersection.UNKNOWN;
	}

}
