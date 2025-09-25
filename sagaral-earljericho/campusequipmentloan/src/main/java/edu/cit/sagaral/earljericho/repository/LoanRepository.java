package edu.cit.sagaral.earljericho.campusequipmentloan.repository;

import edu.cit.sagaral.earljericho.campusequipmentloan.model.Loan;
import edu.cit.sagaral.earljericho.campusequipmentloan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    // Count how many active loans a student currently has
    int countByStudentIdAndStatus(Long studentId, LoanStatus status);
}
