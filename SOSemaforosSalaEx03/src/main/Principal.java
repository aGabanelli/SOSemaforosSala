package main;

import java.util.concurrent.Semaphore;

import controller.AtletaThread;

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		Semaphore armasSemaforo = new Semaphore(5); // Apenas 5 armas disponíveis para os tiros
		AtletaThread[] atletas = new AtletaThread[25];

        for (int i = 0; i < 25; i++) {
            atletas[i] = new AtletaThread("Atleta " + (i + 1), armasSemaforo);
            atletas[i].start();
        }

        // Espera todos os atletas terminarem
        for (AtletaThread atleta : atletas) {
            atleta.join();
        }

        ordenarAtletasPorPontuacao(atletas);

        System.out.println("\n--- Ranking Final ---");
        for (int i = 0; i < atletas.length; i++) {
            System.out.println((i + 1) + "º lugar: " + atletas[i].getNome() + " - " + atletas[i].getPontosTotal() + " pontos");
        }
    }
	
	
	private static void ordenarAtletasPorPontuacao(AtletaThread[] atletas) {
		int n = atletas.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (atletas[j].getPontosTotal() < atletas[j + 1].getPontosTotal()) {
					AtletaThread temp = atletas[j];
					atletas[j] = atletas[j + 1];
					atletas[j + 1] = temp;
				}
			}
		}
	}
}
