package edu.cit.lo.joshuanoel.campusequipmentloan.repository;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.loanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loanRepository extends JpaRepository<loanEntity, Integer> {
}
