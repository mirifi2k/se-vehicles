package ro.upt.se.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ro.upt.se.clauses.Clause;
import ro.upt.se.clauses.EqualsClause;
import ro.upt.se.clauses.OrClause;
import ro.upt.se.engine.Rule;

/**
 * This class represents an implementation of a CSV parser.
 * 
 * @author razvanl
 * @author adamk
 * @author bogdanm
 */
public class Parser {
	private File file = null;
	private List<Rule> rules;
	
	public Parser() {
		this.rules = new ArrayList<>();
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void parseFile() throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(this.file));
		int idx = 1;
		
		while ((line = br.readLine()) != null) {
			String[] content = line.split(",");
			
			if (content.length < 12) {
				break;
			}
			
			Rule rule = new Rule("rule_" + idx);
			rule.addAntecedent(new EqualsClause("brand", content[0]));
			rule.addAntecedent(new EqualsClause("fuel", content[1]));
			rule.addAntecedent(new EqualsClause("capacity", content[2]));
			rule.addAntecedent(new EqualsClause("power", content[3]));
			
			String[] types = content[4].split("/");
			if (types.length == 1) {
				rule.addAntecedent(new EqualsClause("type", types[0]));
			} else {
				Clause[] orClauses = new Clause[types.length];
				
				for (int i = 0; i < types.length; i++) {
					orClauses[i] = new EqualsClause("type", types[i]);
				}
				
				rule.addAntecedent(new OrClause("type", orClauses));
			}

			rule.addAntecedent(new EqualsClause("seats", content[6]));
			rule.addAntecedent(new EqualsClause("price", content[7]));
			
			String[] transmissions = content[8].split("/");
			if (transmissions.length == 1) {
				rule.addAntecedent(new EqualsClause("transmission", transmissions[0]));
			} else {
				rule.addAntecedent(new EqualsClause("transmission", "both"));
			}
			
			rule.addAntecedent(new EqualsClause("awd", content[10]));
			rule.setImage(content[11] + ".png");
			
			rule.setConsequence(new EqualsClause("model", content[9]));
		
			rules.add(rule);
			idx ++;
		}
		
		br.close();
	}
	
	public List<Rule> getRules() {
		return this.rules;
	}
}
