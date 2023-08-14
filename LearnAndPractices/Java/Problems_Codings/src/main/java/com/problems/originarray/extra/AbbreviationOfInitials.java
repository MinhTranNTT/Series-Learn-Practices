package com.problems.originarray.extra;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AbbreviationOfInitials {
    public static void main(String[] args) {
        String[] data = initialData();

        for (String item : data) {
            if (item == null || item.isEmpty()) continue;

            String output = Arrays.stream(item.split(" "))
                    .filter(AbbreviationOfInitials::getWordUpperCaseCharacter)
                    .map(AbbreviationOfInitials::extractFirstUppercaseWord)
                    .collect(Collectors.joining(""));

            System.out.println(output);

        }
    }

    private static String extractFirstUppercaseWord(String word) {
        Matcher matcher = Pattern.compile("\\b[A-Z][a-zA-Z]*\\b").matcher(word);
        StringBuilder builder = new StringBuilder();

        while(matcher.find()) {
            builder.append(matcher.group());
        }

        return Character.toString(builder.toString().trim().charAt(0));
    }

    private static boolean getWordUpperCaseCharacter(String word) {
        return !word.isEmpty() && Character.isUpperCase(word.charAt(0));
    }

    public static String[] initialData() {
        String[] input = {
                "Hinata Card App team",
                "In Dode We Drust",
                "Power in % Imagination",
                "%$EQ DieB a8 YW",
        };
        return input;
    }
}
