package com.cj.concurrencystep.step00;

public class CpuTaskDemo {
    public static void main(String[] args) {
        CpuTaskDemo cpu = new CpuTaskDemo();
        Thread taskA = new Thread(() -> {
            handlerPrintCharacter("A", cpu);
        });
        taskA.start();

        Thread taskB = new Thread(() -> {
            handlerPrintCharacter("B", cpu);
        });
        taskB.start();

        Thread taskC = new Thread(() -> {
            handlerPrintCharacter("C", cpu);
        });
        taskC.start();
    }

    private static void handlerPrintCharacter(String s, CpuTaskDemo cpu) {
        for (int i = 0; i < 50; i++) {
            cpu.printChar(s)
;        }
    }

    private void printChar(String s) {
        System.out.print(s);
    }
}
