package com.problems.originstring.leet._13;

public class _13_Roman2Integer {
    public static void main(String[] args) {
        System.out.println(getTextFromRoman("I"));
        System.out.println(getTextFromRoman("II"));
        System.out.println(getTextFromRoman("III"));
        System.out.println(getTextFromRoman("LC"));
        System.out.println(getTextFromRoman("VM"));
        System.out.println(getTextFromRoman("IV"));
        System.out.println(getTextFromRoman("V"));
        System.out.println(getTextFromRoman("VI"));
        System.out.println(getTextFromRoman("VIII"));
        System.out.println(getTextFromRoman("IX"));
        System.out.println(getTextFromRoman("X"));
    }

    private static int getTextFromRoman(String romanText) {
        int num=0; int ans=0;
        for (int i=romanText.length()-1 ; i>=0 ; i--) {
            switch(romanText.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            // When the value of the current character
            // is greater than or equal to 4 times the value of the previous character,
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }

}
