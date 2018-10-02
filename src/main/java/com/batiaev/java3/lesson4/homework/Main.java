package com.batiaev.java3.lesson4.homework;

public class Main {

    private String currentSymbol = " ";

    public static void main(String[] args) {
        new Main();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Main() {
        Thread threadA = new Thread(() -> run(" C","A"));
        Thread threadB = new Thread(() -> run("A","B"));
        Thread threadC = new Thread(() -> run("B","C"));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public synchronized void run(String previousSymbols, String symbol) {
        for (int i = 0; i < 3; i++) {
            while (previousSymbols.indexOf(currentSymbol) < 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentSymbol = symbol;
            System.out.print(symbol);
            notifyAll();
        }
    }
}
