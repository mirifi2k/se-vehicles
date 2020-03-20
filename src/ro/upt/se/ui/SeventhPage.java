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
import org.eclipse.swt.widgets.Text;

import ro.upt.se.SurveyController;
import ro.upt.se.ui.i18n.MessagesNLS;

public class SeventhPage extends WizardPage {

	private String[] answers = {
		MessagesNLS.QUESTION_7_ANSWER_1, MessagesNLS.QUESTION_7_ANSWER_2, MessagesNLS.QUESTION_7_ANSWER_3, MessagesNLS.QUESTION_7_ANSWER_4
	};
	
	private Button[] answerButton = new Button[4];
	private Text overText;
	private Text belowText;
	
	private boolean pageComplete = false;
	private SurveyController controller;
	  
	public SeventhPage(String pageName, SurveyController controller) {
		super(pageName, MessagesNLS.Q7, MessagesNLS.DEFAULT_IMAGE);
		this.setDescription(MessagesNLS.PAGE_DESCRIPTION);
		this.controller = controller;
	}
	  
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
			
		Label questionLabel = new Label(composite, SWT.LEFT);
		questionLabel.setText(MessagesNLS.QUESTION_7);
		
		Composite answersComposite = new Composite(composite, SWT.NONE);
		answersComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		for (int i = 0; i < answers.length; i++) {
			answerButton[i] = new Button(answersComposite, SWT.RADIO);
			answerButton[i].setText(answers[i]);
			
			if (i == 2) {
				answerButton[i].addListener(SWT.Selection, new Listener() {
					
					@Override
					public void handleEvent(Event e) {
						overText.setEnabled(true);
						belowText.setEnabled(false);
						
						pageComplete = true;
						if (overText.getText().isEmpty()) {
							pageComplete = false;
						}
						
						getWizard().getContainer().updateButtons();
					}
				});
				
				overText = new Text(answersComposite, SWT.BORDER);
				overText.setEnabled(false);
				
				overText.addListener(SWT.Modify, new Listener() {

					@Override
					public void handleEvent(Event e) {
						controller.setAnswer(6, "Over " + overText.getText());
						
						if (overText.getText().isEmpty()) {
							pageComplete = false;
						} else if (!overText.getText().matches(("[-+]?\\d*\\.?\\d+"))) {
							setErrorMessage("You must only input digits!");
							pageComplete = false;
						} else {
							controller.setAnswer(6, "Over " + overText.getText());
							pageComplete = true;
						}
						
						getWizard().getContainer().updateButtons();
					}
				});
			} else if (i == 3) {
				answerButton[i].addListener(SWT.Selection, new Listener() {
					
					@Override
					public void handleEvent(Event e) {
						overText.setEnabled(false);
						belowText.setEnabled(true);
						
						pageComplete = true;
						if (belowText.getText().isEmpty()) {
							pageComplete = false;
						}
						
						getWizard().getContainer().updateButtons();
					}
				});
				
				belowText = new Text(answersComposite, SWT.BORDER);
				belowText.setEnabled(false);
				
				belowText.addListener(SWT.Modify, new Listener() {

					@Override
					public void handleEvent(Event e) {
						if (belowText.getText().isEmpty()) {
							pageComplete = false;
						} else if (!belowText.getText().matches(("[-+]?\\d*\\.?\\d+"))) {
							setErrorMessage("You must only input digits!");
							pageComplete = false;
						} else {
							controller.setAnswer(6, "Below " + belowText.getText());
							pageComplete = true;
						}
						
						getWizard().getContainer().updateButtons();
					}
				});
			} else {
				answerButton[i].addListener(SWT.Selection, new Listener() {
				
					@Override
					public void handleEvent(Event e) {
						controller.setAnswer(6, ((Button)e.widget).getText());
						
						overText.setEnabled(false);
						belowText.setEnabled(false);
						
						pageComplete = true;
						getWizard().getContainer().updateButtons();
					}
				});
			}
		}
		
		setControl(composite);
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return pageComplete;
	}

	@Override
	public IWizardPage getNextPage() {
		return getWizard().getPage("Page 8");
	}
}