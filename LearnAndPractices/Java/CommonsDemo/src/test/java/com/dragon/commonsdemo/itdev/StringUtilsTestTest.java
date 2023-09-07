package com.dragon.commonsdemo.itdev;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringUtilsTestTest {

    @Test
    void test1() {
        String str = "12345678911";
        // return number of characters to left of the string
        // 123
        String left = StringUtils.left(str, 3);
        System.out.println(left);

        // return number of characters to right of the string
        // 8911
        String right = StringUtils.right(str, 4);
        System.out.println(right);

        // 123****
        String padRight = StringUtils.rightPad(left, 2, '*');
        System.out.println(padRight);

        // *8911
        String padLeft = StringUtils.leftPad(right, 5, '*');
        System.out.println(padLeft);
    }

    @Test
    void test2() {
        String str = "    ";
        System.out.println(StringUtils.isBlank(str));
        System.out.println(StringUtils.isEmpty(str));
        System.out.println(StringUtils.isNotEmpty(str));
    }

    @Test
    void test3() {
        String str = "aj\tld1\b23aAbCs  kF45JAb  c56sl";
        System.out.println(str);
        // remove all character 'a' in string
//        System.out.println(CharMatcher.is('a').removeFrom(str));

        // remove all character is not 'A'
//        System.out.println(CharMatcher.isNot('A').removeFrom(str));

        // return string matching input
//        System.out.println(CharMatcher.anyOf("abc").retainFrom(str));

        // keep a,b,c in string
//        System.out.println(CharMatcher.noneOf("abc").removeFrom(str));
//        System.out.println(CharMatcher.noneOf("abc").retainFrom(str));

        // replace to 6 with character 'a' OR 'j' in string
//        System.out.println(CharMatcher.inRange('a', 'j').replaceFrom(str, "6"));

        // remove white space in string
//        System.out.println(CharMatcher.breakingWhitespace().removeFrom(str));

        // remove digit in string
//        System.out.println(CharMatcher.digit().removeFrom(str));

        // remove character system \t, \n, ...
//        System.out.println(CharMatcher.javaIsoControl().removeFrom(str));

        // get lowercase in string
//        System.out.println(CharMatcher.javaLowerCase().retainFrom(str));

        // merge condition
        System.out.println(CharMatcher.javaUpperCase().or(CharMatcher.digit()).retainFrom(str));
    }
}