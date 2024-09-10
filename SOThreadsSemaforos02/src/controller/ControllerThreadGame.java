package controller;
import java.util.Random;
import java.util.concurrent.*;

public class ControllerThreadGame extends Thread{
	
	private Semaphore semaforo;
	private int tidPrato;
	private Random random = new Random();
	
	public ControllerThreadGame (Semaphore semaforo, int tidPrato) {
		this.semaforo = semaforo;	
		this.tidPrato = tidPrato;
	}
	
	public void run() {
		calc();
	}
	
	public void calc () {
		//int tidPrato = (int) threadId();
		if (tidPrato % 2 != 0) {
			SopaDeCebola ();
			
		} else {
			LasanhaABolonhesa ();
		}
	}
	
	public void SopaDeCebola () {
		try {
			Cozimento (0.5, 0.8, "Sopa de Cebola");
			Entrega (0.5, "Sopa de Cebola");
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void LasanhaABolonhesa () {
		try {
			Cozimento (0.6, 1.2, "Lasanha à Bolonhesa");
			Entrega (0.5, "Lasanha à Bolonhesa");
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void Cozimento (double tempInicio, double tempFim, String tipoPrato) throws InterruptedException {
		double tempTotal = (tempInicio + (tempFim - tempInicio) * random.nextDouble());
		int intervalo = (int) ((tempTotal)/0.1);
		int percentual;
		
		System.out.println(tipoPrato + " (#" + tidPrato + ") iniciado.");
		
		for (int i = 0; i <= intervalo; i++) {
            percentual = (i * 100) / intervalo;
            System.out.println(tipoPrato + " (#" + tidPrato + ") cozinhando: " + percentual + "% concluído.");
            Thread.sleep(100);
		}
	}
	
	public void Entrega (double tempEntrega, String tipoPrato) throws InterruptedException {
		semaforo.acquire();
		System.out.println("\n" + tipoPrato + " (#" + tidPrato + ") entregue!\n");
		Thread.sleep((long) (tempEntrega * 1000));
		semaforo.release();
	}

}
