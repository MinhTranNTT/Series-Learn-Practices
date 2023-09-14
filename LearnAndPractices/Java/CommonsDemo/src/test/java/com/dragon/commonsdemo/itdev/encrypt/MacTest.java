package com.dragon.commonsdemo.itdev.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class MacTest {

    private static final String UTF8 = StandardCharsets.UTF_8.name();

    @Test
    void test4() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = "Java I love U!!!";
        String hex = MessageUtils.doMacDigest(s,"123","HmacMD5");
        String hex256 = MessageUtils.doMacDigest(s,"123","HmacSHA256");
        String hex512 = MessageUtils.doMacDigest(s,"123","HmacSHA512");
        System.out.println("Mac: " + hex);
        System.out.println("Mac256: " + hex256);
        System.out.println("Mac512: " + hex512);
        // 37fcfa50198a3b78146b97a51bbd8bc8
        // 227c8876a856ce8280e67dd45942a1b63ca10341224dd5b486ff5e9e26056ef3
        // 4d8424416b32a5dd058623925eadf4bc0e98711bb1d26825f0b140dc987770a50eeb47a70ef5182e3d3b14534d237460097a2ba6246daf2cfc7f76946466ee2a
    }

    @Test
    void test5() throws UnsupportedEncodingException {
        String s = "Java I love U!!!";
        String key = "123";

        String s1 = new HmacUtils(HmacAlgorithms.HMAC_MD5, key.getBytes(UTF8)).hmacHex(s.getBytes(UTF8));
        String s2 = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key.getBytes(UTF8)).hmacHex(s.getBytes(UTF8));
        String s3 = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, key.getBytes(UTF8)).hmacHex(s.getBytes(UTF8));

        System.out.println("Mac Lib codec: " + s1);
        System.out.println("Mac Lib codec 256: " + s2);
        System.out.println("Mac Lib codec 512: " + s3);

        // 37fcfa50198a3b78146b97a51bbd8bc8
        // 227c8876a856ce8280e67dd45942a1b63ca10341224dd5b486ff5e9e26056ef3
        // 4d8424416b32a5dd058623925eadf4bc0e98711bb1d26825f0b140dc987770a50eeb47a70ef5182e3d3b14534d237460097a2ba6246daf2cfc7f76946466ee2a
    }

}
