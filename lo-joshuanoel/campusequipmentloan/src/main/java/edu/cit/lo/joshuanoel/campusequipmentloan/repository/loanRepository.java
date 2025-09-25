package edu.cit.lo.joshuanoel.campusequipmentloan.repository;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.loanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface loanRepository extends JpaRepository<loanEntity, Integer> {
    @Query("SELECT l FROM loanEntity l WHERE l.student = :student AND l.status = 'ACTIVE'")
    List<loanEntity> findActiveLoansByStudent(String student);
}
