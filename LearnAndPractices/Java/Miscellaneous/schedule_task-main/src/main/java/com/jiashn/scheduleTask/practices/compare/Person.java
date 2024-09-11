package com.jiashn.scheduleTask.practices.compare;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class Person implements Comparable<Person> {
    private String name;
    private int age;

    @Override
    public int compareTo(Person o) {
        return this.age - o.getAge(); // asc
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person("Alice", 25),
                new Person("Bob", 20),
                new Person("Charlie", 23)
        };
        System.out.println("origin");
        for (Person person : people) {
            System.out.print(person + "   ");
        }
        System.out.println();

        // sort with Collection
        Arrays.sort(people);

        System.out.println("after sort");
        for (Person person : people) {
            System.out.print(person + "   ");
        }
    }
}
