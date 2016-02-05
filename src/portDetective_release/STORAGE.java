package portDetective_release;

import java.net.InetAddress;
import java.util.ArrayList;

public class STORAGE {
	public static InetAddress host_IP;
	public static ArrayList<Integer> open_ports = new ArrayList<Integer>();
	public static int ports_to_discover;
	public static int ports_gone_through;
}
