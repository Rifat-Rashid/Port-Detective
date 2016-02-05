package portDetective_release;

public class PortScanner {
	
	private int startPort;
	private int endPort;
	private PortData _portData;
	
	public PortScanner(int startPort, int endPort){
		this.startPort = startPort;
		this.endPort = endPort;
	}
	
	public void scanPorts(){
		Runnable r = new Runnable(){
			@Override
			public void run(){
				//begin port scan!
				for(int i = 0; i <=endPort - startPort + 1; i++){
					//go through
					_portData = new PortData(startPort + i);
					_portData.startSocket();
				}
			}
		};
		new Thread(r).start();
	}
	
	public int getStartPort() {
		return startPort;
	}
	
	public void setStartPort(int startPort) {
		this.startPort = startPort;
	}
	
	public int getEndPort() {
		return endPort;
	}
	
	public void setEndPort(int endPort) {
		this.endPort = endPort;
	}
}
