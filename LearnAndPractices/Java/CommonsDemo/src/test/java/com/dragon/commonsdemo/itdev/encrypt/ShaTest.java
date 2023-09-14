package com.dragon.commonsdemo.itdev.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class ShaTest {
    private static final String UTF8 = StandardCharsets.UTF_8.name();
    @Test
    void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = "Java I love U!!!";
        String hex = MessageUtils.doDigest(s, "SHA-256");
        System.out.println("Hex: " + hex);
        // bbb15c7157205421642d04d931ebb7a4e73c99ec33ab443d824c219227cabed3
    }

    @Test
    void test3() throws UnsupportedEncodingException {
        String s = "Java I love U!!!";
//        String md5 = MessageUtils.doDigest(s, "SHA-256");
//        System.out.println("MD5 hand: " + md5);

        String s2 = DigestUtils.sha256Hex(s.getBytes(UTF8));
        System.out.println("HEX Lib codec: " + s2);

        // bbb15c7157205421642d04d931ebb7a4e73c99ec33ab443d824c219227cabed3
    }

    @Test
    void test4() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = "Java I love U!!!";
        String hex = MessageUtils.doDigest(s, "SHA-512");
        System.out.println("Hex: " + hex);
        // 0b69700ef7ae8c13d84e3fab8245f0087992ac164e41ef39ab43883c88bc8aa2661d51fc63bd1058122be7d6523c3974d288596a8078d7c1c120b87d6d1fc902
    }

    @Test
    void test5() throws UnsupportedEncodingException {
        String s = "Java I love U!!!";
//        String md5 = MessageUtils.doDigest(s, "SHA-512");
//        System.out.println("MD5 hand: " + md5);

        String s2 = DigestUtils.sha512Hex(s.getBytes(UTF8));
        System.out.println("HEX Lib codec: " + s2);

        // 0b69700ef7ae8c13d84e3fab8245f0087992ac164e41ef39ab43883c88bc8aa2661d51fc63bd1058122be7d6523c3974d288596a8078d7c1c120b87d6d1fc902
    }
}
