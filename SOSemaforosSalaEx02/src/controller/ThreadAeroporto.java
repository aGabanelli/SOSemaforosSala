package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread {
	
	private final String nomeAviao;
	private final String nomePista;
	private final Semaphore pistaSemaforo;
	private final Semaphore areaDecolagemSemaforo;

	public ThreadAeroporto(String nomeAviao, String nomePista, Semaphore areaDecolagemSemaforo) {
		this.nomeAviao = nomeAviao;
		this.nomePista = nomePista;
		this.pistaSemaforo = new Semaphore(1); 
		this.areaDecolagemSemaforo = areaDecolagemSemaforo; 
	}

	@Override
	public void run() {
		try {
			pistaSemaforo.acquire();
			System.out.println(nomeAviao + " está na pista " + nomePista + " para iniciar as manobras.");
			fase("manobra", 300, 700);
			fase("taxiar", 500, 1000);

			areaDecolagemSemaforo.acquire(); 
			fase("decolagem", 600, 800);
			fase("afastamento", 300, 800);
			areaDecolagemSemaforo.release();

			System.out.println(nomeAviao + " decolou com sucesso da pista " + nomePista);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			pistaSemaforo.release();
		}
	}

	private void fase(String fase, int minTempo, int maxTempo) throws InterruptedException {
		int tempo = new Random().nextInt(maxTempo - minTempo + 1) + minTempo;
		System.out.println(nomeAviao + " está na fase de " + fase + " por " + tempo + " ms.");
		Thread.sleep(tempo);
	}

}
