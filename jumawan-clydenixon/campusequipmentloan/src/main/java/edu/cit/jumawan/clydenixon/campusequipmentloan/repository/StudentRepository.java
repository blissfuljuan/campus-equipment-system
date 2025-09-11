package edu.cit.jumawan.clydenixon.campusequipmentloan.repository;
import edu.cit.jumawan.clydenixon.campusequipmentloan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student, Long> {
}