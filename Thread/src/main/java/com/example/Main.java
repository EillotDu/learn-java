package com.example;

public class Main {

//    Thread t = new Thread(() -> {
//        int n = 0;
//        while (! isInterrupted()) {
//            n ++;
//            System.out.println(n + " hello!");
//        }
//    });

    public static void main(String[] args)  throws InterruptedException {
        HelloThread t = new HelloThread();
        t.start();
        Thread.sleep(10);
        t.running = false; // 标志位置为false
    }
}
