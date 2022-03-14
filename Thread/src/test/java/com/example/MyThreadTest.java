package com.example;


import org.junit.jupiter.api.Test;

class MyThreadTest {

    @Test
    void run() {
        Thread t = new MyThread();
        t.start();
    }
}