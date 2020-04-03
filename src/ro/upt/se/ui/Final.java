package ro.upt.se.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ro.upt.se.Response;
import ro.upt.se.SurveyController;
import ro.upt.se.clauses.Clause;
import ro.upt.se.engine.InferenceEngine;
import ro.upt.se.engine.InferenceEngineImpl;
import ro.upt.se.engine.Rule;
import ro.upt.se.parser.Parser;

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

public class Final extends Dialog {

	protected Object result;
	protected Shell shell;
	private SurveyController controller;
	
	private Rule consequence;

	public Final(Shell parent, int style, SurveyController controller) {
		super(parent, style);
		setText("SWT Dialog");
		
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
		shell.setLayout(new GridLayout(1, false));

		Label label = new Label(shell, SWT.NONE);
		label.setText("The suited vehicle for you is:");
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Label img = new Label(shell, SWT.NONE);
		img.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Label vehicle = new Label(shell, SWT.NONE);
		// vehicle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		
		if (consequence != null) {
			vehicle.setText(controller.getAnswer(2) + " " + consequence.getConsequence().getValue());
			img.setImage(new Image(getParent().getDisplay(), getClass().getResource("/" + consequence.getImage()).getFile()));
		}
		vehicle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Button closeButton = new Button(shell, SWT.NONE);
		closeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		closeButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		closeButton.setText("Close");
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
		
		//TODO
		return inferenceEngine.getConsequence().get(0);
	}

}
