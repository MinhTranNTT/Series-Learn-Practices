package com.example._09_BlockingQueue_start;



import com.example.ThreadUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class _07_LinkedBlockingQueue_Scenes {
    public static void main(String[] args) {
        BlockingQueue<String> shaobingQueue = new LinkedBlockingQueue<>(3);

        List<String> xiaoBaiMsg = new LinkedList<>();
        List<String> chefAMsg = new LinkedList<>();
        List<String> roadPeopleAMsg = new LinkedList<>();
        List<String> roadPeopleBMsg = new LinkedList<>();

        Thread xiaoBai = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String shaobing = String.format("Xiaobai's %d biscuit", i + 1);
                try {
                    shaobingQueue.put(shaobing);
                } catch (InterruptedException e) {
                    ThreadUtils.printTimeAndThread("小白被中断" + e.getMessage());
                }
                xiaoBaiMsg.add(String.format("%d Xiaobai made it [%s]", System.currentTimeMillis(), shaobing));
            }
        });
        Thread chushiA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String shaobing = String.format("Chef A's %d pancake", i + 1);
                try {
                    shaobingQueue.put(shaobing);
                } catch (InterruptedException e) {
                    ThreadUtils.printTimeAndThread("Chef A is interrupted" + e.getMessage());
                }
                chefAMsg.add(String.format("%d Chef A made [%s]", System.currentTimeMillis(), shaobing));
            }
        });

        Thread roadPeopleA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String shaobing = null;
                try {
                    shaobing = shaobingQueue.take();
                } catch (InterruptedException e) {
                    ThreadUtils.printTimeAndThread("路人甲被中断" + e.getMessage());
                }
                roadPeopleAMsg.add(String.format("%d  A passerby bought it [%s]", System.currentTimeMillis(), shaobing));
            }
        });

        Thread roadPeopleB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String shaobing = null;
                try {
                    shaobing = shaobingQueue.take();
                } catch (InterruptedException e) {
                    ThreadUtils.printTimeAndThread("路人乙被中断" + e.getMessage());
                }
                roadPeopleBMsg.add(String.format("%d  Passerby B bought it [%s]", System.currentTimeMillis(), shaobing));
            }
        });

        xiaoBai.start();
        chushiA.start();
        roadPeopleA.start();
        roadPeopleB.start();

        try {
            xiaoBai.join();
            chushiA.join();
            roadPeopleA.join();
            roadPeopleB.join();
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("join generate interrupt" + e.getMessage());
        }

        System.out.println(xiaoBaiMsg.stream().collect(Collectors.joining("\n")));
        System.out.println(chefAMsg.stream().collect(Collectors.joining("\n")));
        System.out.println("--------------------------");   // 分隔线
        System.out.println(roadPeopleAMsg.stream().collect(Collectors.joining("\n")));
        System.out.println(roadPeopleBMsg.stream().collect(Collectors.joining("\n")));
    }
}
