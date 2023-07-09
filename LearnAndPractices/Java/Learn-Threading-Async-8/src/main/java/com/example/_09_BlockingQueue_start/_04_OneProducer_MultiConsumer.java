package com.example._09_BlockingQueue_start;



import com.example.ThreadUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _04_OneProducer_MultiConsumer {
    public static void main(String[] args) {
        final int count = 30;
        Queue<String> shaobingQueue = new LinkedList<>();

        List<String> xiaoBaiMsg = new LinkedList<>();
        List<String> roadPeopleAMsg = new LinkedList<>();
        List<String> roadPeopleBMsg = new LinkedList<>();

        Thread xiaoBai = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                String tmp = String.format("The %d biscuit", i + 1);
                shaobingQueue.add(tmp);
                xiaoBaiMsg.add(String.format("%d Xiaobai made it [%s]", System.currentTimeMillis(), tmp));
            }
        });

        Thread roadPeopleA = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                roadPeopleAMsg.add(String.format("%d  A passerby bought it [%s]", System.currentTimeMillis(), shaobingQueue.poll()));
            }
        });
        Thread roadPeopleB = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                roadPeopleBMsg.add(String.format("%d  Passerby B bought it [%s]", System.currentTimeMillis(), shaobingQueue.poll()));
            }
        });


        xiaoBai.start();
        roadPeopleA.start();
        roadPeopleB.start();

        try {
            xiaoBai.join();
            roadPeopleA.join();
            roadPeopleB.join();
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("join generate interrupt" + e.getMessage());
        }

        List<String> xiaoBaiMsgSub = xiaoBaiMsg.subList(xiaoBaiMsg.size() - 1, xiaoBaiMsg.size());
        System.out.println(xiaoBaiMsgSub.stream().collect(Collectors.joining("\n")));
        System.out.println("--------------------------");   // 分隔线

        Predicate<String> notContainsNull = str -> !str.contains("null");
        System.out.println(roadPeopleAMsg.stream().filter(notContainsNull).collect(Collectors.joining("\n")));
        System.out.println("--------------------------");   // 分隔线
        System.out.println(roadPeopleBMsg.stream().filter(notContainsNull).collect(Collectors.joining("\n")));
    }
}
