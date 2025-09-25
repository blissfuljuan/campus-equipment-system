package edu.cit.angus.shayne.campusequipmentloan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.cit.angus.shayne.campusequipmentloan.service.UserService;

@SpringBootApplication
public class CampusequipmentloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusequipmentloanApplication.class, args);
	}

	// ✅ This will run on app startup and insert a test user if not exists
	@Bean
	CommandLineRunner init(UserService userService) {
		return args -> {
			if (userService.authenticate("student1", "password123").isEmpty()) {
				userService.register("student1", "password123", "STUDENT");
				System.out.println("✅ Seeded test user: student1 / password123");
			}
		};
	}
}
