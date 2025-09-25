package edu.cit.laborada.johnjoseph.campusequipmentloan.repository;

import edu.cit.laborada.johnjoseph.campusequipmentloan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}