package ro.upt.se.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import ro.upt.se.SurveyController;
import ro.upt.se.ui.i18n.MessagesNLS;

public class ResultDialog extends Dialog {

	private Object result;
	private Shell shell;
	
	private Label imageLabel;
	private Label vehicleLabel;
	
	private SurveyController controller;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ResultDialog(Shell parent, int style, SurveyController controller) {
		super(parent, style);
		setText(MessagesNLS.PAGE_TITLE);
		
		this.controller = controller;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		Display display = getParent().getDisplay();
		
		createContents();
		shell.open();
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(480, 240);
		shell.setText(getText());
		shell.setLayout(new GridLayout(1, false));
		
		Label suitedVehicleLabel = new Label(shell, SWT.NONE);
		suitedVehicleLabel.setText(MessagesNLS.SUITED_VEHICLE);
		suitedVehicleLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		imageLabel = new Label(shell, SWT.NONE);
		imageLabel.setImage(new Image(getParent().getDisplay(), getClass().getResource("/resized_a7.png").getFile()));
		imageLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		vehicleLabel = new Label(shell, SWT.NONE);
		//vehicleLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		vehicleLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Button closeButton = new Button(shell, SWT.NONE);
		closeButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		
		closeButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		closeButton.setText("Close");
	}
}
