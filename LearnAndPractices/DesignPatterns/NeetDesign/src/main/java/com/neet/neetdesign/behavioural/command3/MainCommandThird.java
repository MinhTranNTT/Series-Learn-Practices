package com.neet.neetdesign.behavioural.command3;

import java.util.ArrayList;
import java.util.List;

public class MainCommandThird {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        NumberProcessor processor = new NumberProcessor(numbers);

        // Create commands
        Command evenCommand = new EvenCommand();
        Command oddCommand = new OddCommand();
        Command divisibleBy5Command = new DivisibleBy5Command();

        // Process the numbers
        List<Integer> evenNumbers = processor.process(evenCommand);
        List<Integer> oddNumbers = processor.process(oddCommand);
        List<Integer> divisibleBy5Numbers = processor.process(divisibleBy5Command);

        // Print the results
        System.out.println("List Even: " + evenNumbers);
        System.out.println("List Odd: " + oddNumbers);
        System.out.println("List Divisible by 5: " + divisibleBy5Numbers);


    }
}
