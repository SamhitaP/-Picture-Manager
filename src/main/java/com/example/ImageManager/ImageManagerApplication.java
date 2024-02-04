package com.example.ImageManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableMongoRepositories
@EnableWebMvc
public class ImageManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageManagerApplication.class, args);
	}

}
