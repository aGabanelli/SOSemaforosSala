package controller;

import java.util.concurrent.Semaphore;

public class AtletaThread extends Thread {
	private final String nome;
    private final Semaphore armasSemaforo;
    private int pontosCorrida;
    private int pontosTiro;
    private int pontosTotal;
    private static int posicaoCorrida = 0;

    public AtletaThread(String nome, Semaphore armasSemaforo) {
        this.nome = nome;
        this.armasSemaforo = armasSemaforo;
    }

    @Override
    public void run() {
        try {
            corrida();
            pegarArma();
            tiro();
            ciclismo();
            calcularPontuacao();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    private void corrida() throws InterruptedException {
        int distanciaPercorrida = 0;
        System.out.println(nome + " começou a correr.");
        while (distanciaPercorrida < 3000) { 
            int distancia = (int)(Math.random()*26) + 20;
            distanciaPercorrida += distancia;
            Thread.sleep(30);
        }
        synchronized (AtletaThread.class) { // Bloqueia as threads para garantir que a ordem de chegada seja correta
            pontosCorrida = 250 - (posicaoCorrida * 10); 
            posicaoCorrida++;
        }
        System.out.println(nome + " terminou a corrida com " + pontosCorrida + " pontos.");
    }

    
    
    private void pegarArma() throws InterruptedException {
        armasSemaforo.acquire();
        System.out.println(nome + " pegou uma arma para os tiros.");
    }
    

    private void tiro() throws InterruptedException {
        pontosTiro = 0;
        for (int i = 1; i <= 3; i++) {
            int tempoTiro = (int)(Math.random()*3001) + 500; 
            Thread.sleep(tempoTiro);
            int pontuacaoTiro = (int)(Math.random()*11) + 0;
            pontosTiro += pontuacaoTiro;
            System.out.println(nome + " fez o tiro " + i + " e marcou " + pontuacaoTiro + " pontos.");
        }
        armasSemaforo.release(); 
        System.out.println(nome + " terminou os tiros.");
    }
    

    private void ciclismo() throws InterruptedException {
        int distanciaPercorrida = 0;
        System.out.println(nome + " começou a pedalar.");
        while (distanciaPercorrida < 5000) { 
            int distancia = (int)(Math.random()*41) + 30;
            distanciaPercorrida += distancia;
            Thread.sleep(40);
        }
        System.out.println(nome + " terminou o ciclismo.");
    }

    
    private void calcularPontuacao() {
        this.pontosTotal = this.pontosCorrida + this.pontosTiro;
        System.out.println(nome + " terminou a prova com " + pontosTotal + " pontos.");
    }
    

    public int getPontosTotal() {
        return pontosTotal;
    }

    public String getNome() {
        return nome;
    }
    
    
}

