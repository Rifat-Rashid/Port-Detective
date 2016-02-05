package portDetective_release;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
	private static int current_element;
	private static List list;
	private static Label label_3;
	private static ProgressBar progressBar;
	private static int ports_gone_through = 0;
	private static int i;

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

		progressBar = new ProgressBar(this, SWT.NONE);
		progressBar.setBounds(10, 131, 198, 25);

		label_3 = new Label(this, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setBounds(148, 116, 55, 15);
		label_3.setText("0");
		createContents();

		list = new List(this, SWT.BORDER);
		list.setBounds(10, 198, 198, 44);

		Button scan_button = new Button(this, SWT.NONE);
		scan_button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// send Data packet here
				try {
					STORAGE.host_IP = InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					progressBar.setSelection(0);
					ports_gone_through = 0;
					// try to convert ports into integers
					final int startPort = Integer.valueOf(port_textbox
							.getText().toString());
					final int endPort = Integer.valueOf(text.getText()
							.toString());
					StringBuilder targetIP_string = new StringBuilder();
					targetIP_string.append(ip_textbox_1.getText().toString()
							+ ".");
					targetIP_string.append(ip_textbox_2.getText().toString()
							+ ".");
					targetIP_string.append(ip_textbox_3.getText().toString());
					final ArrayList<Integer> successful_ports_list = new ArrayList<Integer>();
					final InetAddress host_ip = InetAddress.getByName("10.187.9.36");
					System.out.println(InetAddress.getLocalHost().getHostAddress());
					current_element = startPort;
					final int ports_to_discover = endPort - startPort;
					progressBar.setMaximum(endPort);
					progressBar.setMinimum(startPort);

					Runnable r = new Runnable() {
						@Override
						public void run() {
							for (i = 0; i <= endPort - startPort; i++) {
								Runnable a = new Runnable() {
									// isReachable stores if port is open or not
									boolean isReachable = false;
									int port_num = i + startPort;

									@Override
									public void run() {
										try {/*
											if (host_ip.isReachable(port_num)) {
												successful_ports_list
														.add(port_num);
												isReachable = true;
											} else {
												// do nothing :(
											}
											*/
											Socket s = new Socket(host_ip, port_num);
											successful_ports_list.add(port_num);
											isReachable = true;
											s.close();
										} catch (Exception e) {
											isReachable = false;
										}

										// ui thread
										Runnable uiRunnable = new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated method
												// stub
												if (isReachable) {
													list.add("Port: "
															+ (port_num)
															+ " Unoccupied");
												}
												label_3.setText(ports_gone_through
														+ "/"
														+ ports_to_discover);
												progressBar
														.setSelection(progressBar
																.getSelection() + 1);
												ports_gone_through++;
											}
										};
										Display.getDefault().syncExec(
												uiRunnable);
										isReachable = false;
									}
								};
								new Thread(a).start();
							}
						};

					};
					new Thread(r).start();
				} catch (Exception io) {
					io.printStackTrace();

				}
			}
		});
		scan_button.setBounds(76, 167, 66, 25);
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

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Open Port");
		setSize(234, 290);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
