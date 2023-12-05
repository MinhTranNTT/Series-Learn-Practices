package com.practices.test01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class TestHeap {
    public static void main(String[] args) {
        List<CustomerTest> customers = new ArrayList<>();

        while (true) {
            CustomerTest c = new CustomerTest("Mike");
            if (customers.size() >= 100) {
                for (int i = 0; i < 10; i++) {
                    customers.remove(0);
                }
            }
        }
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class CustomerTest {
    private String name;
}
