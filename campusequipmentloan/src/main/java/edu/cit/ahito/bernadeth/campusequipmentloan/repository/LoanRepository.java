package edu.cit.angus.shayne.campusequipmentloan.repository;

import edu.cit.angus.shayne.campusequipmentloan.model.Loan;
import edu.cit.angus.shayne.campusequipmentloan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    // Count how many active loans a student currently has
    int countByStudentIdAndStatus(Long studentId, LoanStatus status);
}
