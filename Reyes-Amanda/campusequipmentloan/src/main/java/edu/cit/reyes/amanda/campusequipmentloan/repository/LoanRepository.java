package edu.cit.reyes.amanda.campusequipmentloan.repository;

import edu.cit.reyes.amanda.campusequipmentloan.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentAndStatus(Student student, Loan.Status status);
}


