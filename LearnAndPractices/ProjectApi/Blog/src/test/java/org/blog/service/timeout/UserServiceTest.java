package org.blog.service.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Test
    public void testCase3() {
        String str = "a" + "b";
    }


    @Test
    public void testCase1() {
        String str = new String("ab");
    }

    @Test
    public void testCase2() {
        String str = new String("a") + new String("b");
    }

    @Test
    public void testCaseInteger() {
        Integer x1 = 256;
        Integer x2 = 256;
        Integer x5 = 127;

        Integer x3 = 128;
        Integer x4 = 128;
        Integer x6 = new Integer(127);

        // System.out.println(x1 == x2);
        // System.out.println(x3 == x4);
        System.out.println(x5 == x6);

    }
}