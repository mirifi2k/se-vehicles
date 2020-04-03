package ro.upt.se.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import ro.upt.se.Response;
import ro.upt.se.SurveyController;
import ro.upt.se.engine.InferenceEngine;
import ro.upt.se.engine.InferenceEngineImpl;
import ro.upt.se.engine.Rule;
import ro.upt.se.parser.Parser;
import ro.upt.se.ui.i18n.MessagesNLS;

public class ResultDialog extends Dialog {

	private Object result;
	private Shell shell;
	
	private SurveyController controller;
	private Rule consequence;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ResultDialog(Shell parent, int style, SurveyController controller) {
		super(parent, style);
		setText(MessagesNLS.PAGE_TITLE);
		
		this.controller = controller;
		this.consequence = this.runInferenceEngine();
	}

	public Object open() {
		createContents();
		
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		
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
		shell.setLayout(new GridLayout(3, false));

		Label label = new Label(shell, SWT.NONE);
		label.setText(MessagesNLS.SUITED_VEHICLE);
		
		GridData gd = new GridData(SWT.CENTER, SWT.FILL, true, false);
		gd.horizontalSpan = 3;
		label.setLayoutData(gd);
		
		Label img = new Label(shell, SWT.NONE);
		img.setLayoutData(gd);
		
		Label vehicle = new Label(shell, SWT.NONE);
		vehicle.setLayoutData(gd);
		// vehicle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		
		if (consequence != null) {
			vehicle.setText(controller.getAnswer(2) + " " + consequence.getConsequence().getValue());
			img.setImage(new Image(getParent().getDisplay(), getClass().getResource("/" + consequence.getImage()).getFile()));
		} else {
			vehicle.setText(MessagesNLS.NO_VEHICLE_FOUND);
		}
		
		Button prevButton = new Button(shell, SWT.NONE);
		Button closeButton = new Button(shell, SWT.NONE);
		Button nextButton = new Button(shell, SWT.NONE);
		
//		closeButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
//		prevButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
//		prevButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		
		closeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		
		GridData gd2 = new GridData(SWT.CENTER, SWT.FILL, true, true);
		gd2.widthHint = 50;
		
		closeButton.setText("Close");
		closeButton.setLayoutData(gd2);
		
		prevButton.setText("<");
		prevButton.setLayoutData(gd2);
		
		nextButton.setText(">");
		nextButton.setLayoutData(gd2);
	}
	
	private Rule runInferenceEngine() {
		InferenceEngine inferenceEngine = new InferenceEngineImpl();
		Response response = controller.getResponse();
		Parser p = new Parser();
		
		p.setFile(new File("C:\\Users\\razva\\Desktop\\cars.txt"));
		try {
			p.parseFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inferenceEngine.addAllRules(p.getRules());
		
		inferenceEngine.addFact(response.getPrefferedBrand());
		inferenceEngine.addFact(response.getEngineType());
		inferenceEngine.addFact(response.getDesiredPrice());
		inferenceEngine.addFact(response.getEngineCapacity());
		inferenceEngine.addFact(response.getAwd());
		inferenceEngine.addFact(response.getPowerCapacity());
		inferenceEngine.addFact(response.getPrefferedBody());
		inferenceEngine.addFact(response.getPrefferedTransmission());
		inferenceEngine.addFact(response.getSeatsNumber());
		
		// TODO modify
		if (inferenceEngine.getConsequence().size() > 0) {
			return inferenceEngine.getConsequence().get(0);
		}
		return null;
	}

}

