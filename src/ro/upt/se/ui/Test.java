package ro.upt.se.ui;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import ro.upt.se.SurveyController;
import ro.upt.se.SurveyModel;
import ro.upt.se.ui.i18n.MessagesNLS;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class Test {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Test window = new Test();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(display);
		
		shell.open();
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Display display) {
		shell = new Shell();
		shell.setMinimumSize(new Point(480, 240));
		shell.setSize(480, 240);
		shell.setText(MessagesNLS.APP_TITLE);
		shell.setLayout(new GridLayout(1, false));
		
		Label img = new Label(shell, SWT.NONE);
		img.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		System.out.println(getClass().getResource("/resized_a7.png").getFile());
		img.setImage(new Image(display, getClass().getResource("/resized_a7.png").getFile()));
		
		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		label.setText(MessagesNLS.APP_TITLE);
		
		Button startWizard = new Button(shell, SWT.NONE);
		startWizard.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		startWizard.setText("Start Survey");
		
		startWizard.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event e) {
				SurveyController controller = new SurveyController(new SurveyModel());
				
				WizardDialog dlg = new WizardDialog(shell, new SurveyWizard(controller));
			    dlg.open();
			    
			    /*
			     * After the dialog is closed -> open the result dialog
			     */
			    
			    if (!shell.isDisposed()) {
			    	ResultDialog resultDialog = new ResultDialog(shell, SWT.BORDER, controller);
			    	resultDialog.open();
			    }
			}
		});
	}
}
