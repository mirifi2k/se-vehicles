package ro.upt.se.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
	private List<Rule> consequences;
	
	private int idx;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ResultDialog(Shell parent, int style, SurveyController controller) {
		super(parent, style);
		setText(MessagesNLS.PAGE_TITLE);
		
		this.controller = controller;
		this.consequences = this.runInferenceEngine();
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
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(3, false));

		Label label = new Label(shell, SWT.NONE);
		label.setText(consequences.size() > 1 ? MessagesNLS.SUITED_VEHICLES_PLURAL : MessagesNLS.SUITED_VEHICLE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 5));
		
		Label img = new Label(shell, SWT.NONE);
		img.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		
		Label vehicle = new Label(shell, SWT.NONE);
		vehicle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 5));
		// vehicle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		
		if (consequences != null && !consequences.isEmpty()) {
			String horsePower = consequences.get(0).getAntecedent("power");
			
			vehicle.setText(controller.getAnswer(2) + " " + consequences.get(0).getConsequence().getValue() + " (" + horsePower + "HP)");
			img.setImage(new Image(getParent().getDisplay(), getClass().getResource("/" + consequences.get(0).getImage()).getFile()));
		} else {
			vehicle.setText(MessagesNLS.NO_VEHICLE_FOUND);
		}
		
		Button prevButton = new Button(shell, SWT.NONE);
		Button closeButton = new Button(shell, SWT.NONE);
		Button nextButton = new Button(shell, SWT.NONE);
		
		closeButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		prevButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		nextButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		
		prevButton.setText("Previous");
		closeButton.setText("OK");
		nextButton.setText("Next");
		
		nextButton.setEnabled(consequences.size() > 1);
		prevButton.setEnabled(consequences.size() > 1);
		
		closeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		
		prevButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				idx = (idx - 1) < 0 ? (consequences.size() - 1) : (idx - 1);
				
				String horsePower = consequences.get(idx).getAntecedent("power");
				
				vehicle.setText(controller.getAnswer(2) + " " + consequences.get(idx).getConsequence().getValue() + " (" + horsePower + "HP)");
				img.setImage(new Image(getParent().getDisplay(), getClass().getResource("/" + consequences.get(idx).getImage()).getFile()));
				
				shell.layout();
			}
		});
		
		nextButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				idx = (idx + 1 > consequences.size() - 1) ? 0 : (idx + 1);
				
				String horsePower = consequences.get(idx).getAntecedent("power");
				
				vehicle.setText(controller.getAnswer(2) + " " + consequences.get(idx).getConsequence().getValue() + " (" + horsePower + "HP)");
				img.setImage(new Image(getParent().getDisplay(), getClass().getResource("/" + consequences.get(idx).getImage()).getFile()));
				
				shell.layout();
			}
		});
	}
	
	private List<Rule> runInferenceEngine() {
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
		return inferenceEngine.getConsequence();
	}
}

