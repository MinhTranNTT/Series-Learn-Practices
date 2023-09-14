package com.dragon.commonsdemo.itdev.encrypt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class UrlTest {
    private static final String UTF8 = StandardCharsets.UTF_8.name();

    @Test
    void testUrl1() throws UnsupportedEncodingException {
        String url = "https://google.com/";
        String encode = URLEncoder.encode(url, UTF8);
        System.out.println("Encode : " + encode);

        String decode = URLDecoder.decode(encode, UTF8);
        System.out.println("Decode : " + decode);
    }
}
