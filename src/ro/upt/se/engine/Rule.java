package ro.upt.se.engine;

import java.util.List;

import ro.upt.se.clauses.Clause;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the Rule that the Inference Engine uses.
 * 
 * @author razvanl
 * @author adamk
 * @author bogdanm
 */
public class Rule {
	private Clause consequence;
	
	private List<Clause> antecedents;
	private String name;
	private String image;
	
	private boolean isFired;
	private int idx = 0;
	
	public Rule(String name) {
		this.antecedents = new ArrayList<>();
		this.name = name;
		this.isFired = false;
	}
	
	public boolean addAllAntecedents(Collection<? extends Clause> col) {
		return antecedents.addAll(col);
	}
	
	public boolean addAntecedent(Clause c) {
		return antecedents.add(c);
	}
	
	public void resetIterator() {
		idx = 0;
	}
	
	public boolean hasFollowingAntecedent() {
		return idx < antecedents.size();
	}
	
	public Clause getNextAntecedent() {
		return antecedents.get(idx++);
	}

	public Clause getConsequence() {
		return consequence;
	}

	public void setConsequence(Clause consequence) {
		this.consequence = consequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFired() {
		return isFired;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public boolean isTriggered(KnowledgeBase kb) {
		for (Clause c : antecedents) {
			if (!kb.isFact(c)) {
				return false;
			}
		}
		return true;
	}
	
	public void fire(KnowledgeBase kb) {
		if (kb.isFact(this.consequence)) {
			kb.addFact(this.consequence);
		}
		
		this.isFired = true;
	}
	
	public String getAntecedent(String antecedent) {
		for (Clause c : antecedents) {
			if (c.getVariable().equals(antecedent)) {
				return c.getValue();
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.name + "=[");
		antecedents.forEach(e -> sb.append(e + ","));
		return sb.toString() + "]";
	}
}
