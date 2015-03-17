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
				if (action.equals("accepter") || action.equals("avancer") || action.equals("droite") || action.equals("gauche") || action.equals("reculer")) {
					if(thread != null)
					{
						thread.interrupt();
					}
					thread = new Thread(new Action(action, vitesse));
					thread.start();
				} else {
					if(thread != null)
					{
						thread.interrupt();
					}
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
