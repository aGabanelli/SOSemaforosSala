package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCavaleiro;

public class Principal {

	public static void main(String[] args) {
		
		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int i = 0; i < 4 ; i ++) {
			int passo = (int)(Math.random()*4)+2;
			Thread t = new ThreadCavaleiro(i, passo, semaforo);
			t.start();
		}
		
		
	}
	
}
