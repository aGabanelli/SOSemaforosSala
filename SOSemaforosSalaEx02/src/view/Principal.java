package view;

import java.util.Random;
import java.util.concurrent.Semaphore;
import controller.ThreadAeroporto;

public class Principal {

	public static void main(String[] args) {
        Semaphore areaDecolagemSemaforo = new Semaphore(2);
        Random random = new Random();

        for (int i = 1; i <= 12; i++) {
            String nomePista = random.nextBoolean() ? "Norte" : "Sul";
            ThreadAeroporto aviao = new ThreadAeroporto("Avião " + i, nomePista, areaDecolagemSemaforo);
            aviao.start();
        }
    }
	
}
