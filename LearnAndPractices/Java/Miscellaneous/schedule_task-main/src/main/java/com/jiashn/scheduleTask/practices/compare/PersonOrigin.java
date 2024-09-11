package com.jiashn.scheduleTask.practices.compare;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;

@Data
@AllArgsConstructor
public class PersonOrigin {
    private String name;
    private int age;

    public static class NameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName()); //asc alphabet
        }
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person("Bob", 20),
                new Person("Alice", 25),
                new Person("Charlie", 23)
        };

        System.out.println("origin");
        for (Person person : people) {
            System.out.print(person + "   ");
        }
        System.out.println();

        // sort with custom style
        Arrays.sort(people, new NameComparator());

        System.out.println("after");
        for (Person person : people) {
            System.out.print(person + "   ");
        }
    }
}
