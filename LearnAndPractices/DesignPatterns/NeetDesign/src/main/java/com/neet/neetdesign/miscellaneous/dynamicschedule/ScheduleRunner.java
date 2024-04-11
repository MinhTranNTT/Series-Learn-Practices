package com.neet.neetdesign.miscellaneous.dynamicschedule;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleRunner implements CommandLineRunner {

    CronTaskRegister cronTaskRegister;

    @Override
    public void run(String... args) throws Exception {


    }
}
