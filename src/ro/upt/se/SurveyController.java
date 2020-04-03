package ro.upt.se;

import java.util.List;

public class SurveyController {
	private SurveyModel model;
	private boolean wizardCanceled = false;
	
	public SurveyController(SurveyModel model) {
		this.model = model;
	}
	
	public boolean isWizardCanceled() {
		return wizardCanceled;
	}

	public void setWizardCanceled(boolean wizardCanceled) {
		this.wizardCanceled = wizardCanceled;
	}

	public void setAnswer(int idx, String answer) {
		this.model.setAnswer(idx, answer);
	}
	
	public List<String> getAnswers() {
		return this.model.getAnswers();
	}
	
	public String getAnswer(int idx) {
		return this.model.getAnswer(idx);
	}
	
	public SurveyModel getModel() {
		return this.model;
	}
	
	public void setResponse(Response response) {
		this.model.setResponse(response);
	}
	
	public Response getResponse() {
		return this.model.getResponse();
	}
}
