package com.neet.neetdesign.miscellaneous.finalkeyword;

public class Example {
    final StringBuilder stringBuilder;

    public Example() {
        stringBuilder = new StringBuilder("Hello");
    }

    public void appendStringBuilder(String string) {
        stringBuilder.append(string);
    }

    public StringBuilder getStringBuilder() {
        appendStringBuilder(" world");
        return stringBuilder;
    }

    public static void main(String[] args) {
        Example example = new Example();
        System.out.println(example.getStringBuilder());
    }

}
