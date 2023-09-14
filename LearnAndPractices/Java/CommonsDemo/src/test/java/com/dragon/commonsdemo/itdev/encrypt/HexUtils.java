package com.dragon.commonsdemo.itdev.encrypt;

public class HexUtils {
    public static String convertBytes2Hex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(((int)b)&0xff);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
