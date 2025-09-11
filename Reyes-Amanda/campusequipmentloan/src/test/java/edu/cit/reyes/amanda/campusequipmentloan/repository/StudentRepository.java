package edu.cit.reyes.amanda.campusequipmentloan.repository;

import edu.cit.reyes.amanda.campusequipmentloan.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {}