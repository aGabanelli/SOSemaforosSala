package controller;

import java.util.concurrent.Semaphore;

public class ThreadCavaleiro extends Thread {

	private int cavaleiro;
	private int passo;
	private int metrosPercorridos;
	private String item = "";
	private int porta;
	private Semaphore semaforo;
	private static int chegada = 2000;
	private static Boolean tocha = true;
	private static Boolean pedra = true;

	public ThreadCavaleiro(int cavaleiro, int passo, Semaphore semaforo) {
		this.cavaleiro = cavaleiro;
		this.passo = passo;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		try {
			semaforo.acquire();
			caminhada();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			abrirPorta();
		}

	}

	private void caminhada() {
		while (metrosPercorridos < 2000) {
			if (metrosPercorridos >= 500 && tocha == true) {
				item = "Tocha";
				tocha = false;
				passo = passo + 2;
				System.out.println("O cavaleiro " + cavaleiro + " pegou o item " + item + " | Total = " + metrosPercorridos);
			}
			if (metrosPercorridos >= 1500 && pedra == true && item == "") {
				item = "Pedra Brilhante";
				pedra = false;
				passo = passo + 2;
				System.out.println("O cavaleiro " + cavaleiro + " pegou o item " + item + " | Total = " + metrosPercorridos);
			}
			metrosPercorridos = metrosPercorridos + passo;
			System.out.println("O cavaleiro " + cavaleiro + " andou " + passo + "m | Total = " + metrosPercorridos + "m");
		}
		porta = (int) (Math.random() * 5) + 1;
	}

	private void abrirPorta() {

		if (porta == 3) {
			System.out.println("O cavaleiro " + cavaleiro + " escolheu a porta certa para a saída");
		} else {
			System.out.println("O cavaleiro " + cavaleiro + " escolheu a porta que tem um monstro e morreu");
		}

	}

}
