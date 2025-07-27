package com.project.bookcenter;

import com.project.bookcenter.external.aladin.AladinApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class BookCenterApplication implements CommandLineRunner {

	@Autowired
	public AladinApiService aladinApiService;

	public static void main(String[] args) {
		SpringApplication.run(BookCenterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		aladinApiService.fetchBooksByCategory("351"); // IT
	}
}
