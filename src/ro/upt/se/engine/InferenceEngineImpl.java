package ro.upt.se.engine;

import ro.upt.se.clauses.Clause;
import ro.upt.se.enums.Intersection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InferenceEngineImpl implements InferenceEngine {
    protected List<Rule> rules;
    protected KnowledgeBase knowledgeBase;

    public InferenceEngineImpl(){
    	rules = new ArrayList<>();
    	knowledgeBase = new KnowledgeBase();
    }

    @Override 
    public void addRule(Rule rule){
        rules.add(rule);
    }
    
    @Override
    public void addAllRules(List<Rule> rules) {
    	this.rules.addAll(rules);
    }

    @Override 
    public List<Rule> getConsequence() {
    	List<Rule> conclusions = new ArrayList<>();
        List<Clause> unprovedConditions = new ArrayList<>();

        for (Rule rule : rules) {
        	if (conclusions.contains(rule)) {
        		continue;
        	}
        	
            rule.resetIterator();
            
            boolean goalReached = true;
            while (rule.hasFollowingAntecedent()) {
                Clause antecedent = rule.getNextAntecedent();

                if (!knowledgeBase.isFact(antecedent)){
                    if (knowledgeBase.isNotFact(antecedent)) {
                        goalReached = false;
                        continue;
                    } else if (isFact(antecedent, unprovedConditions)) {
                        knowledgeBase.addFact(antecedent);
                    } else {
                        goalReached = false;
                        break;
                    }
                }
            }
            
            if (goalReached) {
            	conclusions.add(rule);
                continue;
            }
        }

        return conclusions;
    }

    public boolean isFact(Clause goal, List<Clause> unprovedConditions) {
        List<Rule> goalStack = new ArrayList<>();

        for (Rule rule : rules) {
            Clause consequent = rule.getConsequence();
            Intersection it = consequent.matchClause(goal);

            if (it == Intersection.INCLUSIVE) {
                goalStack.add(rule);
            }
        }

        if (goalStack.isEmpty()) {
        	unprovedConditions.add(goal);
        } else {
            for (Rule rule : goalStack) {
                rule.resetIterator();
                boolean goalReached = true;
                
                while (rule.hasFollowingAntecedent()) {
                    Clause antecedent = rule.getNextAntecedent();

                    if (!knowledgeBase.isFact(antecedent)){
                        if (knowledgeBase.isNotFact(antecedent)){
                            goalReached = false;
                            break;
                        } else if (isFact(antecedent, unprovedConditions)) {
                            knowledgeBase.addFact(antecedent);
                        } else {
                            goalReached = false;
                            break;
                        }
                    }
                }

                if (goalReached) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override 
    public KnowledgeBase getKnowledgeBase() {
        return knowledgeBase;
    }
    
    @Override 
    public void addFact(Clause c) {
        knowledgeBase.addFact(c);
    }

    @Override 
    public List<Rule> match() {
    	return rules
    			.stream()
    			.filter(rule -> rule.isTriggered(knowledgeBase))
    			.collect(Collectors.toList());
    }
	@Override
	public List<Clause> getAllFacts() {
		return knowledgeBase.getFacts();
	}
	
	protected boolean fireRule(List<Rule> conflictingRules) {
        boolean hasRule2Fire = false;
        
        for (Rule rule : conflictingRules) {
            if (!rule.isFired()) {
                hasRule2Fire = true;
                rule.fire(knowledgeBase);
            }
        }

        return hasRule2Fire;
    }
}
