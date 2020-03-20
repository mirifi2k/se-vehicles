package ro.upt.se;

import java.util.Arrays;
import java.util.List;

import ro.upt.se.Response;

public class SurveyModel {
	private static final int QUESTIONS_NO = 9;
	
	private String[] answers;
	private Response response;
	
	public SurveyModel() {
		answers = new String[QUESTIONS_NO];
	}
	
	public String getAnswer(int idx) {
		return answers[idx];
	}
	
	public void setAnswer(int idx, String answer) {
		answers[idx] = answer;
	}
	
	public List<String> getAnswers() {
		return Arrays.asList(this.answers);
	}
	
	public void setResponse(Response response) {
		this.response = response;
	}
	
	public Response getResponse() {
		return this.response;
	}
}
