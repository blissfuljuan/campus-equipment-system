package edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository;

import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}



