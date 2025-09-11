package edu.cit.yu.rainricrandy.campuseequipmentloan.repository;

import edu.cit.yu.rainricrandy.campuseequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailabilityTrue();
}