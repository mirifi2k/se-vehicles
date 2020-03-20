package ro.upt.se.engine;

import java.util.ArrayList;
import java.util.List;

import ro.upt.se.clauses.Clause;
import ro.upt.se.enums.Intersection;

public class KnowledgeBase {
	private List<Clause> facts;
	
	public KnowledgeBase() {
		facts = new ArrayList<>();
	}
	
	public boolean addFact(Clause fact) {
		return facts.add(fact);
	}
	
	public boolean isFact(Clause fact) {
		for (Clause f : facts) {
			if (f.matchClause(fact) == Intersection.INCLUSIVE) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNotFact(Clause fact) {
		for (Clause f : facts) {
			if (f.matchClause(fact) == Intersection.MUTUALLY_EXCLUSIVE) {
				return true;
			}
		}
		return false;
	}
	
	public List<Clause> getFacts() {
		return facts;
	}
}
