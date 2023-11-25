package com.example.goodcontroller.service;

import com.example.goodcontroller.dto.TestDTO;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public Double service(TestDTO testDTO) throws Exception {
        if (testDTO.getNum() <= 0) {
            throw new Exception("The number entered needs to be greater than 0");
        }
        if (testDTO.getType().equals("square")) {
            return Math.pow(testDTO.getNum(), 2);
        }
        if (testDTO.getType().equals("factorial")) {
            double result = 1;
            int num = testDTO.getNum();
            while (num > 1) {
                result = result * num;
                num -= 1;
            }
            return result;
        }
        throw new Exception("Unrecognized algorithm");
    }
}
