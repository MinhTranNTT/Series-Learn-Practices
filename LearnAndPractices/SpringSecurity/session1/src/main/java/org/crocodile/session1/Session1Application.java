package org.crocodile.session1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan("org.crocodile")
// @EntityScan("org.crocodile.session1.model")
// @EnableJpaRepositories("org.crocodile.session1.repository")
// @EnableWebSecurity
public class Session1Application {

	public static void main(String[] args) {
		SpringApplication.run(Session1Application.class, args);
	}

}
