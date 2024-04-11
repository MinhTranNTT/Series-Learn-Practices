package com.neet.neetdesign.miscellaneous.finalkeyword;

public class FinalExample3 /*extends Zoo*/ { // Cannot inherit from final 'xxx.finalkeyword.Zoo'
    // Class using final keyword, all method on Class auto final
}

final class Zoo {
    public void run() {
        System.out.println("Zoo run");
    }
}
