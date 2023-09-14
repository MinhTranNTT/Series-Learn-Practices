package com.dragon.commonsdemo.itdev.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class MD5Test {
    private static final String UTF8 = StandardCharsets.UTF_8.name();

    @Test
    void test1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = "Java I love U!!!";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] originBytes = s.getBytes(UTF8);
        byte[] digest = md5.digest(originBytes);

//        md5.update(originBytes);
//        md5.digest();

        System.out.println("MD5 Encode: " + new String(digest, UTF8));

        String hex = HexUtils.convertBytes2Hex(digest);
        System.out.println("Hex: " + hex);
    }

    @Test
    void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = "Java I love U!!!";
        String hex = MessageUtils.doDigest(s, "MD5");
        System.out.println("Hex: " + hex);
    }
    // with CodeC


    @Test
    void test3() throws UnsupportedEncodingException {
        // d52387880e1ea22817a72d3759213819
        String s = "Java";
        String md5 = MessageUtils.doDigest(s, "MD5");
        System.out.println("MD5 hand: " + md5);

        // 4a617661
        String s1 = Hex.encodeHexString(s.getBytes(UTF8));
        System.out.println("HEX codec: " + s1);

        // d52387880e1ea22817a72d3759213819
        String s2 = DigestUtils.md5Hex(s.getBytes(UTF8));
        System.out.println("HEX Lib codec: " + s2);
    }
}
