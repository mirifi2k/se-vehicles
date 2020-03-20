package ro.upt.se.ui;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import ro.upt.se.ComparingValue;
import ro.upt.se.SurveyController;
import ro.upt.se.Response;
import ro.upt.se.ui.i18n.MessagesNLS;
import ro.upt.se.utils.Utils;

public class NinthPage extends WizardPage {

	private String[] answers = {
		MessagesNLS.QUESTION_9_ANSWER_1, MessagesNLS.QUESTION_9_ANSWER_2, MessagesNLS.QUESTION_9_ANSWER_3
	};
	
	private Button[] answerButton = new Button[3];
	private boolean wizardFinished = false;
	private SurveyController controller;
	  
	public NinthPage(String pageName, SurveyController controller) {
		super(pageName, MessagesNLS.Q9, MessagesNLS.DEFAULT_IMAGE);
		this.setDescription(MessagesNLS.PAGE_DESCRIPTION);
		this.controller = controller;
	}
	  
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
			
		Label questionLabel = new Label(composite, SWT.LEFT);
		questionLabel.setText(MessagesNLS.QUESTION_9);
		
		Composite answersComposite = new Composite(composite, SWT.NONE);
		answersComposite.setLayout(new FillLayout(SWT.VERTICAL));
			
		for (int i = 0; i < answers.length; i++) {
			answerButton[i] = new Button(answersComposite, SWT.RADIO);
			answerButton[i].setText(answers[i]);
			
			answerButton[i].addListener(SWT.Selection, new Listener() {
				
				@Override
				public void handleEvent(Event e) {
					controller.setAnswer(8, ((Button)e.widget).getText());
					
					wizardFinished = true;
					getWizard().getContainer().updateButtons();
				}
			});
		}
		
		setControl(composite);
	}
	
	@Override
	public boolean isPageComplete() {
		return wizardFinished;
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return false;
	}

	@Override
	public IWizardPage getNextPage() {
		return null;
	}
}