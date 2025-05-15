package org.crocodile.apptour;

import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootTest
public class DemoStringConstantPoolTests {

    @Test
    public void testLocalDate() {
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);
        // 2025-03-22
    }

    @Test
    public void test() {
        String str2 = new String("str") + new String("01"); // str01
        str2.intern();
        String str1 = "str01";
        System.out.println(str2 == str1);

        String str3 = new String("str01");
        str3 = str3.intern();
        String str4 = "str01";
        System.out.println(str3 == str4);

        // String s1 = "abc" + "efd";
        // String s2 = "abcefd";
        // System.out.println(s1 == s2 );
    }


    @Test
    public void testStudent() {
        Student student = new Student();
        System.out.println("student = " + student);
    }

    @Data
    class Student {
        private String name;
        private int age;

        {
            name = "default";
            age = 5;
        }
    }

    @ToString
    class Animal {
        public Animal getAnimal() {
            return new Animal();
        }
    }

    class Dog extends Animal {
        @Override
        public Dog getAnimal() {  // Cho phép đổi kiểu trả về từ Animal thành Dog
            return new Dog();
        }
    }

    @Test
    public void testPolymorphism() {
        Animal animal = new Dog();
        System.out.println(animal.getAnimal());
    }

    enum DAY {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    @Test
    public void testEnum01() {
        DAY today = DAY.MONDAY;

        if (today == DAY.MONDAY) {
            System.out.println("Hôm nay là thứ Hai!");
        }

        System.out.println("DAY.values() = " + Arrays.toString(DAY.values()));
        System.out.println("DAY.MONDAY.ordinal() = " + DAY.MONDAY.ordinal());
        System.out.println("DAY.MONDAY.name() = " + DAY.MONDAY.name());
        System.out.println("DAY.valueOf(\"TUESDAY\") = " + DAY.valueOf("TUESDAY"));

    }

    public interface Description {
        String getDescription();
    }

    public enum PaymentMethod implements Description {
        CREDIT_CARD {
            @Override
            public String getDescription() {
                return "CREDIT_CARD";
            }
        },

        PAYPAL {
            @Override
            public String getDescription() {
                return "PAYPAL";
            }
        }
    }

    @Test
    public void testEnumImplementsInterface() {
        Description creditCard = PaymentMethod.CREDIT_CARD;
        System.out.println("creditCard.getDescription() = " + creditCard.getDescription());

        Description paypal = PaymentMethod.PAYPAL;
        System.out.println("paypal.getDescription() = " + paypal.getDescription());
    }
}
