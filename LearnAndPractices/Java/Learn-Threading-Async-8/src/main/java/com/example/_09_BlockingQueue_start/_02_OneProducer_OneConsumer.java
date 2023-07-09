package com.example._09_BlockingQueue_start;



import com.example.ThreadUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class _02_OneProducer_OneConsumer {
    public static void main(String[] args) {
        Queue<String> shaobingQueue = new LinkedList<>();

        List<String> xiaoBaiMsg = new LinkedList<>();
        List<String> roadPeopleAMsg = new LinkedList<>();

        Thread xiaoBai = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String tmp = String.format("The %d biscuit", i + 1);
                shaobingQueue.add(tmp);
                xiaoBaiMsg.add(String.format("%d Xiaobai made it [%s]", System.currentTimeMillis(), tmp));
            }
        });

        Thread roadPeopleA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                roadPeopleAMsg.add(String.format("%d  A passerby bought it [%s]", System.currentTimeMillis(), shaobingQueue.poll()));
            }
        });

        xiaoBai.start();
        roadPeopleA.start();

        try {
            xiaoBai.join();
            roadPeopleA.join();
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("join generate interrupt" + e.getMessage());
        }

        System.out.println(xiaoBaiMsg.stream().collect(Collectors.joining("\n")));
        System.out.println("--------------------------");   // 分隔线
        System.out.println(roadPeopleAMsg.stream().collect(Collectors.joining("\n")));
    }
}
