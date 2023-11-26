package com.practices.bestpractices.issue07;

public class Test07 {
    public static void main(String[] args) {
        ValidateUtils.isTrue(true).throwMessage("Oops, something is abnormal...");
    }
}
