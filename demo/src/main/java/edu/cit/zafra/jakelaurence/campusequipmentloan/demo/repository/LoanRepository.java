package edu.cit.zafra.jakelaurence.campusequipmentloan.demo.repository;

import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.LoanEntity;
import edu.cit.zafra.jakelaurence.campusequipmentloan.demo.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    @Query("select count(l) from LoanEntity l where l.student = :student and l.status = 'ACTIVE'")
    long countActiveLoansByStudent(@Param("student") StudentEntity student);

    @Query("select l from LoanEntity l where l.status = 'ACTIVE' and l.dueDate < :today")
    List<LoanEntity> findOverdueActiveLoans(@Param("today") LocalDate today);
}



