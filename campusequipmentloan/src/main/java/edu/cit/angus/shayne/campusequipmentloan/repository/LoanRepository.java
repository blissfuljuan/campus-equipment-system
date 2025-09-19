package edu.cit.angus.shayne.campusequipmentloan.repository;

import edu.cit.angus.shayne.campusequipmentloan.model.Loan;
import edu.cit.angus.shayne.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentAndStatus(Student student, String status);
}
