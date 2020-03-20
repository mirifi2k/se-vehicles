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

public class Final extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Final(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
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
		img.setImage(new Image(getParent().getDisplay(), getClass().getResource("/resized_a7.png").getFile()));
		img.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Label vehicle = new Label(shell, SWT.NONE);
		// vehicle.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		vehicle.setText("BMW");
		vehicle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		btnNewButton.setText("Close");

	}

}
