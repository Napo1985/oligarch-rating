package org.example.oligarchrating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OligarchRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OligarchRatingApplication.class, args);
	}

}
