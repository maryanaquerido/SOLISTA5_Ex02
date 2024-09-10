package view;
import controller.ControllerThreadGame;
import java.util.concurrent.Semaphore;

public class PrincipalThreadGame {
	public static void main (String[]args) {
		
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 5; i++) {
			ControllerThreadGame thread = new ControllerThreadGame(semaforo, i);
			thread.start();
		}
	}

}
