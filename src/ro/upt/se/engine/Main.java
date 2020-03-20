package ro.upt.se.engine;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.graphics.Image;

import ro.upt.se.clauses.Clause;
import ro.upt.se.clauses.EqualsClause;
import ro.upt.se.clauses.GreaterEqualClause;
import ro.upt.se.clauses.LessClause;
import ro.upt.se.clauses.LessEqualClause;
import ro.upt.se.clauses.NotEqualClause;
import ro.upt.se.clauses.OrClause;
import ro.upt.se.parser.Parser;

public class Main {
	private static InferenceEngine getInferenceEngine() {
	    return new InferenceEngineImpl();
	}
	
	public static void testBackwardChain(InferenceEngine rie) {
		rie.addFact(new EqualsClause("brand", "BMW"));
	    rie.addFact(new EqualsClause("fuel", "diesel"));
	    rie.addFact(new EqualsClause("capacity", "2000"));
	    rie.addFact(new LessEqualClause("power", "306"));
	    rie.addFact(new EqualsClause("type", "sedan"));
	    rie.addFact(new EqualsClause("seats", "5"));
	    rie.addFact(new LessEqualClause("price", "90000"));
	    rie.addFact(new GreaterEqualClause("price", "50000"));
	    rie.addFact(new EqualsClause("transmission", "automatic"));
	    rie.addFact(new EqualsClause("awd", "no"));

	    System.out.println("Infer: vehicle");

	    Rule conclusion = rie.getConsequence();
	    //String image = Main.class.getResource("/" + conclusion.getImage()).getFile();

	    System.out.println("Conclusion: " + conclusion.getConsequence());
	}
	
	public static void main(String[] args) {
		Parser p = new Parser();
		
		p.setFile(new File("C:\\Users\\razva\\Desktop\\cars.txt"));
		try {
			p.parseFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InferenceEngine rie = getInferenceEngine();
		rie.addAllRules(p.getRules());
		
		testBackwardChain(rie);
	}
}
