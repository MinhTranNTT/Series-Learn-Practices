package com.neet.neetdesign.behavioural.command3;

import java.util.List;

public class DivisibleBy5Command implements Command {
    @Override
    public void execute(List<Integer> numbers, List<Integer> result) {
        for (Integer number : numbers) {
            if (number % 5 == 0) {
                result.add(number);
            }
        }
    }
}
