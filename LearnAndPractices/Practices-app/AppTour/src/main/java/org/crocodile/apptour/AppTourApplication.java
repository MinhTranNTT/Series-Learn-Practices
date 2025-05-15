package org.crocodile.apptour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AppTourApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppTourApplication.class, args);
    }

}
