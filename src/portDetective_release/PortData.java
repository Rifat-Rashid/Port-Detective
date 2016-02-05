package portDetective_release;

import java.io.IOException;
import java.net.Socket;

import org.eclipse.swt.widgets.Display;

public class PortData {

	private int timeout;
	private int port;
	private boolean isOpen;

	public PortData() {
		this.isOpen = false;
	}

	public PortData(int port) {
		this.port = port;
		this.isOpen = false;
	}

	public void startSocket() {
		try {
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					try {
						Socket s = new Socket(STORAGE.host_IP, port);
						isOpen = true;
						s.close();
					} catch (IOException e) {
						isOpen = false;
					}finally{
						
						//Do incrementing operations over here!
						Runnable displayRunnable = new Runnable(){
							@Override
							public void run(){
								//conduct operation here
								if(isOpen){
									launcher.list.add("Port: "
											+ (port)
											+ " Unoccupied");;
								}
								launcher.label_3.setText(STORAGE.ports_gone_through
										+ "/"
										+ STORAGE.ports_to_discover);
								launcher.progressBar.setSelection(launcher.progressBar.getSelection() + 1);
								STORAGE.ports_gone_through++;
							}
						};
						//runnable UI thread: avoids thread exception!
						Display.getDefault().syncExec(displayRunnable);
					}
				}
			};
			//do operations in the background thread
			new Thread(r).start();
		} catch (Exception e) {
			this.isOpen = false;
		}
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
}
