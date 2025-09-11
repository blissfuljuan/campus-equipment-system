package edu.cit.yu.rainricrandy.campuseequipmentloan.repository;

import edu.cit.yu.rainricrandy.campuseequipmentloan.model.Loan;
import edu.cit.yu.rainricrandy.campuseequipmentloan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentIdAndStatus(Long studentId, LoanStatus status);
}