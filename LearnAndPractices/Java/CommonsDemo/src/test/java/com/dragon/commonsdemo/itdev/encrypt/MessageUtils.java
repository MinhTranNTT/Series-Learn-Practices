package com.dragon.commonsdemo.itdev.encrypt;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageUtils {
    private static final String UTF8 = StandardCharsets.UTF_8.name();
    public static String doDigest(String content, String algorithm) {
        byte[] digest = new byte[0];
        try {
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            byte[] originBytes = content.getBytes(UTF8);
            digest = md5.digest(originBytes);

//            System.out.println("MD5 Encode: " + new String(digest, UTF8));
            return HexUtils.convertBytes2Hex(digest);
        } catch (Exception e) {
            return null;
        }
    }

    public static String doMacDigest(String content, String key, String algorithm) {
        byte[] digest = new byte[0];
        try {
            Mac instance = Mac.getInstance(algorithm);
            SecretKey secretKey = new SecretKeySpec(key.getBytes(UTF8), algorithm);
            instance.init(secretKey);

            byte[] originBytes = content.getBytes(UTF8);
            digest = instance.doFinal(originBytes);

            return HexUtils.convertBytes2Hex(digest);
        } catch (Exception e) {
            return null;
        }
    }
}
