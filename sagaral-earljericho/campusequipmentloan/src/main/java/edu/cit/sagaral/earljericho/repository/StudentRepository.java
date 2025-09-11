package edu.cit.sagaral.earljericho.campusequipmentloan.repository;

import edu.cit.sagaral.earljericho.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}