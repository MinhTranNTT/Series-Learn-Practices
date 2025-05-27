package org.blog.service.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TryCatchTest {
    public String testTryCatch1() {
        String text = "aa";
        try {
            text = "bb";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            text = "cc";
        }
        return text;
    }

    public String testTryCatch2() {
        String text = "aa";
        try {
            System.out.println("11");
            text = "bb";
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("22");
            text = "cc";
        }
        System.out.println("33");
        return text;
    }

    @Test
    public void testingMain1() {
        System.out.println(testTryCatch1());
        System.out.println("=================");
        System.out.println(testTryCatch2());
    }
}
