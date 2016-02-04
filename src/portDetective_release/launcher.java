package portDetective_release;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ProgressBar;

public class launcher extends Shell {
	private Text ip_textbox_1;
	private Text ip_textbox_2;
	private Text ip_textbox_3;

	/**
	 * Launch the application.
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
		
		ProgressBar progressBar = new ProgressBar(this, SWT.NONE);
		progressBar.setBounds(10, 81, 198, 17);
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
