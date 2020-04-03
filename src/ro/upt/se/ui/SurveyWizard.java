package ro.upt.se.ui;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

import ro.upt.se.Response;
import ro.upt.se.SurveyController;
import ro.upt.se.clauses.Clause;
import ro.upt.se.clauses.EqualsClause;
import ro.upt.se.clauses.GreaterEqualClause;
import ro.upt.se.clauses.LessEqualClause;
import ro.upt.se.clauses.NotEqualClause;
import ro.upt.se.clauses.OrClause;
import ro.upt.se.ui.i18n.MessagesNLS;
import ro.upt.se.utils.Utils;

public class SurveyWizard extends Wizard {
	
	private WizardPage firstPage;
	private WizardPage secondPage;
	private WizardPage thirdPage;
	private WizardPage fourthPage;
	private WizardPage fifthPage;
	private WizardPage sixthPage;
	private WizardPage seventhPage;
	private WizardPage eighthPage;
	private WizardPage ninthPage;
	
	private SurveyController controller;

	public SurveyWizard(SurveyController controller) {
		setWindowTitle(MessagesNLS.PAGE_TITLE);
		
		firstPage = new FirstPage("Page 1", controller);
		secondPage = new SecondPage("Page 2", controller);
		thirdPage = new ThirdPage("Page 3", controller);
		fourthPage = new FourthPage("Page 4", controller);
		fifthPage = new FifthPage("Page 5", controller);
		sixthPage = new SixthPage("Page 6", controller);
		seventhPage = new SeventhPage("Page 7", controller);
		eighthPage = new EighthPage("Page 8", controller);
		ninthPage = new NinthPage("Page 9", controller);
		
		this.controller = controller;
	}

	@Override
	public void addPages() {
		addPage(firstPage);
		addPage(secondPage);
		addPage(thirdPage);
		addPage(fourthPage);
		addPage(fifthPage);
		addPage(sixthPage);
		addPage(seventhPage);
		addPage(eighthPage);
		addPage(ninthPage);
	}

	@Override
	public boolean performFinish() {
		Response response = new Response();
		List<String> answers = controller.getAnswers();
		
		response.setEngineType(new EqualsClause("fuel", Utils.mapOption(answers.get(0))));
		if (answers.get(1).equals("Does not matter")) {
			response.setPrefferedBrand(new NotEqualClause(new EqualsClause("brand", "none")));
		} else {
			response.setPrefferedBrand(new EqualsClause("brand", answers.get(1)));
		}
		
		response.setPrefferedBody(new EqualsClause("type", answers.get(2).toLowerCase()));
		
		if (answers.get(3).contains("Over")) {
			response.setEngineCapacity(new GreaterEqualClause("capacity", String.valueOf(Utils.getNumberFromString(answers.get(3)))));
		} else {
			response.setEngineCapacity(new LessEqualClause("capacity", String.valueOf(Utils.getNumberFromString(answers.get(3)))));
		}
		
		if (answers.get(4).contains("Over")) {
			response.setPowerCapacity(new GreaterEqualClause("power", String.valueOf(Utils.getNumberFromString(answers.get(4)))));
		} else {
			response.setPowerCapacity(new LessEqualClause("power", String.valueOf(Utils.getNumberFromString(answers.get(4)))));
		}
		
		if (answers.get(5).equals("Does not matter")) {
			response.setPrefferedTransmission(
					new OrClause("transmission", 
							new Clause[] {new EqualsClause("transmission", "manual"), new EqualsClause("transmission", "automatic")}));
		} else {
			response.setPrefferedTransmission(new EqualsClause("transmission", Utils.mapOption(answers.get(5))));
		}
		
		if (answers.get(6).contains("Over")) {
			response.setDesiredPrice(new GreaterEqualClause("price", String.valueOf(Utils.getNumberFromString(answers.get(6)))));
		} else {
			response.setDesiredPrice(new LessEqualClause("price", String.valueOf(Utils.getNumberFromString(answers.get(6)))));
		}
		
		response.setSeatsNumber(new EqualsClause("seats", Utils.mapOption(answers.get(7))));
		
		if (answers.get(8).equals("Does not matter")) {
			response.setAwd(new NotEqualClause(new EqualsClause("awd", "none")));
		} else {
			response.setAwd(new EqualsClause("awd", answers.get(8).toLowerCase()));
		}
		
		controller.setResponse(response);
		return true;
	}

	@Override
	public boolean canFinish() {
		return ninthPage.isPageComplete();
	}
}
