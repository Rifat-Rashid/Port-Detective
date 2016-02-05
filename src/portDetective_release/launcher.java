package portDetective_release;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class launcher extends Shell {
	private Text ip_textbox_1;
	private Text ip_textbox_2;
	private Text ip_textbox_3;
	private Text port_textbox;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			launcher shell = new launcher(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public launcher(Display display) {
		super(display, SWT.SHELL_TRIM | SWT.PRIMARY_MODAL);
		setModified(true);

		ip_textbox_1 = new Text(this, SWT.BORDER);
		ip_textbox_1.setBounds(10, 41, 60, 21);

		ip_textbox_2 = new Text(this, SWT.BORDER);
		ip_textbox_2.setBounds(76, 41, 66, 21);

		ip_textbox_3 = new Text(this, SWT.BORDER);
		ip_textbox_3.setBounds(148, 41, 60, 21);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText(".");
		label_1.setBounds(143, 47, 10, 15);

		Label label = new Label(this, SWT.NONE);
		label.setBounds(72, 47, 10, 15);
		label.setText(".");

		ProgressBar progressBar = new ProgressBar(this, SWT.NONE);
		progressBar.setBounds(10, 131, 198, 17);

		List list = new List(this, SWT.BORDER);
		list.setBounds(10, 198, 198, 44);

		Button scan_button = new Button(this, SWT.NONE);
		scan_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// send Data packet here
				try {
					//try to convert ports into integers
					int startPort = Integer.valueOf(port_textbox.getText()
							.toString());
					int endPort = Integer.valueOf(text.getText().toString());
					//merge 3 input boxes into single string
					//!using string builder: dont want to create new Object each time
					StringBuilder targetIP_string = new StringBuilder();
					targetIP_string.append(ip_textbox_1.getText().toString() + ".");
					targetIP_string.append(ip_textbox_2.getText().toString() + ".");
					targetIP_string.append(ip_textbox_3.getText().toString());
				} catch (Exception io) {
					io.printStackTrace();

				}
			}
		});
		scan_button.setBounds(76, 154, 66, 25);
		scan_button.setText("Scan");

		Label lblTargetIp = new Label(this, SWT.NONE);
		lblTargetIp.setBounds(10, 20, 55, 15);
		lblTargetIp.setText("Target IP:");

		port_textbox = new Text(this, SWT.BORDER);
		port_textbox.setBounds(57, 89, 42, 21);

		text = new Text(this, SWT.BORDER);
		text.setBounds(123, 89, 42, 21);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setAlignment(SWT.CENTER);
		label_2.setBounds(102, 92, 18, 15);
		label_2.setText("-");

		Label lblPort = new Label(this, SWT.NONE);
		lblPort.setBounds(10, 68, 60, 15);
		lblPort.setText("Port Range::");

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setBounds(148, 116, 55, 15);
		label_3.setText("0");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(234, 290);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
