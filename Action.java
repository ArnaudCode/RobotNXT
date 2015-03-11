import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Action implements Runnable {

	private String action = "";
	private int vitesse = 0;
	public static DifferentialPilot pilot;

	public Action(String action, String vitesse) {
		this.action = action;
		this.vitesse = Integer.parseInt(vitesse);
	}

	public void run() {
		pilot = new DifferentialPilot(5.5, 11.59, Motor.A, Motor.B);
		
		pilot.setTravelSpeed(vitesse);
		
		System.out.println(action);
		System.out.println(vitesse);

		if (action.equals("avancer")) {
			while (!Thread.currentThread().isInterrupted()) {
				pilot.forward();
			}
			pilot.stop();
		}
		if (action.equals("droite")) {
			pilot.setTravelSpeed(vitesse/2);
			while (!Thread.currentThread().isInterrupted()) {
				pilot.rotateLeft();
			}
			pilot.stop();
		}
		if (action.equals("gauche")) {
			pilot.setTravelSpeed(vitesse/2);
			while (!Thread.currentThread().isInterrupted()) {
				pilot.rotateRight();
			}
			pilot.stop();
		}
		if (action.equals("reculer")) {
			while (!Thread.currentThread().isInterrupted()) {
				pilot.backward();
			}
			pilot.stop();
		}
	}

}
