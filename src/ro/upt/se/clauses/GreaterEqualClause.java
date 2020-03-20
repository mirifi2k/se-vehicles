package ro.upt.se.clauses;

import ro.upt.se.enums.Intersection;

public class GreaterEqualClause extends Clause {

	public GreaterEqualClause(String var, String value) {
		super(var, value, ">=");
	}

    @Override
    public Intersection intersect(Clause c) {
        if (c instanceof NotEqualClause) {
            return c.intersect(this);
        }

        String v1 = this.getValue();
        String v2 = c.getValue();

        double a = 0;
        double b = 0;

        boolean numeric = true;
        try {
            a = Double.parseDouble(v1);
            b = Double.parseDouble(v2);
        } catch (NumberFormatException exception) {
            numeric = false;
        }

        if (numeric){
            if (c instanceof LessClause) {
                if (b <= a) {
                	return Intersection.MUTUALLY_EXCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof LessEqualClause) {
                if (b < a) {
                	return Intersection.MUTUALLY_EXCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof EqualsClause) {
                if (b >= a) {
                	return Intersection.INCLUSIVE;
                }
                return Intersection.MUTUALLY_EXCLUSIVE;
            } else if (c instanceof GreaterEqualClause) {
                if (b >= a) {
                	return Intersection.INCLUSIVE;
                }
                return Intersection.UNKNOWN;
            } else if (c instanceof GreaterClause) {
                if (b >= a) {
                	return Intersection.INCLUSIVE;
                }
                return Intersection.UNKNOWN;
            }
        }
        return Intersection.UNKNOWN;
    }
}
