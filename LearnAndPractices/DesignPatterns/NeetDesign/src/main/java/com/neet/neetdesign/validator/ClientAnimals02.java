package com.neet.neetdesign.validator;

import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClientAnimals02 {
    public static void main(String[] args) {
        Animal dog = new Animal(1,"dog",5);
        Animal cat = new Animal(2,"cat",2);
        Animal bird = new Animal(3,"bird",6);
        Animal elephant = new Animal(4,"elephant",50);
        Animal butterfly = new Animal(5,"butterfly",20);
        Animal fish = new Animal(6,"fish",6);
        Animal bee = new Animal(7,"bee",15);
        Animal snake = new Animal(8,"snake",2);
        Animal chicken = new Animal(9,"chicken",6);
        Animal pig = new Animal(10,"pig",6);

        List<Animal> list = new ArrayList<>();
        list.add(dog);
        list.add(cat);
        list.add(bird);

        list.add(elephant);
        list.add(butterfly);
        list.add(fish);

        list.add(bee);
        list.add(snake);
        list.add(chicken);
        list.add(pig);

        System.out.println("=========================");

        String fileName = "DataAnimal.jsonl";

        // try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        //     Gson gson = new Gson();
        //     for (Animal animal : list) {
        //         String json = gson.toJson(animal);
        //         writer.write(json);
        //         writer.newLine();
        //     }
        //     System.out.println("Successfully wrote JSON to file: " + fileName);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }


        // List<Animal> animalList = readAnimalsFromFile(fileName);
        // for (Animal animal : animalList) {
        //     System.out.println(animal);
        // }

        String outputPrefix = "output";
        int batchSize = 3;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Gson gson = new Gson();
            String line;
            int batchCount = 1;
            List<Animal> batch = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                Animal animal = gson.fromJson(line, Animal.class);
                batch.add(animal);
                if (batch.size() == batchSize) {
                    writeBatchToFile(batch, outputPrefix + batchCount + ".jsonl");
                    batch.clear();
                    batchCount++;
                }
            }
            if (!batch.isEmpty()) {
                writeBatchToFile(batch, outputPrefix + batchCount + ".jsonl");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Animal> readAnimalsFromFile(String fileName) {
        List<Animal> animalList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Gson gson = new Gson();
            String line;
            while ((line = reader.readLine()) != null) {
                Animal animal = gson.fromJson(line, Animal.class);
                animalList.add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return animalList;
    }

    private static void writeBatchToFile(List<Animal> batch, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Gson gson = new Gson();
            for (Animal animal : batch) {
                String json = gson.toJson(animal);
                writer.write(json);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
