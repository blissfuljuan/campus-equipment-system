package edu.cit.lo.joshuanoel.campusequipmentloan.repository;

import edu.cit.lo.joshuanoel.campusequipmentloan.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userEntity, Integer> {
    userEntity findByUsername(String username);
}
