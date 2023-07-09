package com.example.createthread01;

import java.util.concurrent.*;

public class _01_Create_Thread_FutureTask {
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            System.out.println("I am a subtask");
//            Thread.sleep(5000);   TimeoutException
            return "sub task done";
        };

        FutureTask<String> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Child Thread Start");

        try {
            String result = task.get(2, TimeUnit.MILLISECONDS);
            System.out.println("Child Thread return value: " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main END");
    }
}
