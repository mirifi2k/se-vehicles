import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class TestDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TestDialog(Shell parent, int style) {
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
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(3, false));
		
		Label lblTest = new Label(shell, SWT.NONE);
		lblTest.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		lblTest.setText("The suited vehicle for you is:");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		lblNewLabel.setImage(new Image(getParent().getDisplay(), getClass().getResource("/resized_a7.png").getFile()));
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		lblNewLabel_1.setText("Audi A6 3.0 TDI Clean Diesel Quattro");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setEnabled(false);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		btnNewButton.setText("Previous");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		btnNewButton_1.setText("OK");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		btnNewButton_2.setText("Next");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

	}

}
