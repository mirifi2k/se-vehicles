package ro.upt.se.ui;

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

import ro.upt.se.SurveyController;
import ro.upt.se.ui.i18n.MessagesNLS;
public class FirstPage extends WizardPage {

	private String[] answers = {
		MessagesNLS.QUESTION_1_ANSWER_1, MessagesNLS.QUESTION_1_ANSWER_2, MessagesNLS.QUESTION_1_ANSWER_3,
		MessagesNLS.QUESTION_1_ANSWER_4
	};
	
	private Button[] answerButton = new Button[4];
	private boolean pageComplete = false;
	private SurveyController controller;
	  
	public FirstPage(String pageName, SurveyController controller) {
		super(pageName, MessagesNLS.Q1, MessagesNLS.DEFAULT_IMAGE);
		
		this.controller = controller;
		this.setDescription(MessagesNLS.PAGE_DESCRIPTION);
	}
	  
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
			
		Label questionLabel = new Label(composite, SWT.LEFT);
		questionLabel.setText(MessagesNLS.QUESTION_1);
		
		Composite answersComposite = new Composite(composite, SWT.NONE);
		answersComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		for (int i = 0; i < answerButton.length; i++) {
			answerButton[i] = new Button(answersComposite, SWT.RADIO);
			answerButton[i].setText(answers[i]);

			answerButton[i].addListener(SWT.Selection, new Listener() {
		
				@Override
				public void handleEvent(Event e) {
					controller.setAnswer(0, ((Button)e.widget).getText());
					
					pageComplete = true;
					getWizard().getContainer().updateButtons();
				}
			});
		}
		
		setControl(composite);
	}

	@Override
	public IWizardPage getNextPage() {
		return getWizard().getPage("Page 2");
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return pageComplete;
	}
}