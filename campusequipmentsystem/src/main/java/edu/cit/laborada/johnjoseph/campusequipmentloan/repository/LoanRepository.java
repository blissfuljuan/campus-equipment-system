package edu.cit.laborada.johnjoseph.campusequipmentloan.repository;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<LoanModel, Long> {

    @Query("SELECT COUNT(l) FROM LoanModel l WHERE l.student.id = :studentId AND l.status IN ('ONGOING','OVERDUE')")
    long countActiveLoans(Long studentId);
}