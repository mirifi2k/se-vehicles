package ro.upt.se.ui;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import ro.upt.se.Response;
import ro.upt.se.SurveyController;
import ro.upt.se.ui.i18n.MessagesNLS;

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
//    	
//    	response.setEngineType(Utils.mapOption(answers.get(0)));
//    	response.setModel(answers.get(1));
//    	response.setType(Utils.mapOption(answers.get(2)));
//    	
//    	if (answers.get(3).contains("Over")) {
//    		response.setCapacity(new ComparingValue(">=", Utils.getNumberFromString(answers.get(3))));
//    	} else {
//    		response.setCapacity(new ComparingValue("<=", Utils.getNumberFromString(answers.get(3))));
//    	}
//    	
//    	if (answers.get(4).contains("Over")) {
//    		response.setHorsePower(new ComparingValue(">=", Utils.getNumberFromString(answers.get(4))));
//    	} else {
//    		response.setHorsePower(new ComparingValue("<=", Utils.getNumberFromString(answers.get(4))));
//    	}
//    	
//    	if (answers.get(4).equals("Does not matter")) {
//    		response.setTransmission("any");
//    	} else {
//    		response.setTransmission(Utils.mapOption(answers.get(5)));
//    	}
//    	
//    	response.setAge(Utils.mapOption(answers.get(6)));
//    	response.setSeatsNo(Integer.valueOf(Utils.mapOption(answers.get(7))));
//    	
//    	if (answers.get(8).equals("Yes")) {
//    		response.setAWD(1);
//    	} else if (answers.get(8).equals("No")) {
//    		response.setAWD(0);
//    	} else {
//    		response.setAWD(-1);
//    	}
		return true;
	}

	@Override
	public boolean canFinish() {
		return ninthPage.isPageComplete();
	}
}
