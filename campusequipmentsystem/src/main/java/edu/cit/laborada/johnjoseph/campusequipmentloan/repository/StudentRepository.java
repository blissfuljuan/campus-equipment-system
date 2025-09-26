package edu.cit.laborada.johnjoseph.campusequipmentloan.repository;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    StudentModel findByStudentNo(String studentNo);
}