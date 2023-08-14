package com.problems.originstring.leet._13;

public class _13_Roman2Integer_Enum {
    public static void main(String[] args) {
        System.out.println(getTextFromRomanEnum("I"));
        System.out.println(getTextFromRomanEnum("II"));
        System.out.println(getTextFromRomanEnum("III"));
        System.out.println(getTextFromRomanEnum("LC"));
        System.out.println(getTextFromRomanEnum("VM"));
        System.out.println(getTextFromRomanEnum("IV"));
        System.out.println(getTextFromRomanEnum("V"));
        System.out.println(getTextFromRomanEnum("VI"));
        System.out.println(getTextFromRomanEnum("VIII"));
        System.out.println(getTextFromRomanEnum("IX"));
        System.out.println(getTextFromRomanEnum("X"));
    }

    private static int getTextFromRomanEnum(String romanText) {
        int prevValue =0;
        int ans=0;
        for (int i=romanText.length()-1 ; i>=0 ; i--) {
            int num = RomanEnum.valueOf(String.valueOf(romanText.charAt(i))).getValue();
            // When the value of the current character
            // is greater than or equal to 4 times the value of the previous character,
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }

}

enum RomanEnum {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    private int value;

    RomanEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
