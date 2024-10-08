package org.fun.caffeinev1.question01;

import java.io.*;

public class TestTransient {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("JavaTest");
        person.setAge(25);
        System.out.println(person.getName() + " " + person.getAge());

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\MinhTran\\Downloads\\TestTransientFile\\person.txt"));) {
            objectOutputStream.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        person.par1 = "Static FIELD after serializable";

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\MinhTran\\Downloads\\TestTransientFile\\person.txt"));) {
            Person p = (Person) objectInputStream.readObject();
            System.out.println(p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
