package edu.cit.reyes.amanda.campusequipmentloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import edu.cit.reyes.amanda.campusequipmentloan.model.Equipment;
import edu.cit.reyes.amanda.campusequipmentloan.model.Student;
import edu.cit.reyes.amanda.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.reyes.amanda.campusequipmentloan.repository.StudentRepository;

@SpringBootApplication
public class CampusequipmentloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusequipmentloanApplication.class, args);
	}

	@Bean
	CommandLineRunner seedData(EquipmentRepository equipmentRepo, StudentRepository studentRepo) {
		return args -> {
			if (studentRepo.count() == 0) {
				studentRepo.save(new Student("S2023001", "Alice Johnson", "alice@example.com"));
				studentRepo.save(new Student("S2023002", "Bob Smith", "bob@example.com"));
			}
			if (equipmentRepo.count() == 0) {
				equipmentRepo.save(new Equipment("Canon DSLR", "Camera", "SN-CAM-001"));
				equipmentRepo.save(new Equipment("Dell XPS 13", "Laptop", "SN-LAP-001"));
			}
		};
	}
}
