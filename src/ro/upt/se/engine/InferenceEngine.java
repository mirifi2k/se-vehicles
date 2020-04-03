package ro.upt.se.engine;

import java.util.List;

import ro.upt.se.clauses.Clause;

/**
 * This interface represents the Interface Engine of the Expert System.
 * 
 * @author razvanl
 * @author adamk
 * @author bogdanm
 */
public interface InferenceEngine {

	/**
	 * This method adds a rule to the Inference Engine.
	 * 
	 * @param rule The rule to be added.
	 */
	void addRule(Rule rule);
	
	/**
	 * This method gets the consequence from a given goal.
	 * 
	 * @return The consequence clause.
	 */
	List<Rule> getConsequence();
	
	/**
	 * This method returns the Knowledge Base of the Expert System.
	 * 
	 * @return The Knowledge Base.
	 */
	KnowledgeBase getKnowledgeBase();
	
	/**
	 * This method returns all the facts from the Inference Engine.
	 * 
	 * @return A list containing the facts.
	 */
	List<Clause> getAllFacts();
	
	/**
	 * This method adds a fact to the Inference Engine.
	 * 
	 * @param fact The fact to be added.
	 */
	void addFact(Clause fact);
	
	/**
	 * 
	 * @return
	 */
	List<Rule> match();
	
	/**
	 * Adds all the rules to the Knowledge Base.
	 * 
	 * @param rules the rules that need to be added.
	 */
	void addAllRules(List<Rule> rules);
}
