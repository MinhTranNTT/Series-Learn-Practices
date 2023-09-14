package com.dragon.commonsdemo.itdev.encrypt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@SpringBootTest
public class Base64Test {

    private static final String UTF8 = StandardCharsets.UTF_8.name();
    @Test
    void test1() throws UnsupportedEncodingException {
        String book = "Java Headfirst";
        // SmF2YSBIZWFkZmlyc3Q=
        String encodedBook = Base64.getEncoder().encodeToString(book.getBytes(UTF8));

        System.out.println("Encoded Book: " + encodedBook);

        byte[] decodeBook = Base64.getDecoder().decode(encodedBook.getBytes(UTF8));
        System.out.println("Decode Book:" + new String(decodeBook, UTF8));

    }

//    with common-codec

    @Test
    void test2() throws UnsupportedEncodingException {
        String book = "Java Headfirst";
        // SmF2YSBIZWFkZmlyc3Q=
        String s = org.apache.commons.codec.binary.Base64.encodeBase64String(book.getBytes(UTF8));
        System.out.println("Encoded Book: " + s);

        byte[] decodeBook = org.apache.commons.codec.binary.Base64.decodeBase64(s.getBytes(UTF8));
        System.out.println("Decode Book:" + new String(decodeBook, UTF8));
    }

    // ###############
    @Test
    void test3() throws UnsupportedEncodingException {
        String str = "Java";
        String s = Base64.getEncoder().encodeToString(str.getBytes(UTF8));
        String sWithoutPadding = Base64.getEncoder().withoutPadding().encodeToString(str.getBytes(UTF8));

        System.out.println(s);  // SmF2YQ==
        System.out.println(sWithoutPadding); // SmF2YQ
    }

    @Test
    void test4() throws UnsupportedEncodingException {
        String url1 = "https://www.google.com/search?q=Hello&sca_esv=563914618&sxsrf=AB5stBgmuJy9fyfZdOTM45wVJBkYWx8rXw%3A1694233670700&source=hp&ei=RvT7ZPDCJ5rU1e8Pgt6cwAw&iflsig=AD69kcEAAAAAZPwCVm_GYIIPp-kToTZf2ZezRQkecgIv&ved=0ahUKEwjw_eug2JyBAxUaavUHHQIvB8gQ4dUDCAk&uact=5&oq=Hello&gs_lp=Egdnd3Mtd2l6IgVIZWxsbzINEC4YigUYsQMYgwEYQzIHEC4YigUYQzINEC4YigUYsQMYgwEYQzIKEAAYigUYsQMYQzIKEC4YigUYsQMYQzINEC4YigUYxwEY0QMYQzILEAAYgAQYsQMYgwEyBRAuGIAEMgsQLhiABBixAxiDATILEC4YgAQYsQMYgwFI2AZQAFiIBnAAeACQAQCYAZoDoAGgCKoBCTAuMS4yLjAuMbgBA8gBAPgBAcICBxAjGIoFGCfCAgQQIxgnwgIIEAAYigUYkQLCAgcQABiKBRhDwgIIEC4YgAQYsQM&sclient=gws-wiz";
        String s = Base64.getUrlEncoder().encodeToString(url1.getBytes(UTF8));

        System.out.println("Encode: " + s);

        byte[] decode = Base64.getUrlDecoder().decode(s.getBytes(UTF8));
        System.out.println("Decode URL: " + new String(decode, UTF8));


    }

    @Test
    void testEncodeWithMine() throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(UUID.randomUUID().toString());
        }
        String s = Base64.getMimeEncoder().encodeToString(builder.toString().getBytes(UTF8));
        System.out.println("Encode MINE: " + s);

        byte[] decode = Base64.getMimeDecoder().decode(s.getBytes(UTF8));
        System.out.println("Decode: " + new String(decode, UTF8));
    }

    // ############### APACHE

    @Test
    void testDecodeWithApache() throws UnsupportedEncodingException {
        String a = "Java";
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();

        byte[] encode = base64.encode(a.getBytes(UTF8));
        String strEncode = new String(encode, UTF8);
        System.out.println("Encode: " + strEncode);

        byte[] decode = base64.decode(strEncode.getBytes(UTF8));
        System.out.println("Decode: " + new String(decode, UTF8));

    }

    @Test
    void testApacheStatic() throws UnsupportedEncodingException {
        String s = "Java";
        String encode = new String(org.apache.commons.codec.binary.Base64.encodeBase64String(s.getBytes(UTF8)));
        System.out.println("Encode: " + encode);

        String decode = new String(org.apache.commons.codec.binary.Base64.decodeBase64(encode.getBytes(UTF8)));
        System.out.println("Decode: " + decode);
    }
}
