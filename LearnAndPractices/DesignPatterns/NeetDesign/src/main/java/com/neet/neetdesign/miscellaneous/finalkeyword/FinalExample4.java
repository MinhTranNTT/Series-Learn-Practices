package com.neet.neetdesign.miscellaneous.finalkeyword;

public class FinalExample4 {
    public static String test() {
        String str = null;
        int i = 0;
        if (i == 0) {
            return str;//Return directly, to the finally statement block that is not executed
        }
        try {
            System.out.println("try...");
            return str;
        } finally {
            System.out.println("finally...");
        }
    }

    public static String test2() {
        String str = null;
        int i = 0;
        i = i / 0;//The exception is thrown and the finally statement block is not executed.
        try {
            System.out.println("try...");
            return str;
        } finally {
            System.out.println("finally...");
        }
    }

    public static String test3() {
        String str = null;
        try {
            System.out.println("try...");
            System.exit(0);//The system exits without executing the finally statement block.
            return str;
        } finally {
            System.out.println("finally...");
        }
    }

    public static void main(String[] args) {
        // System.out.println(test()); // null
        // System.out.println(test2()); //
        System.out.println(test3()); //
    }
}
