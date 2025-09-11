package edu.cit.lo.joshuanoel.campusequipmentloan.repository;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.studentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<studentEntity, Integer> {

}
