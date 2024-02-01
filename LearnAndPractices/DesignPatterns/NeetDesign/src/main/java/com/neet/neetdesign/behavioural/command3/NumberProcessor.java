package com.neet.neetdesign.behavioural.command3;

import java.util.ArrayList;
import java.util.List;

public class NumberProcessor {
    private List<Integer> numbers;

    public NumberProcessor(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> process(Command command) {
        List<Integer> result = new ArrayList<>();
        command.execute(numbers, result);
        return result;
    }
}
