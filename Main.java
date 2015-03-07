import java.io.DataInputStream;
import java.io.DataOutputStream;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;

public class Main {

	public static DataOutputStream dataOut;
	public static USBConnection USBLink;
	public static DataInputStream dataIn;

	private static Thread thread = null;
	private static String action = "";
	private static String vitesse = "";

	public static void main(String[] args) {
		/* Initialisation */
		connection();

		try {
			while (action != null) {
				action = dataIn.readUTF();
				vitesse = dataIn.readUTF();
				if (action.equals("stop")) {
					thread.interrupt();
				} else {
					thread = new Thread(new Action(action, vitesse));
					thread.start();
				}
			}
		} catch (Exception e) {
		}
	}

	public static void connection() {
		System.out.println("Ecoute...");
		USBLink = USB.waitForConnection();
		dataOut = USBLink.openDataOutputStream();
		dataIn = USBLink.openDataInputStream();
	}

}
