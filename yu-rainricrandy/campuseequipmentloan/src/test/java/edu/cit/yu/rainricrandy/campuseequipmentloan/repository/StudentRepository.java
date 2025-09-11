package edu.cit.yu.rainricrandy.campuseequipmentloan.repository;

import edu.cit.yu.rainricrandy.campuseequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
